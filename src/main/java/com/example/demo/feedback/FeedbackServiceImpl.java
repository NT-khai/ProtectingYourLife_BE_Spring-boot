package com.example.demo.feedback;

import com.example.demo.feedback.dto.FeedbackDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackEntity createFeedback(FeedbackDto dto) {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setHovaten(dto.getHovaten());
        feedback.setEmail(dto.getEmail());
        feedback.setContent(dto.getContent());
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public long countFeedbacks() {
        return feedbackRepository.count();
    }
}
