package com.example.mybatis;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@ConfigurationProperties(prefix = "binlog")
public class BinlogConfiguration{
    /** DB host(ipv4) */
//    @Value("${binlog.host}")
    private String host = "localhost";
    /** DB port */
//    @Value("${binlog.port}")
    private int port = 3306;
    /** DB username */
//    @Value("${binlog.user}")
    private String user = "root";
    /** DB password */
//    @Value("${binlog.password}")
    private String password = "1234";

    /**
     * Tapping into MySQL Replication Stream<br>
     *   - [전제] Replication Slave Privilege
     */
    @Bean
    BinaryLogClient binaryLogClient(){

        BinaryLogClient binaryLogClient = new BinaryLogClient(
                host,
                port,
                user,
                password);

        // 받은 데이터를 BYTE 로 표현
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        binaryLogClient.setEventDeserializer(eventDeserializer);

        return binaryLogClient;
    }
}
