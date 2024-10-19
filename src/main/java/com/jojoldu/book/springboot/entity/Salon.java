package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Salon {
    //미용실정보
    @Id
    //미용실아이디
    private String salonId;
    //미용실이름
    private String salonName;
    //미용실사진
    private String salonPictureId;
    //미용실소개
    private String salonIntroduce;
    //주소
    private String address;
    //홈페이지주소
    private String homepageUrl;
    //영업시간
    private String businessHours;
    //주차여부
    private String parking;
    //전화번호
    private String tel;


@OneToMany(mappedBy = "salon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Designer> designers; // Salon에 연결된 디자이너 리스트

    public List<Designer> getDesigners() {
        return designers;
    }

    public void addDesigner(Designer designer) {
        this.designers.add(designer);
        designer.setSalon(this);
    }
}