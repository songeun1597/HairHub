package com.jojoldu.book.springboot.repository;

import com.jojoldu.book.springboot.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    // 특정 디자이너와 서비스에 대한 예약 횟수 조회
    @Query(value = "SELECT COUNT(*) " +
            "FROM reservation r " +
            "JOIN service s ON r.service_id = s.service_id " +
            "WHERE s.designer_id = :designerId" , nativeQuery = true)
    int countReservationsByDesigner(@Param("designerId") String designerId);
}
