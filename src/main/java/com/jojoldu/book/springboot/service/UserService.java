package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.SalonResponseDto;
import com.jojoldu.book.springboot.dto.UserResponseDto;
import com.jojoldu.book.springboot.entity.Salon;
import com.jojoldu.book.springboot.entity.User;
import com.jojoldu.book.springboot.repository.SalonRepository;
import com.jojoldu.book.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto findById(String id) {
        Optional<User> byId = userRepository.findById(id);

        return new UserResponseDto(byId.orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다")));
    }
}
