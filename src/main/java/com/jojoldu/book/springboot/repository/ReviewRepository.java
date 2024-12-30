package com.jojoldu.book.springboot.repository;

import com.jojoldu.book.springboot.dto.ReviewResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query("SELECT r FROM Review r JOIN r.reservation res JOIN res.service s JOIN s.designer d")
    List<ReviewResponseDto> findAllReviewsWithDesignerInfo();

//    // 사용자 ID로 리뷰 목록 조회
//    List<Review> findAllByReservation_User_userId(String userId);

    // 사용자 ID로 리뷰 목록 조회
    @Query("SELECT r FROM Review r JOIN r.reservation res JOIN res.user u WHERE u.userId = :userId")
    Page<Review> findAllByUserId(@Param("userId") String userId, Pageable pageable);

    // 사용자 ID로 리뷰 수 조회
    @Query("SELECT COUNT(r) FROM Review r JOIN r.reservation res JOIN res.user u WHERE u.userId = :userId")
    long countByUserId(@Param("userId") String userId);
}


//    @Query("SELECT r.reviewId AS num, " +
//            "res.reservationTime AS reservationDate, " +
//            "u.userId AS userid, " +
//            "r.reviewContent AS reviewcontent, " +
//            "s.serviceName AS servicename, " +
//            "r.reviewPictureId AS thumbnail, " +
//            "s.price AS price, " +
//            "r.reviewRating AS rating, " +
//            "res.revisitCount AS revisiting " +
//            "FROM Review r " +
//            "JOIN r.reservation res " +
//            "JOIN res.service s " +
//            "JOIN s.designer des " +
//            "JOIN res.user u " +
//            "WHERE des.designerId = :designerId")
//    List<Review> findReviewsByDesignerId(@Param("designerId") String designerId);

