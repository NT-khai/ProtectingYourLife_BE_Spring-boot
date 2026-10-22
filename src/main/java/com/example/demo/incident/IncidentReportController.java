package com.example.demo.incident;

import com.example.demo.incident.dto.IncidentReportDto;
import com.example.demo.incident.dto.IncidentStatsDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/incidents")
public class IncidentReportController {

    private final IncidentReportService incidentReportService;

    public IncidentReportController(IncidentReportService incidentReportService) {
        this.incidentReportService = incidentReportService;
    }

    @PostMapping
    public ResponseEntity<IncidentReportEntity> createIncident(@Valid @RequestBody IncidentReportDto dto) {
        IncidentReportEntity created = incidentReportService.createIncident(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<IncidentReportEntity> getAllIncidents() {
        return incidentReportService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public IncidentReportEntity getIncidentById(@PathVariable Long id) {
        return incidentReportService.getIncidentById(id);
    }

    @GetMapping("/user/{userId}")
    public List<IncidentReportEntity> getIncidentsByUserId(@PathVariable Long userId) {
        return incidentReportService.getIncidentsByUserId(userId);
    }

    @PatchMapping("/{id}/status")
    public IncidentReportEntity updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String status = body.getOrDefault("status", "Cho xu ly");
        return incidentReportService.updateStatus(id, status);
    }

    @PatchMapping("/{id}/approve")
    public IncidentReportEntity approveIncident(@PathVariable Long id) {
        return incidentReportService.approveIncident(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteIncident(@PathVariable Long id) {
        incidentReportService.deleteIncident(id);
        return ResponseEntity.ok(Map.of("success", true, "message", "Đã xóa sự cố"));
    }

    @GetMapping("/stats")
    public IncidentStatsDto getStats() {
        return incidentReportService.getStats();
    }
}
