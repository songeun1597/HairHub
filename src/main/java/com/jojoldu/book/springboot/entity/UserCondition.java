package com.jojoldu.book.springboot.entity;

import com.jojoldu.book.springboot.dto.ReservationResponseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class UserCondition {
    @Id
    private String conditionId;
    private String gender;
    private String hairLength;
    private String hairCharacter;
    private String state;

    @OneToOne(mappedBy = "userCondition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Reservation reservation;


}
