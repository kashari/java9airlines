package com.sda.java9.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AircraftDTO {
    private Long id;
    private String make;
    private String model;
    private int capacity;
}
