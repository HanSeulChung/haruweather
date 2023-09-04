package zerobase.weather.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class DeleteDiary {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @ApiModelProperty(example = "13")
        private int id;

        @NotNull
        @ApiModelProperty(example = "2023-09-01")
        private LocalDate date;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @ApiModelProperty(example = "해당 날짜(2023-09-01)의 해당 id(13)의 다이어리가 삭제되었습니다.")
        private String deleteMessage;
    }
}
