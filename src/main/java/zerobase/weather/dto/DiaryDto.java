package zerobase.weather.dto;

import lombok.*;
import zerobase.weather.domain.Diary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryDto {
    private int id;
    private String weather;
    private double temperature;
    private String text;
    private LocalDate date;

    public static DiaryDto fromEntity(Diary diary) {
        return DiaryDto.builder()
                .id(diary.getId())
                .weather(diary.getWeather())
                .temperature(diary.getTemperature())
                .text(diary.getText())
                .date(diary.getDate())
                .build();
    }

}
