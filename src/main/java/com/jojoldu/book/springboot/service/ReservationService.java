package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.DesignerResponseDto;
import com.jojoldu.book.springboot.dto.ReservationResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.User;
import com.jojoldu.book.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConditionRepository userConditionRepository;



    public ReservationResponseDto findById(String id) {
        Optional<Reservation> byId = reservationRepository.findById(id);

        return new ReservationResponseDto(byId.orElseThrow(()->new IllegalArgumentException("예약을 찾을 수 없습니다")));
    }

    public int getReservationCountForDesigner(String designerId) {
        return reservationRepository.countReservationsByDesigner(designerId);
    }

    public void save(ReservationResponseDto reservationResponseDto) {

        // 예약 정보 생성
        Reservation reservation = new Reservation();

            User byUser = userRepository.findById(reservationResponseDto.getUserId()).get();
            reservation.setUser(byUser);

        reservation.setTimeSlots(LocalDateTime.of(reservationResponseDto.getDate(), LocalTime.parse(reservationResponseDto.getTime()))); // 예약 시간 설정
        reservation.setGender(reservationResponseDto.getGender()); // 성별 설정

        // 서비스와 예약 관계 설정
        // 서비스 선택을 통해 서비스 정보를 가져오기
        // List<com.jojoldu.book.springboot.entity.Service> services = serviceService.findAllByIds(serviceIds);
        com.jojoldu.book.springboot.entity.Service byService = serviceRepository.findById(reservationResponseDto.getServiceId()).get();

        //for (com.jojoldu.book.springboot.entity.Service service : services) {
            reservation.setService(byService); // 1:1 관계 설정
        //}


        reservationRepository.save(reservation);

    }
}
