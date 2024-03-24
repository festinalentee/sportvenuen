package com.kbien.sportvenuenserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class BookingDto {
    private LocalDate date;
    private Integer timeFrom;
    private Integer timeTo;
    private Double price;
    private Long venueId;
}
