package com.giggal.board.domain.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GeoLocationDto {
    private String subdivisionName;
    private String cityName;
    private String continentName;
    private String countryName;
    private String countryIsoCode;
    private String hostAddress;
}
