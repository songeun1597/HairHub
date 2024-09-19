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
public class User {
    //회원정보
    @Id @GeneratedValue
    //유저고유값
    private String userToken;
    //유저아이디
    private String userId;
    //이메일
    private String email;
    //전화번호
    private String phone;
    //성별
    private String gender;
    //역할
    private String role;
    //포일트
    private String point;

}

