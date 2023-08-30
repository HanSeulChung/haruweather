package zerobase.weather.dto;

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
        private String deleteMessage;
    }
}
