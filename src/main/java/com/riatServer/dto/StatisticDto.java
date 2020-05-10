package com.riatServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riatServer.domain.ListOfEmployees;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticDto {
    LocalDateTime date;
    String name;

    public StatisticDto toStatisticDto(){
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setDate(date);
        statisticDto.setName(name);
        return statisticDto;
    }

    public static StatisticDto fromStatisticDto(LocalDateTime date, String name){
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setName(name);
        statisticDto.setDate(date);
        return statisticDto;
    }
}
