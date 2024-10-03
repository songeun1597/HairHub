package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Service;
import lombok.Getter;

@Getter
public class ServiceResponseDto {

    private String serviceId;
    private String designerId;
    private String serviceGroup;
    private String serviceName;
    private String thumbnail;
    private int price;
    private String servicePictureId;

    public ServiceResponseDto(Service service) {
            this.serviceId = service.getServiceId();
            this.designerId = service.getDesigner() != null ? service.getDesigner().getDesignerId() : null; // 디자이너 ID 가져오기
            this.serviceGroup = service.getServiceGroup();
            this.serviceName = service.getServiceName();
            this.thumbnail = service.getThumbnail();
            this.price = service.getPrice();
            this.servicePictureId = service.getServicePictureId();


    }
}
