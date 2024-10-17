package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.dto.*;
import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.Reservation;
import com.jojoldu.book.springboot.entity.Review;
import com.jojoldu.book.springboot.entity.Salon;
import com.jojoldu.book.springboot.repository.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesignerService {

    @Autowired  //Spring이 DesignerRepository의 인스턴스를 자동으로 주입하도록 함. 이를통해 데이터베이스와 상호작용
    private DesignerRepository designerRepository;

    private static final int RECORDS_PER_PAGE = 20; // 한 페이지에 표시할 디자이너 카드 수
    private static final int PAGE_SIZE = 10; // 페이지 리스트에 표시할 페이지 수

    public DesignerResponseDto findById(String id) {
        Optional<Designer> byId = designerRepository.findById(id);

        return new DesignerResponseDto(byId.orElseThrow(()->new IllegalArgumentException("디자이너를 찾을 수 없습니다")));
    }

    public List<DesignerResponseDto> getDesignerList(int page, int itemsPerPage) {
        Pageable pageable = PageRequest.of(page-1, itemsPerPage);
        Page<Designer> all = designerRepository.findAll(pageable);
        List<Designer> designers = all.getContent();

        // 시작 인덱스 계산
//        int offset = (page - 1) * itemsPerPage;
//
//        List<Designer> designers = designerRepository.findAllWithPagination(offset, itemsPerPage);
//        System.out.println(offset +"+++++++++++++++++++++++++++++"+  designers.toString()+"---------------------------------------------------"+ itemsPerPage);

        // 디자이너 정보를 담을 'responseDtos' 리스트 생성
        List<DesignerResponseDto> responseDtos = new ArrayList<>();

            for (Designer designer : designers) {
                // 가져온 디자이너 목록을 하나씩 반복 처리.
                Salon salon = designer.getSalon();
                // 디자이너가 속한 살롱 정보를 가져옴. 살롱은 디자이너 엔티티와 연관되어 있음.
                String salonName = (salon != null) ? salon.getSalonName() : "정보 없음"; // null 체크
                // 살롱 객체가 null이 아닌 경우 살롱 이름을 가져오고, null인 경우 "정보 없음"으로 처리.
                String address = (salon != null) ? salon.getAddress() : "정보 없음"; // null 체크
                // 살롱 객체가 null이 아닌 경우 살롱 주소를 가져오고, null인 경우 "정보 없음"으로 처리.
                responseDtos.add(new DesignerResponseDto(designer));
                // 각 디자이너의 ID, 닉네임, 별점, 살롱 이름, 주소를 담은 'DesignerResponseDto' 객체를 리스트에 추가.
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
        return designerRepository.count(); // 전체 디자이너 수 반환
    }
}

