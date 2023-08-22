package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemo() {
        //given
        Memo newMemo = new Memo(10, "this is jpa memo");
        //when
        jdbcMemoRepository.save(newMemo);
        //then
        List<Memo> result = jdbcMemoRepository.findAll();
        assertTrue(result.size() > 0);
    }

    @Test
    void findByIdTEst() {
        //given
        Memo newMemo = new Memo(11, "jpa");
        //when
        Memo memo = jdbcMemoRepository.save(newMemo);
        System.out.println(memo.getId());
        //then
        Optional<Memo> result = jdbcMemoRepository.findById(memo.getId());
        assertEquals(result.get().getText(), "jpa");
    }
}