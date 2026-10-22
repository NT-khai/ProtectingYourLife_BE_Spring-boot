package com.example.demo.feedback;

import com.example.demo.feedback.dto.FeedbackDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackEntity> createFeedback(@Valid @RequestBody FeedbackDto dto) {
        FeedbackEntity created = feedbackService.createFeedback(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(Map.of("success", true, "message", "Đã xóa phản hồi"));
    }

    @GetMapping("/count")
    public Map<String, Long> countFeedbacks() {
        return Map.of("count", feedbackService.countFeedbacks());
    }
}
