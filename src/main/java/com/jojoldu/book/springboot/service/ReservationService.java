package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.ReservationResponseDto;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.User;
import com.jojoldu.book.springboot.entity.UserCondition;
import com.jojoldu.book.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

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

//        String timeString = reservationResponseDto.getTime();
//        if (timeString != null && !timeString.isEmpty()) {
//            reservation.setTimeSlots(LocalDateTime.of(
//                    reservationResponseDto.getDate(),  // 날짜 (LocalDate)
//                    LocalTime.parse(timeString)  // 시간 (String -> LocalTime 변환)
//            ));
//        } else {
//            // 시간 값이 없으면 기본 시간을 설정하거나 에러를 던질 수 있습니다.
//            throw new IllegalArgumentException("예약 시간이 제공되지 않았습니다.");
//        }

        reservation.setTimeSlots(LocalDateTime.of(reservationResponseDto.getDate(), LocalTime.parse(reservationResponseDto.getTime()))); // 예약 시간 설정
        reservation.setGender(reservationResponseDto.getGender()); // 성별 설정

        // 서비스 선택을 통해 서비스 정보를 가져오기
        // List<com.jojoldu.book.springboot.entity.Service> services = serviceService.findAllByIds(serviceIds);
        com.jojoldu.book.springboot.entity.Service byService = serviceRepository.findById(reservationResponseDto.getServiceId()).get();

        //for (com.jojoldu.book.springboot.entity.Service service : services) {
        reservation.setService(byService); // 1:1 관계 설정
        //}


        reservationRepository.save(reservation);
        /*com.jojoldu.book.springboot.entity.Service byService = serviceRepository.findById(reservationResponseDto.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("서비스를 찾을 수 없습니다. ID: " + reservationResponseDto.getServiceId()));
        reservation.setService(byService);

        // 사용자 조건 설정 (UserCondition 저장)
        if (reservationResponseDto.getConditionId() != null) {
            UserCondition userCondition = userConditionRepository.findById(reservationResponseDto.getConditionId())
                    .orElseThrow(() -> new IllegalArgumentException("사용자 조건을 찾을 수 없습니다."));
            reservation.setUserCondition(userCondition);
        }
        // 예약 저장
        Reservation savedReservation = reservationRepository.save(reservation);

        // 저장된 예약의 reservationId 가져오기
        String reservationId = savedReservation.getReservationId();


        // 저장된 데이터를 ReservationResponseDto로 변환해서 반환해야 함
        // 변환 로직 필요 (예: Reservation -> ReservationResponseDto 변환)
        ReservationResponseDto responseDto = new ReservationResponseDto();
        responseDto.setReservationId(reservationId);
        responseDto.setUserId(savedReservation.getUser().getUserId());
        responseDto.setServiceId(savedReservation.getService().getServiceId());
        responseDto.setGender(savedReservation.getGender());
        responseDto.setConditionId(savedReservation.getUserCondition() != null ? savedReservation.getUserCondition().getConditionId() : null);*/

        //return responseDto;

    }

}
