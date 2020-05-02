package com.demo.gateway.route;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author V
 */
@Data
public class TimeBetweenConfig {

    private LocalTime start;
    private LocalTime end;

}
