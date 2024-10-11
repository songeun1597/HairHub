package com.jojoldu.book.springboot.service;


import com.jojoldu.book.springboot.dto.ReviewResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.Review;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import com.jojoldu.book.springboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private DesignerRepository designerRepository;

    // 리뷰 ID로 리뷰 조회
    public ReviewResponseDto findById(String id) {
        Optional<Review> byId = reviewRepository.findById(id);
        return new ReviewResponseDto(byId.orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다.")));
    }
    public List<ReviewResponseDto> getReviewsByDesignerId(String id) {
        Optional<Designer> byId = designerRepository.findById(id);
        System.err.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        List<ReviewResponseDto> reviewList = new ArrayList<>();
        List<com.jojoldu.book.springboot.entity.Service> services = byId.get().getServices();
        for(com.jojoldu.book.springboot.entity.Service service: services){
            List<Reservation> reservations = service.getReservations();
            for(Reservation reservation : reservations){
                Review review = reservation.getReview();
                System.out.println("###############################"+review.getReviewContent());
//reviewList.add();
                ReviewResponseDto reviewDto = new ReviewResponseDto(review);
                reviewDto.setReservationDate(reservation.getReservationTime());
                reviewDto.setUserId(reservation.getUserId());
                reviewDto.setServiceName(service.getServiceName());
                reviewDto.setRevisiting(reservation.getRevisitCount());

                reviewList.add(reviewDto);
            }

        }

        return reviewList;
    }
    // 디자이너 ID로 리뷰 목록 조회
    //public List<Review> getReviewsByDesignerId(String designerId) {
       // return reviewRepository.findReviewsByDesignerId(designerId);
    //}
}













