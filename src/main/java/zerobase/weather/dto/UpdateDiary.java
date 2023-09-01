package zerobase.weather.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateDiary {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @ApiModelProperty(example = "21")
        private int id;
        @NotNull
        @ApiModelProperty(example = "2023-09-01")
        private LocalDate date;
        @NotNull
        @ApiModelProperty(example = "나는 오늘 운동을 했다.")
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
