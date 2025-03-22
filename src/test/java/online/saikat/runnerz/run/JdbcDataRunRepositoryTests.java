package online.saikat.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RunController.class)
@Import(JdbcDataRunRepository.class)
public class JdbcDataRunRepositoryTests {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired JdbcDataRunRepository repository;

    private final List<Run> runs = new ArrayList<>();


    @BeforeEach
    void setUp(){
        runs.add(new Run(
                87,
                "Run 1",
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1),
                10,
                Location.OUTDOOR
        ));

        runs.add(new Run(
                88,
                "Run 2",
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1),
                15,
                Location.INDOOR
        ));
    }

    @Test
    void shouldReturnAll() throws Exception {
        when(repository.findAll()).thenReturn(runs);
        mvc.perform(MockMvcRequestBuilders.get("/run"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()", is(runs.size() )));
    }
}
