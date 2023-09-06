package dev.test.aswemake.domain.controller.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    private List<OrderCreateListRequest> orderCreateListRequests;
}
