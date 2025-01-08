package com.jojoldu.book.springboot.entity;

import com.jojoldu.book.springboot.dto.ReservationResponseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "usercondition")
public class UserCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String conditionId;
    private String gender;
    private String hairLength;
    private String hairCharacter;
    private String state;

    @OneToOne(mappedBy = "userCondition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Reservation reservation;


}
