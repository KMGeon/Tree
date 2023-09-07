package dev.test.aswemake.domain.controller.dto.request.product;

import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NotEmpty(message = "제품 제목을 입력하세요.")
    private String name;

    @NotNull(message = "가격을 입력하세요.")
    @Positive(message = "가격은 양수여야 합니다.")
    private Integer price;

    @NotNull(message = "제품 수량을 입력하세요.")
    @Positive(message = "제품 수량은 양수여야 합니다.")
    private Integer productQuantity;

    @NotNull(message = "상품의 전략을 선택하세요. TOTAL, SPECIFIC")
    private ProductStrategy productStrategy;

}
