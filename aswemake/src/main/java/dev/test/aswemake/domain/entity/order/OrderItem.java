package dev.test.aswemake.domain.entity.order;

import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.global.exception.product.NotFullYetAboutQuantity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ORDER_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    //******************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /********************************* PK가 아닌 필드 *********************************/

    private int orderPrice;

    private int productCount;

    /********************************* 연관관계 매핑 *********************************/
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setOrder(Order order) {
        this.order = order;
    }

    /********************************* Builder *********************************/

    @Builder
    protected OrderItem(Long id, int orderPrice, int productCount, Order order, Product product) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.productCount = productCount;
        this.order = order;
        this.product = product;
    }

    /********************************* 비즈니스 로직 *********************************/
    public static OrderItem createOrderItem(Product product, int orderPrice, int count) {
        if (product.getProductQuantity() < count) {
            throw new NotFullYetAboutQuantity(count);
        }
        return OrderItem.builder()
                .product(product)
                .orderPrice(orderPrice)
                .productCount(count)
                .build();
    }


    /********************************* public 인터페이스 *********************************/

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
