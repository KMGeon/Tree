package dev.test.aswemake.domain.entity.order;

import dev.test.aswemake.domain.entity.product.Product;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    //******************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /********************************* PK가 아닌 필드 *********************************/

    private int orderPrice;

    private int productCount;

    /********************************* 연관관계 매핑 *********************************/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setOrder(Order order) {
        this.order = order;
    }

    /********************************* 생성자 *********************************/
    protected OrderItem() {
    }

    @Builder
    public OrderItem(Long id, int orderPrice, int productCount, Order order, Product product) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.productCount = productCount;
        this.order = order;
        this.product = product;
    }

    /********************************* 비즈니스 로직 *********************************/
    public static OrderItem createOrderItem(Product product, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .orderPrice(orderPrice)
                .productCount(count)
                .build();

        product.removeProductQuantity(count);
        return orderItem;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
