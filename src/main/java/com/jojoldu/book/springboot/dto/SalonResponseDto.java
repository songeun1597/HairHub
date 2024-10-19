package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Salon;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SalonResponseDto {

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

    private List<DesignerResponseDto> designers;



    public SalonResponseDto(Salon salon) {
            this.salonId = salon.getSalonId();
            this.salonName = salon.getSalonName();
            this.salonPictureId = salon.getSalonPictureId();
            this.salonIntroduce = salon.getSalonIntroduce();
            this.address = salon.getAddress();
            this.homepageUrl = salon.getHomepageUrl();
            this.businessHours = salon.getBusinessHours();
            this.parking = salon.getParking();
            this.tel = salon.getTel();

        // 디자이너 목록을 DTO로 변환하여 저장
        this.designers = salon.getDesigners().stream()
                .map(DesignerResponseDto::new) // DesignerResponseDto 생성자 호출
                .collect(Collectors.toList());
    }
}
