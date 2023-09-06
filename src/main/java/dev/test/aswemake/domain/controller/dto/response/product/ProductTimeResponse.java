package dev.test.aswemake.domain.controller.dto.response.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTimeResponse {
    private String productName;
    private int productPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime targetDateTime;

    public static ProductTimeResponse of(String name, int productPrice, LocalDateTime targetTime) {
        return ProductTimeResponse.builder()
                .productName(name)
                .productPrice(productPrice)
                .targetDateTime(targetTime)
                .build();
    }

}
