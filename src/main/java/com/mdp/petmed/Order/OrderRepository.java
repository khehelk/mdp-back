package com.mdp.petmed.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mdp.petmed.Report.ServiceWithCount;

public interface OrderRepository extends JpaRepository<OrderModel, Long>{
    @Query("SELECT COUNT(o) FROM OrderModel o WHERE o.date BETWEEN :dateFrom AND :dateTo")
    long countOrdersBetweenDate(@Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo);

    @Query("SELECT COALESCE(SUM(o.total), 0) FROM OrderModel o WHERE o.date BETWEEN :dateFrom AND :dateTo")
    double totalEarnBetweenDate(@Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo);

    @Query("SELECT COALESCE(AVG(o.total), 0) FROM OrderModel o WHERE o.date BETWEEN :dateFrom AND :dateTo")
    double averageCheckBetweenDate(@Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo);

    @Query("SELECT os.service, SUM(os.quantity) as totalQuantity " +
        "FROM OrderServiceModel os " +
        "WHERE os.order.date > :dateFrom AND os.order.date < :dateTo " +
        "GROUP BY os.service " +
        "ORDER BY totalQuantity DESC")
    List<Object[]> getMostFrequentServicesBetweenDate(@Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo, Pageable pageable);
}
