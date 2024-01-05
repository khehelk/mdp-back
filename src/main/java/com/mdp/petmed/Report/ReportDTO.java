package com.mdp.petmed.Report;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {
    private Long countOrder;
    private Double totalEarn;
    private Double avgCheck;
    private List<ServiceWithCount> serviceList;

    public ReportDTO(
        Long countOrder, 
        Double totalEarn, 
        Double avgCheck, 
        List<ServiceWithCount> serviceList
    ){
        this.countOrder = countOrder;
        this.totalEarn = totalEarn;
        this.avgCheck = avgCheck;
        this.serviceList = serviceList;
    }
}
