package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.DesignerResponseDto;
import com.jojoldu.book.springboot.dto.SalonResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Salon;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import com.jojoldu.book.springboot.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalonService {

    @Autowired  //Spring이 SalonRepository의 인스턴스를 자동으로 주입하도록 함. 이를통해 데이터베이스와 상호작용
    private SalonRepository salonRepository;

    public SalonResponseDto findById(String id) {
        Optional<Salon> byId = salonRepository.findById(id);

        return new SalonResponseDto(byId.orElseThrow(()->new IllegalArgumentException("미용실을를 찾을 수 없습니다")));
    }
}
