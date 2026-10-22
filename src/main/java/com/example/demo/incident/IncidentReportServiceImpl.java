package com.example.demo.incident;

import com.example.demo.incident.dto.IncidentReportDto;
import com.example.demo.incident.dto.IncidentStatsDto;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidentReportServiceImpl implements IncidentReportService {

    private final IncidentReportRepository incidentReportRepository;
    private final UserRepository userRepository;

    public IncidentReportServiceImpl(
            IncidentReportRepository incidentReportRepository,
            UserRepository userRepository
    ) {
        this.incidentReportRepository = incidentReportRepository;
        this.userRepository = userRepository;
    }

    private String normalizeMucDo(String raw) {
        if (raw == null) return "Trung binh";
        String lower = raw.trim().toLowerCase();
        if (lower.contains("bach") || lower.contains("bách") || lower.equals("high") || lower.equals("urgent")) {
            return "Cap bach";
        }
        if (lower.contains("binh") || lower.contains("bình") || lower.equals("medium") || lower.equals("moderate")) {
            return "Trung binh";
        }
        if (lower.contains("thap") || lower.contains("thấp") || lower.equals("low")) {
            return "Thap";
        }
        return "Trung binh";
    }

    private String normalizeTrangThai(String raw) {
        if (raw == null) return "Cho xu ly";
        String lower = raw.trim().toLowerCase();
        if (lower.contains("dang") || lower.contains("đang") || lower.contains("progress") || lower.contains("duyet")) {
            return "Dang xu ly";
        }
        if (lower.contains("da ") || lower.contains("đã") || lower.contains("resolved") || lower.contains("done") || lower.equals("da xu ly")) {
            return "Da xu ly";
        }
        return "Cho xu ly";
    }

    @Override
    @Transactional
    public IncidentReportEntity createIncident(IncidentReportDto dto) {
        IncidentReportEntity entity = new IncidentReportEntity();

        // Ensure user exists or fallback to existing system user
        Long userId = dto.getUserId();
        if (userId == null || !userRepository.existsById(userId)) {
            List<UserEntity> allUsers = userRepository.findAll();
            if (!allUsers.isEmpty()) {
                userId = allUsers.get(0).getId();
            } else {
                UserEntity systemUser = userRepository.findByEmail("system@protectingyourlife.com")
                        .orElseGet(() -> {
                            UserEntity u = new UserEntity();
                            u.setHovaten("Cộng đồng An Toàn");
                            u.setEmail("system@protectingyourlife.com");
                            u.setRole("user");
                            return userRepository.save(u);
                        });
                userId = systemUser.getId();
            }
        }
        entity.setUserId(userId);

        entity.setLoaiSuCo(dto.getLoaiSuCo());
        String normalizedMucDo = normalizeMucDo(dto.getMucDo());
        entity.setMucDo(normalizedMucDo);
        entity.setTieuDe(dto.getTieuDe());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setDiaChi(dto.getDiaChi());
        entity.setMoTaChiTiet(dto.getMoTaChiTiet());
        entity.setImageUrl(dto.getImageUrl());

        // Rule: Sự cố cấp bách sẽ được duyệt ngay (chuyển sang 'Dang xu ly')
        if ("Cap bach".equals(normalizedMucDo)) {
            entity.setTrangThai("Dang xu ly");
        } else if (dto.getTrangThai() != null && !dto.getTrangThai().isBlank()) {
            entity.setTrangThai(normalizeTrangThai(dto.getTrangThai()));
        } else {
            entity.setTrangThai("Cho xu ly");
        }

        return incidentReportRepository.save(entity);
    }

    @Override
    public List<IncidentReportEntity> getAllIncidents() {
        return incidentReportRepository.findAllByOrderByNgayTaoDesc();
    }

    @Override
    public IncidentReportEntity getIncidentById(Long id) {
        return incidentReportRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sự cố với id: " + id));
    }

    @Override
    public List<IncidentReportEntity> getIncidentsByUserId(Long userId) {
        return incidentReportRepository.findByUserIdOrderByNgayTaoDesc(userId);
    }

    @Override
    @Transactional
    public IncidentReportEntity updateStatus(Long id, String status) {
        IncidentReportEntity entity = getIncidentById(id);
        entity.setTrangThai(normalizeTrangThai(status));
        return incidentReportRepository.save(entity);
    }

    @Override
    @Transactional
    public IncidentReportEntity approveIncident(Long id) {
        IncidentReportEntity entity = getIncidentById(id);
        // Duyệt ngay: đưa trạng thái về "Dang xu ly"
        entity.setTrangThai("Dang xu ly");
        return incidentReportRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteIncident(Long id) {
        incidentReportRepository.deleteById(id);
    }

    @Override
    public IncidentStatsDto getStats() {
        long total = incidentReportRepository.count();
        long choXuLy = incidentReportRepository.countByTrangThai("Cho xu ly");
        long dangXuLy = incidentReportRepository.countByTrangThai("Dang xu ly");
        long daXuLy = incidentReportRepository.countByTrangThai("Da xu ly");
        long capBach = incidentReportRepository.countByMucDo("Cap bach");

        return new IncidentStatsDto(total, choXuLy, dangXuLy, daXuLy, capBach);
    }
}
