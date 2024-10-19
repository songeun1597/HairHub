package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewResponseDto {

    private String reviewId;
    private String reviewContent;
    private String reviewPictureId;
    private Integer reviewPrice;
    private Integer reviewRating;
    private Integer num;



//    @Getter
//    @AllArgsConstructor
//    public class ReviewResponseDto {
//        private String reviewId;
//        private String reviewContent;
//        private Integer price;
//        private Integer rating;


    @Setter
    private LocalDateTime reservationDate;
    @Setter
    private String userId;
    @Setter
    private String serviceName;
    @Setter
    private Integer revisiting;
    @Setter
    private String designerNickname;
    @Setter
    private String address;
    @Setter
    private String salonName;
    @Setter
    private String designerId;

    public ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.reviewContent = review.getReviewContent();
        this.reviewPictureId = review.getReviewPictureId() == null ? "" : review.getReviewPictureId();
        this.reviewPrice = review.getReviewPrice();
        this.reviewRating = review.getReviewRating();
        this.num = review.getNum();

        this.designerNickname = review.getReservation().getService().getDesigner().getDesignerNickname(); // 디자이너 이름 추가
        this.designerId = review.getReservation().getService().getDesigner().getDesignerId();
        //this.revisiting = review.getReservation().getRevisitCount() + 1; // revisiting 값을 +1
    }



    }

