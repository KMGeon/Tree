package dev.test.aswemake.domain.controller.dto.request.product;

import dev.test.aswemake.domain.entity.enums.CouponStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NotEmpty(message = "제품 제목을 입력하세요.")
    private String name;

    private Integer price;

    private Integer productQuantity;

    private CouponStrategy couponStrategy;

    @Nullable
    private String couponUseStatus;

}
