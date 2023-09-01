package zerobase.weather.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateDiary {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @ApiModelProperty(example = "2023-09-01")
        private LocalDate date;
        @NotNull
        @ApiModelProperty(example = "오늘 정말 행복했다.")
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
