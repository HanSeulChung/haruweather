package zerobase.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import zerobase.weather.dto.CreateDiary;
import zerobase.weather.dto.DiaryDto;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiaryController.class)
class DiaryControllerTest {
    @MockBean
    private DiaryService diaryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // json을 문자열로 변환해주는 object mapper

    @Test
    void successCreateDiary() throws Exception {
        //given
        given(diaryService.createDiary(any(), anyString()))
                .willReturn(DiaryDto.builder()
                        .id(1)
                        .weather("rain")
                        .temperature(374.15)
                        .text("오늘 정말 재밌었다.")
                        .date(LocalDate.parse("2023-08-30"))
                        .build());
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/create/diary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CreateDiary.Request(LocalDate.of(2023, 8, 30),"오늘 정말 재밌었다.")
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("오늘 정말 재밌었다."))
                .andExpect(jsonPath("$.date").value("2023-08-30"))
                .andDo(print()); // controller에 들어갈 요청이 content안에 들어가야함
    }

//    @Test
//    void successDeleteDiaries() throws Exception {
//        //given
//        given(diaryService.deleteDiary(any()).
//        //when
//
//        //then
//        mockMvc.perform(MockMvcRequestBuilders.post("/create/diary?date="+"2023-08-30")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(
//                                new CreateDiary.Request("오늘 정말 재밌었다.") // 이건 자유롭게써도됨
//                        )))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.text").value("오늘 정말 재밌었다."))
//                .andExpect(jsonPath("$.date").value(LocalDate.parse("2023-08-30")))
//                .andDo(print()); // controller에 들어갈 요청이 content안에 들어가야함
//    }


}