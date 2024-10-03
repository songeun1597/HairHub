package com.jojoldu.book.springboot.repository;

import com.jojoldu.book.springboot.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, String> {
    // 디자이너 아이디로 서비스를 조회하는 메서드
    List<Service> findByDesigner_DesignerId(String designerId);
    //여기서 findByDesigner_DesignerId는 Service 엔티티에서 designer 필드의 designerId 속성을 기준으로 조회하는 쿼리를 자동으로 생성합니다.
    // findByDesignerId가 작동하지 않는 이유는 JPA가 자동으로 필드를 찾을 수 없기 때문이며, 이렇게 경로를 명시적으로 지정해주면 해결될 수 있습니다.
}
