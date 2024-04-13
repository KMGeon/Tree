package dev.test.aswemake.domain.entity.order;

import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    //******************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /********************************* PK가 아닌 필드 *********************************/
    private int totalCost;

    private int deliveryFee = 5000;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAITING;


    /********************************* 연관관계 매핑 *********************************/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    /********************************* public 인터페이스 *********************************/

    public void addMappingMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    /********************************* 생성자 *********************************/

    protected Order(Long id, int totalCost, int deliveryFee, Member member, List<OrderItem> orderItems) {
        this.id = id;
        this.totalCost = totalCost;
        this.deliveryFee = deliveryFee;
        this.member = member;
        this.orderItems = orderItems;
    }

    /********************************* 비즈니스 로직 *********************************/
    public static Order createOrder(Member member, List<OrderItem> orderItems) {
        Order order = new Order();
        order.addMappingMember(member);
        int totalCost = 0;
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
            totalCost += orderItem.getOrderPrice() * orderItem.getProductCount();
        }
        order.setTotalCost(totalCost);
        return order;
    }
}
