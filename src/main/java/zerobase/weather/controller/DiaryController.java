package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.dto.CreateDiary;
import zerobase.weather.dto.DeleteDiaries;
import zerobase.weather.dto.DeleteDiary;
import zerobase.weather.dto.UpdateDiary;
import zerobase.weather.service.DiaryService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation(value = "일기 텍스트와 해당 날짜의 날씨를 이용해서 DB에 일기 저장합니다.", notes = "date 형식은 yyyy-MM-dd이며 text에 저장하고싶은 일기 내용을 작성하세요")
    @PostMapping("/create/diary")

    public CreateDiary.Response createDiary(@ApiParam(value = "일기를 쓰고 저장할 날짜", example = "2023-08-23")
                                            @RequestBody @Valid CreateDiary.Request request) {
        return CreateDiary.Response.from(
                diaryService.createDiary(request.getDate(), request.getText())
        );
    }
    @ApiOperation(value = "선택한 날짜의 일기를 삭제할 수 있습니다.")
    @DeleteMapping("/delete/diary")
    public DeleteDiary.Response deleteDiary(@ApiParam(value = "일기를 삭제할 날짜", example = "2023-08-23")
                                                @RequestBody @Valid DeleteDiary.Request request) {
        diaryService.deleteDiary(request.getId(), request.getDate());
        return DeleteDiary.Response.builder().deleteMessage("삭제되었습니다.").build();
    }

    @ApiOperation(value = "선택한 날짜의 일기를 전체 삭제할 수 있습니다.")
    @DeleteMapping("/delete/diaries")
    public DeleteDiaries.Response deleteDiaries(@ApiParam(value = "일기 전체를 삭제할 날짜", example = "2023-08-23")
                                            @RequestBody @Valid DeleteDiaries.Request request) {
        diaryService.deleteDiaries(request.getDate());
        return DeleteDiaries.Response.builder().deleteMessage("삭제되었습니다.").build();
    }
    @ApiOperation(value = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 날짜", example = "2023-08-23") LocalDate date) {
        return diaryService.readDiary(date);
    }
    @ApiOperation(value = "선택한 기간 중의 모든 일기 데이터들을 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째 날", example = "2023-06-01") LocalDate startDate
            ,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막 날", example = "2023-08-23") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation(value = "선택한 날짜의 일기를 수정할 수 있습니다.")
    @PutMapping("/update/diary")
    public UpdateDiary.Response updateDiary(@RequestBody @Valid UpdateDiary.Request request) {
        return UpdateDiary.Response.from(diaryService.updateDiary(
                request.getId(), request.getDate(), request.getText()));
    }

}
