package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.UserCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @NoArgsConstructor @Setter
public class UserConditionResponseDto {

    private String conditionId;
    private String gender;
    private String hairLength;
    private String hairCharacter;
    private String state;

    public UserConditionResponseDto(UserCondition userCondition){
        this.conditionId = userCondition.getConditionId();
        this.gender = userCondition.getGender();
        this.hairLength = userCondition.getHairLength();
        this.hairCharacter = userCondition.getHairCharacter();
        this.state = userCondition.getState();
    }
}
