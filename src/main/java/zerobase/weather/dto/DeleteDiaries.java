package zerobase.weather.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DeleteDiaries {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private LocalDate date;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @ApiModelProperty(example = "해당 날짜(2023-08-23)의 다이어리들이 모두 삭제되었습니다.")
        private String deleteMessage;
    }
}
