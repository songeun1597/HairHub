package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Reservation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ReservationResponseDto {

    //예약아이디
    private String reservationId;
    //서비스아이디
    private String serviceId;
    //유저고유값
    private String userId;
    //예약시간
    private LocalDateTime reservationTime;
    //예약자 상태
    private String userCondition;
    //송금여부
    private String remittance;
    //예약메모
    private String memo;
    //재방문수
    private int revisitCount;

    public ReservationResponseDto(Reservation reservation) {
            this.reservationId = reservation.getReservationId();
            this.serviceId = reservation.getServiceId();
            this.userId = reservation.getUserId();
            this.reservationTime = reservation.getReservationTime();
            this.remittance = reservation.getRemittance();
            this.memo = reservation.getMemo();
            this.revisitCount = reservation.getRevisitCount();




    }

}
