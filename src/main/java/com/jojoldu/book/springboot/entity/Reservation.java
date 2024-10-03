package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Getter @Setter
@Entity
public class Reservation {
    @Id
    private String reservationId;
    private String serviceId;
    private String userToken;
    private String reservationTime;
    private String userCondition;
    private String remittance;
    private String memo;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

}