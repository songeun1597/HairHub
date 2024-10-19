package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.DesignerResponseDto;
import com.jojoldu.book.springboot.dto.PaginationResult;
import com.jojoldu.book.springboot.dto.SalonResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Salon;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import com.jojoldu.book.springboot.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalonService {

    @Autowired  //Spring이 SalonRepository의 인스턴스를 자동으로 주입하도록 함. 이를통해 데이터베이스와 상호작용
    private SalonRepository salonRepository;
    private static final int RECORDS_PER_PAGE = 20; // 한 페이지에 표시할 디자이너 카드 수
    private static final int PAGE_SIZE = 10; // 페이지 리스트에 표시할 페이지 수

    public SalonResponseDto findById(String id) {
        Optional<Salon> byId = salonRepository.findById(id);

        return new SalonResponseDto(byId.orElseThrow(()->new IllegalArgumentException("미용실을를 찾을 수 없습니다")));
    }

    public List<SalonResponseDto> getSalonList(Pageable pageable) {

        Page<Salon> all = salonRepository.findAll(pageable);
        List<Salon> salons = all.getContent();

        // salon 정보를 담을 'responseDtos' 리스트 생성
        List<SalonResponseDto> responseDtos = new ArrayList<>();

        // salon을 SalonResponseDto로 변환하여 responseDtos에 추가
        for (Salon salon : salons) {
            SalonResponseDto dto = new SalonResponseDto(salon); // Salon 객체를 사용하여 DTO 생성
            responseDtos.add(dto);
        }

        return responseDtos;
        // 디자이너 정보가 담긴 'responseDtos' 리스트를 반환.
    }


    public PaginationResult getPaginatedDesigners(int currentPageNo, int totalRecordCount) {
        int totalPageCount = ((totalRecordCount - 1) / RECORDS_PER_PAGE) + 1;
        int firstPageNoOnPageList = ((currentPageNo - 1) / PAGE_SIZE) * PAGE_SIZE + 1;
        int lastPageNoOnPageList = firstPageNoOnPageList + PAGE_SIZE - 1;
        if (lastPageNoOnPageList > totalPageCount) {
            lastPageNoOnPageList = totalPageCount;
        }
        int firstRecordIndex = (currentPageNo - 1) * RECORDS_PER_PAGE;
        int lastRecordIndex = currentPageNo * RECORDS_PER_PAGE;
        return new PaginationResult(currentPageNo, totalPageCount, firstPageNoOnPageList, lastPageNoOnPageList, firstRecordIndex, lastRecordIndex);
    }
    public long getTotalCount() {
        return salonRepository.count(); // 전체 디자이너 수 반환
    }

}
