package com.example.demo.incident;

import com.example.demo.incident.dto.IncidentReportDto;
import com.example.demo.incident.dto.IncidentStatsDto;
import java.util.List;

public interface IncidentReportService {
    IncidentReportEntity createIncident(IncidentReportDto dto);
    List<IncidentReportEntity> getAllIncidents();
    IncidentReportEntity getIncidentById(Long id);
    List<IncidentReportEntity> getIncidentsByUserId(Long userId);
    IncidentReportEntity updateStatus(Long id, String status);
    IncidentReportEntity approveIncident(Long id);
    void deleteIncident(Long id);
    IncidentStatsDto getStats();
}
