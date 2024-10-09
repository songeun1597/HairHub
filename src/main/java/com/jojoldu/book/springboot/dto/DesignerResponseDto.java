package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Salon;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    //예약시간
    private String workTime;
    //휴무일
    private String holiday;
    //누적예약
    private int accumulativeBookings;
    //별점
    private double rating;
    //직급
    private String position;

    private List<ServiceResponseDto> services;

    public DesignerResponseDto(Designer designer) {
            this.designerId = designer.getDesignerId();
            this.salonId = (designer.getSalon() != null)?designer.getSalon().getSalonId(): null;

            this.designerNickname = designer.getDesignerNickname();
            this.designerPictureId = designer.getDesignerPictureId();
            this.designerMemo = designer.getDesignerMemo();
            this.designerIntroduce = designer.getDesignerIntroduce();
            this.snsId = designer.getSnsId();
            this.holiday = designer.getHoliday();
            this.accumulativeBookings = designer.getAccumulativeBookings();
            this.rating = designer.getRating();
            this.position = designer.getPosition();
            this.workTime = designer.getWorkTime();


        // 서비스 목록 변환
        if (designer.getServices() != null) {
            this.services = designer.getServices().stream()
                    .map(ServiceResponseDto::new)
                    .collect(Collectors.toList());
        }

    }

}
