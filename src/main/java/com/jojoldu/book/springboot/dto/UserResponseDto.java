package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.User;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class UserResponseDto {

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
    //비밀번호
    private String password;

    private List<ReservationResponseDto> reservations;
    
    public UserResponseDto(User user) {
            this.userToken = user.getUserToken();
            this.userId = user.getUserId();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.gender = user.getGender();
            this.role = String.valueOf(user.getRole());
            this.point = user.getPoint();
            this.password = user.getPassword();
            
        //예약 목록 변환
        if(user.getReservations() != null){
            this.reservations = user.getReservations().stream()
                    .map(ReservationResponseDto::new)
                    .collect(Collectors.toList());

        }
    }

}
