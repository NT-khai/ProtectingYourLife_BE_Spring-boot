package com.example.demo.incident;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidentReportRepository extends JpaRepository<IncidentReportEntity, Long> {
    List<IncidentReportEntity> findAllByOrderByNgayTaoDesc();
    List<IncidentReportEntity> findByUserIdOrderByNgayTaoDesc(Long userId);
    List<IncidentReportEntity> findByMucDoOrderByNgayTaoDesc(String mucDo);
    List<IncidentReportEntity> findByTrangThaiOrderByNgayTaoDesc(String trangThai);

    long countByTrangThai(String trangThai);
    long countByMucDo(String mucDo);
}
