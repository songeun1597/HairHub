package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @Setter
public class ReservationResponseDto {

    //예약아이디
    private String reservationId;
    //서비스아이디
    private String serviceId;
    //유저고유값
    private String userId;
    //예약시간
    private LocalDateTime timeSlots;
    //예약자 상태
    private String userCondition;
    //송금여부
    private String remittance;
    //예약메모
    private String memo;
    //재방문수
    private int revisitCount;
    //성별
    private String gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;

    private String time;

    public ReservationResponseDto(Reservation reservation) {
            this.reservationId = reservation.getReservationId();
            this.serviceId = reservation.getServiceId();
            this.userId = reservation.getUserId();
            this.timeSlots = reservation.getTimeSlots();
            this.remittance = reservation.getRemittance();
            this.memo = reservation.getMemo();
            this.revisitCount = reservation.getRevisitCount();
            this.gender = reservation.getGender();



    }

}
