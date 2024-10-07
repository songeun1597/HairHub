package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.entity.Service;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ServiceResponseDto {

    private String serviceId;
    private String serviceGroup;
    private String serviceName;
    private String thumbnail;
    private int price;
    private String servicePictureId;
    private List<ReservationResponseDto> reservations;

    public ServiceResponseDto(Service service) {
            this.serviceId = service.getServiceId();
            this.serviceGroup = service.getServiceGroup();
            this.serviceName = service.getServiceName();
            this.thumbnail = service.getThumbnail();
            this.price = service.getPrice();
            this.servicePictureId = service.getServicePictureId();

        if(service.getReservations() != null){
            this.reservations = service.getReservations().stream()
                    .map(ReservationResponseDto::new)
                    .collect(Collectors.toList());
        }
    }
}
