package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {
    //회원정보
    @Id @GeneratedValue
    //유저고유값
    private UUID userToken;
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


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>(); // User에 연결된 디자이너 리스트


    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setUser(this);
    }

}

