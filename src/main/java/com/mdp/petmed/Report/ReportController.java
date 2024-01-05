package com.mdp.petmed.Report;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdp.petmed.Order.OrderService;

@RestController
@RequestMapping({"api/report"})
public class ReportController {

    private final OrderService orderService;

    public ReportController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getReport/{dateFrom}/{dateTo}")
    public ReportDTO getReport(@PathVariable("dateFrom") Long dateFrom,
                               @PathVariable("dateTo") Long dateTo){
        return orderService.getReportOrders(dateFrom, dateTo);
    }
}
