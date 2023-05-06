package com.giggal.board.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeoLocationDto {
    private String subdivisionName;
    private String cityName;
    private String continentName;
    private String countryName;
    private String countryIsoCode;
    private String hostAddress;
}
