package zerobase.weather.dto;

import lombok.*;
import zerobase.weather.type.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private ErrorCode errorCode;
    private String ErrorMessage;
}

