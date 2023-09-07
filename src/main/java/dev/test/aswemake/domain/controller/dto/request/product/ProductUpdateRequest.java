package dev.test.aswemake.domain.controller.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    @NotEmpty(message = "변경 상품의 이름을 작성하세요.")
    private String name;
    @Positive(message = "변경 가격은 양수로 작성을 하세요.")
    private int price;
}
