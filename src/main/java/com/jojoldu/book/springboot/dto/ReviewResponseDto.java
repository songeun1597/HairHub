package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Review;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewResponseDto {

    private String reviewId;
    private String reviewContent;
    private String reviewPictureId;
    private Integer reviewPrice;
    private Integer reviewRating;


    public ReviewResponseDto(Review review) {
            this.reviewId = review.getReviewId();
            this.reviewContent = review.getReviewContent();
            this.reviewPictureId = review.getReviewPictureId();
            this.reviewPrice = review.getReviewPrice();
            this.reviewRating = review.getReviewRating();
    }

}
