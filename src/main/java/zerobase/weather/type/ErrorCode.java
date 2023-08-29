package zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DIARY_NOT_FOUND("해당 날짜의 다이어리가 없습니다."),
    TOO_FUTURE_DATE("너무 미래의 날짜입니다."),
    TOO_PAST_DATE("너무 과거의 날짜입니다.");

    private String description;
}
