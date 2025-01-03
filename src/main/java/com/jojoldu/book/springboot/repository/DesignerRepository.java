package com.jojoldu.book.springboot.repository;

import com.jojoldu.book.springboot.dto.DesignerResponseDto;
import com.jojoldu.book.springboot.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignerRepository extends JpaRepository<Designer, String> {
    // 디자이너 목록을 조회하는 메서드 추가
    List<Designer> findAll(); // 기본적으로 모든 디자이너를 가져옴

    // 페이지네이션을 위한 디자이너 목록 조회 메서드
    @Query(value = "SELECT d FROM Designer d ")
    List<Designer> findAllWithPagination(@Param("offset") int offset, @Param("limit") int limit);

    // 별점 순으로 디자이너를 정렬하여 조회
    @Query("SELECT d FROM Designer d ORDER BY d.rating DESC")
    List<Designer> findAllByOrderByRatingDesc();
//    List<Designer> findAll(int offset, int itemsPerPage);

    // 평점 기준으로 상위 8명의 디자이너를 가져오는 쿼리
    @Query("SELECT d FROM Designer d ORDER BY d.rating DESC")
    List<DesignerResponseDto> findTop8ByOrderByRatingDesc(); // 평점 순으로 상위 8명의 디자이너 가져오기


}
