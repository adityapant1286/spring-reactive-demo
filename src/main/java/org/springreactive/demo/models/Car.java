package org.springreactive.demo.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class Car {
    private final String brand;
    private final String model;
    private final int engineSize;
    private final List<String> features;
}
