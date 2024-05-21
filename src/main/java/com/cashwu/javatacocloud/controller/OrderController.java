package com.cashwu.javatacocloud.controller;

import com.cashwu.javatacocloud.bean.OrderProps;
import com.cashwu.javatacocloud.model.MyUser;
import com.cashwu.javatacocloud.model.TacoOrder;
import com.cashwu.javatacocloud.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProps orderProps;

    public OrderController(OrderRepository orderRepository,
                           OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal MyUser myUser, Model model) {

        PageRequest pageRequest = PageRequest.of(0,
                                                 orderProps.getPageSize());

        List<TacoOrder> orders = orderRepository.findByUserOrderByPlacedAtDesc(myUser, pageRequest);

        model.addAttribute("orders",
                           orders);
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    //  @PostMapping
    //  public String processOrder(TacoOrder order,
    //                             SessionStatus sessionStatus) {
    //    log.info("Order submitted: {}", order);
    //    sessionStatus.setComplete();
    //
    //    return "redirect:/";
    //  }

    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal MyUser user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);



//        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
