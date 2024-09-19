package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
public class Review {
    private String reviewId;
    private String serviceId;
    private String userToken;
    private Integer num;
    private LocalDateTime reservationDate;
    private String userid;
    private String reviewcontent;
    private String servicename;
    private String thumbnail;
    private Long price;
    private Double rating;
    private int revisiting;

}