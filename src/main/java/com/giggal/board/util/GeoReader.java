package com.giggal.board.domain.admin.geoip;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Component
public class GeoReader {
    private static final String DATABASE_CITY_PATH = "C:\\Temp\\test\\GeoLite2-City.mmdb";
    private final DatabaseReader reader;

    public GeoReader() throws IOException {
        File dbFile = new File(DATABASE_CITY_PATH);
        reader = new DatabaseReader.Builder(dbFile).build();
    }

    public CityResponse city(InetAddress ipAddress) {
        CityResponse response = null;

        try {
            response = reader.city(ipAddress);
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}