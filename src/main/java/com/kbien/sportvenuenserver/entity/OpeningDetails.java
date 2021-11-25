package com.kbien.sportvenuenserver.entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningDetails {
    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;
    @CsvBindByPosition(position = 0)
    String dateFrom;
    @CsvBindByPosition(position = 1)
    String dateTo;
    @CsvBindByPosition(position = 2)
    String mondayFrom;
    @CsvBindByPosition(position = 3)
    String mondayTo;
    @CsvBindByPosition(position = 4)
    String tuesdayFrom;
    @CsvBindByPosition(position = 5)
    String tuesdayTo;
    @CsvBindByPosition(position = 6)
    String wednesdayFrom;
    @CsvBindByPosition(position = 7)
    String wednesdayTo;
    @CsvBindByPosition(position = 8)
    String thursdayFrom;
    @CsvBindByPosition(position = 9)
    String thursdayTo;
    @CsvBindByPosition(position = 10)
    String fridayFrom;
    @CsvBindByPosition(position = 11)
    String fridayTo;
    @CsvBindByPosition(position = 12)
    String saturdayFrom;
    @CsvBindByPosition(position = 13)
    String saturdayTo;
    @CsvBindByPosition(position = 14)
    String sundayFrom;
    @CsvBindByPosition(position = 15)
    String sundayTo;
    @CsvBindByPosition(position = 16)
    String mondayPrice;
    @CsvBindByPosition(position = 17)
    String tuesdayPrice;
    @CsvBindByPosition(position = 18)
    String wednesdayPrice;
    @CsvBindByPosition(position = 19)
    String thursdayPrice;
    @CsvBindByPosition(position = 20)
    String fridayPrice;
    @CsvBindByPosition(position = 21)
    String saturdayPrice;
    @CsvBindByPosition(position = 22)
    String sundayPrice;
}
