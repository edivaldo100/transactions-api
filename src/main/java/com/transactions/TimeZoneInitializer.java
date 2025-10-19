package com.transactions;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.TimeZone;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TimeZoneInitializer {

    private static final Logger logger = LoggerFactory.getLogger(TimeZoneInitializer.class);

    @Value("${app.timezone:America/New_York}")
    private String appTimeZone;

    @PostConstruct
    public void init() {
        String tz = appTimeZone;
        try {
            ZoneId.of(tz); // valida ID IANA
        } catch (DateTimeException ex) {
            logger.warn("Invalid timezone '{}', falling back to America/New_York", tz);
            tz = "America/New_York";
        }
        TimeZone.setDefault(TimeZone.getTimeZone(tz));
        logger.info("JVM default timezone set to {}", tz);
    }
}
