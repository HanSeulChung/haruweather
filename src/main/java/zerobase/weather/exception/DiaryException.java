package zerobase.weather.exception;

import lombok.*;
import zerobase.weather.type.ErrorCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;

    public DiaryException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
