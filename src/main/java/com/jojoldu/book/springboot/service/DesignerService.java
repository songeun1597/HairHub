package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.DesignerResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DesignerService {

    @Autowired  //Spring이 DesignerRepository의 인스턴스를 자동으로 주입하도록 함. 이를통해 데이터베이스와 상호작용
    private DesignerRepository designerRepository;

    public DesignerResponseDto findById(String id) {
        Optional<Designer> byId = designerRepository.findById(id);
        /*new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new IllegalArgumentException("디자이너를 찾을 수 없습니다");
            }
        }*/
        return new DesignerResponseDto(byId.orElseThrow(()->new IllegalArgumentException("디자이너를 찾을 수 없습니다")));
    }
}
