package zerobase.weather.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DeleteDiary {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private int id;

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