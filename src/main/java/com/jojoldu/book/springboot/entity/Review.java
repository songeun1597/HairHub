package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter @Entity
public class Review {
    @Id

private String reviewId;
private String reviewPictureId;
private Integer reviewRating;
    private String reviewContent;
    private Integer reviewPrice;
    private Integer num;


    // 예약과 리뷰는 일대일 관계로 수정
    @OneToOne
    @JoinColumn(name="reservationId") // Reservation의 PK가 이 컬럼에 저장됨
    private Reservation reservation;


}