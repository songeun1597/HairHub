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
public class Salon {
    //미용실정보
    @Id @GeneratedValue
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
    private String hompageUrl;
    //영업시간
    private String worktime;
    //주차여부
    private String parking;
    //전화번호
    private String tel;
}

