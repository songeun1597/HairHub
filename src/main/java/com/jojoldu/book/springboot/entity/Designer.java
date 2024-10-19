package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Designer {
    // 디자이너 정
    @Id //@GeneratedValue 아이디가 int일때 사용
    //디자이너아이디
    private String designerId;
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
    //예약가능시간
    private String workTime;
    //휴무일
    private String holiday;
    //누적예약
    private int accumulativeBookings;
    //별점
    private double rating;
    //직급
    private String position;
    //재방문
    private int revisit;



@ManyToOne
@JoinColumn(name = "salonId") // Salon의 salonId와 매핑
private Salon salon; // 해당 디자이너가 소속된 미용실

@OneToOne
@JoinColumn(name="userId")  //User의 userId와 매핑
private User user;

@OneToMany(mappedBy = "designer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Service> services = new ArrayList<>(); //  Designer에 연결된 서비스 리스트, 초기화 추가

public void addService(Service service) {
    this.services.add(service);
    service.setDesigner(this);
}
}