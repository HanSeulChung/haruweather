package zerobase.weather.dto;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateDiary {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private LocalDate date;
        @NotNull
        private String text;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int id;
        private String text;
        private LocalDate date;

        public static Response from(DiaryDto diaryDto) {
            return Response.builder()
                    .id(diaryDto.getId())
                    .text(diaryDto.getText())
                    .date(diaryDto.getDate())
                    .build();
        }
    }
}
