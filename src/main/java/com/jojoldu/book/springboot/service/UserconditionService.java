package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.UserConditionResponseDto;
import com.jojoldu.book.springboot.entity.UserCondition;
import com.jojoldu.book.springboot.repository.ReservationRepository;
import com.jojoldu.book.springboot.repository.UserConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserconditionService {

    @Autowired
    private UserConditionRepository userConditionRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public UserConditionResponseDto findById(String id){
        Optional<UserCondition> byId = userConditionRepository.findById(id);
        return new UserConditionResponseDto(byId.orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다.")));
    }
}
