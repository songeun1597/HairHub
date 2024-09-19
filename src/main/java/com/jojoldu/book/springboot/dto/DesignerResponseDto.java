package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Salon;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class DesignerResponseDto {

    private String designerId;
    //미용실아이디
    private String salonId;
    //디자이너이름
    private String designerNickname;
    //프로필사진
    private String designerPictureId;
    //디자이너메모
    private String designerMemo;
    //디자이너소개
    private String designerIntroduce;
    //snsId
    private String snsId;
    //예약시간-salon table
    private String reservationTime;
    //휴무일
    private String holiday;
    //누적예약
    private String accumulativeBookings;
    //별점
    private String rating;
    //직급
    private String position;

    public DesignerResponseDto(Designer designer) {
            this.designerId = designer.getDesignerId();
            this.salonId = designer.getSalonId();
            this.designerNickname = designer.getDesignerNickname();
            this.designerPictureId = designer.getDesignerPictureId();
            this.designerMemo = designer.getDesignerMemo();
            this.designerIntroduce = designer.getDesignerIntroduce();
            this.snsId = designer.getSnsId();
            this.holiday = designer.getHoliday();
            this.accumulativeBookings = designer.getAccumulativeBookings();
            this.rating = designer.getRating();
            this.position = designer.getPosition();
            this.reservationTime = designer.getReservationTime();


    }
}
