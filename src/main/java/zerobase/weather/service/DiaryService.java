package zerobase.weather.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.WeatherApplication;
import zerobase.weather.domain.DateWeather;
import zerobase.weather.domain.Diary;
import zerobase.weather.dto.DiaryDto;
import zerobase.weather.exception.DiaryException;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;
import zerobase.weather.type.ErrorCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static zerobase.weather.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class DiaryService {
    @Value("${openweathermap.key}")
    private String apiKey;

    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;
    private static final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void saveWeatherDate() {
        logger.info("오늘의 날씨 잘 가져왔음");
        dateWeatherRepository.save(getWeatherFromApi());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public DiaryDto createDiary(LocalDate date, String text){
        // 유효성 검사
        validateDiary(date);
        logger.info("started to create diary");
        // 날씨 데이터 가져오기 (API에서 가져오기 or DB에서 가져오기)
        DateWeather dateWeather = getDateWeather(date);
        // 파싱한 데이터 + 일기 값 우리 db에 넣기
        logger.info("end to create diary");

        return DiaryDto.fromEntity(diaryRepository.save(
                Diary.builder()
                .icon(dateWeather.getIcon())
                .temperature(dateWeather.getTemperature())
                .date(dateWeather.getDate())
                .weather(dateWeather.getWeather())
                .text(text)
                .build()));
    }

    private void validateDiary(LocalDate date) {
        if (!isValidDateFormat(date)) {
            throw new DiaryException(INVALID_DATE_FORMAT);
        }
        if (date.isAfter(LocalDate.ofYearDay(3050, 1))) {
            throw new DiaryException(TOO_FUTURE_DATE);
        }
        if (date.isBefore(LocalDate.ofYearDay(1800, 1))) {
            throw new DiaryException(TOO_PAST_DATE);
        }
    }

    public boolean isValidDateFormat(LocalDate date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 여기서 체크하고 싶은데 아예 들어오질 않는다..
        dateFormat.setLenient(false);
        try {
            dateFormat.parse( String.valueOf(date) );
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private DateWeather getWeatherFromApi() {
        // open weather map 에서 날씨 데이터 가져오기
        String weatherData = getWeatherString();

        // 받아온 날씨 json 파싱하기
        Map<String, Object> parsedWeather = parseWeather(weatherData);
        DateWeather dateWeather = new DateWeather();
        dateWeather.setDate(LocalDate.now());
        dateWeather.setWeather(parsedWeather.get("main").toString());
        dateWeather.setIcon(parsedWeather.get("icon").toString());
        dateWeather.setTemperature((Double) parsedWeather.get("temp"));
        return dateWeather;
    }

    private DateWeather getDateWeather(LocalDate date) {
        List<DateWeather> dateWeatherListFromDB = dateWeatherRepository.findAllByDate(date);
        if (dateWeatherListFromDB.size() == 0) {
            // 새로 api에서 날씨 정보를 가져와야한다.
            // 정책에 따라 유도리있게 진행해야함. 현재 날씨를 가져오거나 날씨없이 일기를 쓰도록
            return getWeatherFromApi();
        } else {
            return dateWeatherListFromDB.get(0);
        }
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiary(LocalDate date) {
        validateDiary(date);
        logger.debug("read diary");
        return diaryRepository.findDiaryByDate(date);
    }
    @Transactional(readOnly = true)
    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
        validateDiaries(startDate, endDate);
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }
    public void validateDiaries(LocalDate startDate, LocalDate endDate) {
        if (!isValidDateFormat(startDate) || !isValidDateFormat(endDate)) {
            throw new DiaryException(INVALID_DATE_FORMAT);
        }
        if (startDate.isAfter(LocalDate.ofYearDay(3050, 1))
                || endDate.isAfter(LocalDate.ofYearDay(3050, 1))) {
            throw new DiaryException(TOO_FUTURE_DATE);
        }
        if (startDate.isBefore(LocalDate.ofYearDay(1800, 1))
                || endDate.isBefore(LocalDate.ofYearDay(1800, 1))) {
            throw new DiaryException(TOO_PAST_DATE);
        }

        if (endDate.isBefore(startDate)) {
            throw new DiaryException(ENDDATE_MUST_AFTER_THAN_STARTDATE);
        }
    }
    public DiaryDto updateDiary(int id, LocalDate date, String text) {
        Diary nowDiaryById = diaryRepository.findById(id)
                .orElseThrow(() -> new DiaryException(ID_NOT_FOUND));

        if(!nowDiaryById.getDate().equals(date)) {
            throw new DiaryException(ID_DATE_UN_MATCH);
        }

        return DiaryDto.fromEntity(diaryRepository.save(
                Diary.builder()
                        .id(nowDiaryById.getId())
                        .icon(nowDiaryById.getIcon())
                        .temperature(nowDiaryById.getTemperature())
                        .date(nowDiaryById.getDate())
                        .weather(nowDiaryById.getWeather())
                        .text(text)
                        .build()));
    }

    public void deleteDiary(int id, LocalDate date) {
        validateDiary(date);
        Diary nowDiary = diaryRepository.findById(id)
                .orElseThrow(() -> new DiaryException(ID_NOT_FOUND));

        diaryRepository.deleteById(id);
    }

    public void deleteDiaries(LocalDate date) {
        validateDiary(date);
        Diary nowDiary = diaryRepository.findByDate(date)
                .orElseThrow(() -> new DiaryException(DIARY_NOT_FOUND));
        diaryRepository.deleteAllByDate(date);
    }
    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    private Map<String, Object> parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        Map<String, Object> resultMap = new HashMap<>();

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));
        return resultMap;
    }
}
