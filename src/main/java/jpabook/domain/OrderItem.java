//package jpabook.domain;
//
//import org.hibernate.type.OrderedSetType;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "ORDERSITEM")
//public class OrderItem {
//    @Id @GeneratedValue
//    @Column(name = "order_item_id")
//    private  Long id;
//
//    @Column(name = "MEMBER_ID")
//    private Long memberId;
//
//    private LocalDateTime orderDate;
//
//    @Enumerated(EnumType.STRING)
//    private OderStatus status;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(Long memberId) {
//        this.memberId = memberId;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public OderStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(OderStatus status) {
//        this.status = status;
//    }
//}
