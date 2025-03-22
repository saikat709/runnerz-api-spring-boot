package online.saikat.runnerz.run;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

@JdbcTest
@Import(JdbcRunRepository.class)
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
public class JdbcRunRepositoryTests {

    @Autowired
    JdbcRunRepository jdbcRunRepository;

    @BeforeEach
    void setUp(){
        jdbcRunRepository.create(new Run(
           87,
           "Run 1",
           LocalDateTime.now().minusHours(2),
           LocalDateTime.now().minusHours(1),
           10,
           Location.OUTDOOR
        ));

        jdbcRunRepository.create(new Run(
                88,
                "Run 2",
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1),
                15,
                Location.INDOOR
        ));
    }


    @Test
    void shouldGetAll(){
        List<Run> runs = jdbcRunRepository.findAll();
        Integer cnt = jdbcRunRepository.count();
        Assertions.assertEquals(cnt, runs.size());
    }
}
