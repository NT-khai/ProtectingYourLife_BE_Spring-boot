package com.example.demo.feedback;

import com.example.demo.feedback.dto.FeedbackDto;
import java.util.List;

public interface FeedbackService {
    FeedbackEntity createFeedback(FeedbackDto dto);
    List<FeedbackEntity> getAllFeedbacks();
    void deleteFeedback(Long id);
    long countFeedbacks();
}
