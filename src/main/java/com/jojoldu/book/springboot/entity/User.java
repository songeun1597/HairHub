package com.jojoldu.book.springboot.entity;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity @Getter @Setter @NoArgsConstructor  //@NoArgsConstructor는 Lombok 라이브러리에서 제공하는 어노테이션으로, 기본 생성자(파라미터가 없는 생성자)를 자동으로 생성해 줍니다.
public class User extends BaseTimeEntity {
    //회원정보
    @Id
    //유저고유값
    private String userId;
    //이름
    private String name;
    //이메일
    private String email;
    //전화번호
    private String phone;
    //성별
    private String gender;
    //포일트
    private int point;
    //비밀번호
    private String password;
    //역할
    @Enumerated(EnumType.STRING)  //enum 값이 데이터베이스에 문자열 형태로 저장되도록 설정
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String name, String email, String phone, String gender, int point, String password, Role role){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.point = point;
        this.password = password;
        this.role = role;
    }

    //업데이트
    public User update(String name){
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>(); // User에 연결된 디자이너 리스트
//  private List<Favorite> favorites = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setUser(this);
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}

