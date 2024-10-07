package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.DesignerResponseDto;
import com.jojoldu.book.springboot.dto.ReservationResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import com.jojoldu.book.springboot.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationResponseDto findById(String id) {
        Optional<Reservation> byId = reservationRepository.findById(id);

        return new ReservationResponseDto(byId.orElseThrow(()->new IllegalArgumentException("예약을 찾을 수 없습니다")));
    }

    public int getReservationCountForDesigner(String designerId) {
        return reservationRepository.countReservationsByDesigner(designerId);
    }
}
