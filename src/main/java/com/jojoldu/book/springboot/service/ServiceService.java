package com.jojoldu.book.springboot.service;


import com.jojoldu.book.springboot.dto.ServiceResponseDto;

import com.jojoldu.book.springboot.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired  //Spring이 ServiceRepository의 인스턴스를 자동으로 주입하도록 함. 이를통해 데이터베이스와 상호작용
    private ServiceRepository serviceRepository;

    public ServiceResponseDto findById(String id) {
        Optional<com.jojoldu.book.springboot.entity.Service> byId = serviceRepository.findById(id);
        /*new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new IllegalArgumentException("디자이너를 찾을 수 없습니다");
            }
        }*/
        return new ServiceResponseDto(byId.orElseThrow(()->new IllegalArgumentException("서비스를 찾을 수 없습니다")));
    }

}
