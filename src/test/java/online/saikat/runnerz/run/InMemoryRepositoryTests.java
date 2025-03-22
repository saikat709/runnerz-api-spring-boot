package online.saikat.runnerz.run;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class InMemoryRepositoryTests {
    RunRepository runRepository;

    @BeforeEach
    void setUp(){
        runRepository = new RunRepository();

        Run run1, run2;
        run1 = new Run(1, "title1", LocalDateTime.now().minusHours(1), LocalDateTime.now(), 10, Location.OUTDOOR );
        run2 = new Run(2, "title2", LocalDateTime.now().minusHours(2), LocalDateTime.now(), 15, Location.INDOOR  );

        runRepository.create(run1);
        runRepository.create(run2);
    }

    @Test
    void shouldGetAllRuns(){
        List<Run> runs = runRepository.getAll();
        Assertions.assertEquals(2, runs.size());
    }

    @Test
    void shouldGetById(){
        Optional<Run> run = runRepository.findById(1);
        Assertions.assertFalse(run.isEmpty());
        Assertions.assertEquals("title1", run.get().title());
    }
}
