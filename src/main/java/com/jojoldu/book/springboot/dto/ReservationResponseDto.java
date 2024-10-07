package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Reservation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReservationResponseDto {

    //예약아이디
    private String reservationId;
    //서비스아이디
    private String serviceId;
    //유저고유값
    private String userToken;
    //예약시간
    private LocalDateTime reservationTime;
    //예약자 상태
    private String userCondition;
    //송금여부
    private String remittance;
    //예약메모
    private String memo;


    public ReservationResponseDto(Reservation reservation) {
            this.reservationId = reservation.getReservationId();
            this.serviceId = reservation.getServiceId();
            this.userToken = reservation.getUserToken();
            this.reservationTime = reservation.getReservationTime();
            this.userCondition = reservation.getUserCondition();
            this.remittance = reservation.getRemittance();
            this.memo = reservation.getMemo();




    }

}
