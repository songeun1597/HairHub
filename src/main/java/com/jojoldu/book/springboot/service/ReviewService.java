package com.jojoldu.book.springboot.service;


import com.jojoldu.book.springboot.dto.ReviewResponseDto;
import com.jojoldu.book.springboot.entity.Review;
import com.jojoldu.book.springboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public ReviewResponseDto findById(String id) {
        Optional<Review> byId = reviewRepository.findById(id);

        return new ReviewResponseDto(byId.orElseThrow(()->new IllegalArgumentException("리뷰를 찾을 수 없습니다.")));
    }
}













