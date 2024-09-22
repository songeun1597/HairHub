package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Designer {
    // 디자이너 정보
    @Id //@GeneratedValue
    //디자이너아이디
    private String designerId;
    //미용실아이디
    private String salonId;
    //유저고유값
    private String userToken;
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
    private String reservationTime;
    //휴무일
    private String holiday;
    //누적예약
    private String accumulativeBookings;
    //별점
    private String rating;
    //직급
    private String position;


//@ManyToOne
//@JoinColumn(name = "salonId") // Salon의 salonId와 매핑
//private Salon salon; // 해당 디자이너가 소속된 미용실

}