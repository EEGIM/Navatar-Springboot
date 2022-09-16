package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Review.Review;
import duksung.eegim.Navatar.domain.Review.ReviewRepository;
import duksung.eegim.Navatar.domain.Satisfaction.Satisfaction;
import duksung.eegim.Navatar.domain.Satisfaction.SatisfactionRepository;
import duksung.eegim.Navatar.web.dto.ReviewDto;
import duksung.eegim.Navatar.web.dto.SatisfactionReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SatisfactionRepository satisfactionRepository;

    @Transactional
    public Review getReviewByCartNo(Long cartNo){
        return reviewRepository.findByCartNo(cartNo).map(entity-> entity).orElse(null);
    }

    @Transactional
    public Review getReviewByReviewNo(Long reviewNo){
        return reviewRepository.findById(reviewNo).get(); // 예외처리 하기
    }

    @Transactional
    public void writeReview(SatisfactionReviewDto requestDto){

        Review review = reviewRepository.findByCartNo(requestDto.getCartNo())
                .map(entity -> {
                    entity.update(requestDto.getRating(), requestDto.getContent());
                    entity.getSatisfaction().update(requestDto.getWeight(), requestDto.getHeight(), requestDto.getSizeSatisfaction());
                    return entity;
                })
                .orElseGet(()-> {
                    Satisfaction satisfaction = satisfactionRepository.save(requestDto.toSatisfactionDto().toEntity());
                    ReviewDto reviewDto = requestDto.toReviewDto();
                    reviewDto.setSatisfaction(satisfaction);
                    return reviewRepository.save(reviewDto.toEntity());
                });
    }


}
