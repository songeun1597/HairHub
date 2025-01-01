package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter @Setter
@Entity
public class Reservation {
    @Id
    private String reservationId = UUID.randomUUID().toString();
    private LocalDateTime timeSlots;
    private String remittance;
    private String memo;
    private int revisitCount;
    private String gender;

    @ManyToOne
    @JoinColumn(name="userId"    )
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="serviceId")
    private Service service;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Review review;  //예약에 대한 리뷰
    //private UserCondition;

    @OneToOne
    @JoinColumn(name="conditionId") // Reservation의 PK가 이 컬럼에 저장됨
    private UserCondition userCondition;


    // Service ID를 반환하는 메서드 추가
    public String getServiceId() {
        return service != null ? service.getServiceId() : null; // service가 null이 아닐 때 ID 반환
    }
    
    public String getUserId(){
        return user != null ? user.getUserId() : null;
    }

    public String getConditionId(){
        return userCondition != null ? userCondition.getConditionId() : null;
    }
}


