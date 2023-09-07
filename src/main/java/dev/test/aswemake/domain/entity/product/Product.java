package dev.test.aswemake.domain.entity.product;

import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.entity.BaseTimeStamp;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.global.exception.product.NotFullYetAboutQuantity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeStamp {

    //******************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /********************************* PK가 아닌 필드 *********************************/
    private String name;

    private int price;

    private int productQuantity;

    /**
     * 상품의 쿠폰 적용 범위를 지정한다.
     * 기본적으로 false로 지정
     * false는 주문 전체에 쿠폰을 사용할 수 있다.
     * true는 특정 상품 한정으로 쿠폰을 사용할 수 있다.
     */
    private boolean couponUseStatus = false;


    /********************************* 연관관계 매핑 *********************************/

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    private List<PriceHistory> priceHistories = new ArrayList<>();


    /********************************* 생성자 *********************************/
    @Builder
    protected Product(String name, int price, int productQuantity, boolean couponUseStatus) {
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
        this.couponUseStatus = couponUseStatus;
    }

    /********************************* 비즈니스 로직 *********************************/
    public void updateProduct(ProductUpdateRequest productUpdateRequest) {
        this.name = productUpdateRequest.getName();
        this.price = productUpdateRequest.getPrice();
    }

    public void isCouponApplicableToProduct() {
        couponUseStatus = true;
    }
    public void declineProductQuantity(int count) {
        int quantity = this.productQuantity - count;
        if (quantity < 0) {
            throw new NotFullYetAboutQuantity(quantity);
        }
        this.productQuantity = quantity;
    }
}
