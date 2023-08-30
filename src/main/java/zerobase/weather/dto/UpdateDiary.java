package zerobase.weather.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateDiary {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private int id;
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

        public static UpdateDiary.Response from(DiaryDto diaryDto) {
            return UpdateDiary.Response.builder()
                    .id(diaryDto.getId())
                    .text(diaryDto.getText())
                    .date(diaryDto.getDate())
                    .build();
        }
    }
}
