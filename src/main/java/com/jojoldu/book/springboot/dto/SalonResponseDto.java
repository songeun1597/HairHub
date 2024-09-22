package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Salon;
import lombok.Getter;

@Getter
public class SalonResponseDto {

    //미용실아이디
    private String salonId;
    //미용실이름
    private String salonname;
    //미용실사진
    private String salonPictureId;
    //미용실소개
    private String salonIntroduce;
    //주소
    private String address;
    //홈페이지주소
    private String homepageUrl;
    //영업시간
    private String worktime;
    //주차여부
    private String parking;
    //전화번호
    private String tel;


    public SalonResponseDto(Salon salon) {
            this.salonId = salon.getSalonId();
            this.salonname = salon.getSalonname();
            this.salonPictureId = salon.getSalonPictureId();
            this.salonIntroduce = salon.getSalonIntroduce();
            this.address = salon.getAddress();
            this.homepageUrl = salon.getHomepageUrl();
            this.worktime = salon.getWorkTime();
            this.parking = salon.getParking();
            this.tel = salon.getTel();


    }
}
