package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.MyUser;
import com.cashwu.javatacocloud.model.TacoOrder;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findTacoOrderByDeliveryZip(String deliveryZip);

    List<TacoOrder> readTacoOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(MyUser myUser,
                                                 PageRequest pageRequest);

    //    @Query("Order o where o.deliveryCity = 'Seattle'")
//    List<TacoOrder> readTacoOrderByDeliveredInSeattle();
}
