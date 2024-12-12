package com.jojoldu.book.springboot.service;


import com.jojoldu.book.springboot.dto.ReviewResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.Review;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import com.jojoldu.book.springboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private DesignerRepository designerRepository;

    private static final int RECORDS_PER_PAGE = 20; // 한 페이지에 표시할 디자이너 카드 수
    private static final int PAGE_SIZE = 10; // 페이지 리스트에 표시할 페이지 수

    // 리뷰 ID로 리뷰 조회
    public ReviewResponseDto findById(String id) {
        Optional<Review> byId = reviewRepository.findById(id);
        return new ReviewResponseDto(byId.orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다.")));
    }

    public List<ReviewResponseDto> getReviewsByDesignerId(String id) {
        Optional<Designer> byId = designerRepository.findById(id);
        List<ReviewResponseDto> reviewList = new ArrayList<>();
        List<com.jojoldu.book.springboot.entity.Service> services = byId.get().getServices();
        for(com.jojoldu.book.springboot.entity.Service service: services){
            List<Reservation> reservations = service.getReservations();
            for(Reservation reservation : reservations){
                Review review = reservation.getReview();
                System.out.println("###############################"+review.getReviewContent());
//reviewList.add();
                ReviewResponseDto reviewDto = new ReviewResponseDto(review);
                reviewDto.setReservationDate(reservation.getTimeSlots());
                reviewDto.setUserId(reservation.getUserId());
                reviewDto.setServiceName(service.getServiceName());
                reviewDto.setRevisiting(reservation.getRevisitCount());
                reviewList.add(reviewDto);
            }
        }
        return reviewList;
    }

    // 모든 리뷰 조회 (디자이너 정보 포함)
    public List<ReviewResponseDto> getAllReviewsWithDesignerInfo(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);  // 모든 리뷰 조회
        return reviews.stream().map(review -> {
            ReviewResponseDto reviewDto = new ReviewResponseDto(review);
            reviewDto.setReservationDate(review.getReservation().getTimeSlots());
            reviewDto.setUserId(review.getReservation().getUserId());
            reviewDto.setServiceName(review.getReservation().getService().getServiceName());
            reviewDto.setRevisiting(review.getReservation().getRevisitCount());
            reviewDto.setAddress(review.getReservation().getService().getDesigner().getSalon().getAddress());
            reviewDto.setSalonName(review.getReservation().getService().getDesigner().getSalon().getSalonName());
            // 디자이너 닉네임 추가
            reviewDto.setDesignerNickname(review.getReservation().getService().getDesigner().getDesignerNickname());
            reviewDto.setDesignerId(review.getReservation().getService().getDesigner().getDesignerId());
            return reviewDto;
        }).collect(Collectors.toList());
    }

    public long getTotalCount() {
        return reviewRepository.count();
    }
    // 디자이너 ID로 리뷰 목록 조회
    //public List<Review> getReviewsByDesignerId(String designerId) {
    // return reviewRepository.findReviewsByDesignerId(designerId);
    //}

}



