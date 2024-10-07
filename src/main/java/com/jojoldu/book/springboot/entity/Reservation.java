package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
@Entity
public class Reservation {
    @Id
    private String reservationId;
    private String userToken;
    private LocalDateTime reservationTime;
    private String userCondition;
    private String remittance;
    private String memo;

    @ManyToOne
    @JoinColumn(name="userToken", insertable = false, updatable = false)  //Hibernate는 user_token 열을 삽입하거나 업데이트하지 않고, 외래 키로서만 사용할 수 있음
    private User user;

    @ManyToOne
    @JoinColumn(name="serviceId")
    private Service service;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Review review;  //예약에 대한 리뷰

    // Service ID를 반환하는 메서드 추가
    public String getServiceId() {
        return service != null ? service.getServiceId() : null; // service가 null이 아닐 때 ID 반환
    }

    // 서비스의 디자이너를 가져오는 메서드
    public Designer getDesigner() {
        return service != null ? service.getDesigner() : null;
    }


}