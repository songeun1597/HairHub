package com.jojoldu.book.springboot.service;


import com.jojoldu.book.springboot.dto.ServiceResponseDto;

import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.repository.ReservationRepository;
import com.jojoldu.book.springboot.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired  //Spring이 ServiceRepository의 인스턴스를 자동으로 주입하도록 함. 이를통해 데이터베이스와 상호작용
    private ServiceRepository serviceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public ServiceResponseDto findById(String id) {
        Optional<com.jojoldu.book.springboot.entity.Service> byId = serviceRepository.findById(id);
        /*new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new IllegalArgumentException("디자이너를 찾을 수 없습니다");
            }
        }*/
        return new ServiceResponseDto(byId.orElseThrow(() -> new IllegalArgumentException("서비스를 찾을 수 없습니다")));
    }

    public List<com.jojoldu.book.springboot.entity.Service> findAllByIds(List<String> serviceIds) {
        List<String> convertedIds = new ArrayList<>();

        for (String id : serviceIds) {
            try {
                convertedIds.add(String.valueOf(id));
            } catch (NumberFormatException e) {
                // 로그를 남기거나 예외 처리
                System.err.println("Invalid ID: " + id);
            }
        }

        return serviceRepository.findAllById(convertedIds);
    }

    public void save(Reservation reservation) {

        reservationRepository.save(reservation);
    }
}
