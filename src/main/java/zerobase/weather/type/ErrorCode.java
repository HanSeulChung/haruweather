package zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum ErrorCode {
    INVALID_DATE_FORMAT("유효하지 않은 날짜 형식입니다."),
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    DIARY_NOT_FOUND("해당 날짜의 다이어리가 없습니다."),
    ID_NOT_FOUND("해당 아이디의 다이어리가 없습니다."),
    ID_DATE_UN_MATCH("해당 아이디의 날짜와 입력된 날짜가 맞지 않습니다."),
    TOO_FUTURE_DATE("너무 미래의 날짜입니다."),
    ENDDATE_MUST_AFTER_THAN_STARTDATE("조회시 끝나는 날짜는 시작하는 날짜보다 최소 하루는 지나야합니다."),
    TOO_PAST_DATE("너무 과거의 날짜입니다.");

    private String description;
}
