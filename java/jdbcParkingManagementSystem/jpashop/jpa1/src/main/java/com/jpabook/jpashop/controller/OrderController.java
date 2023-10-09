package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.OrderSearch;
import com.jpabook.jpashop.service.ItemService;
import com.jpabook.jpashop.service.MemberService;
import com.jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        model.addAttribute(memberService.findMembers());
        model.addAttribute(itemService.findItems());
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId")Long id, @RequestParam("itemId") Long itemId , @RequestParam("count")int count){
        orderService.order(id , itemId , count);
        return "redirect:/orders";
    }

//    @GetMapping("/orders")
//    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch){
//    }
}
