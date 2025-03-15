package online.saikat.runnerz.run;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

// NB: Command Line runner is run after the project is run

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = Logger.getLogger(RunJsonDataLoader.class.toString());

    private final JdbcRunRepository jdbcRunRepository;
    private final ObjectMapper objectMapper;
    
    public RunJsonDataLoader(JdbcRunRepository jdbcRunRepository, ObjectMapper objectMapper){
        this.jdbcRunRepository = jdbcRunRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if( jdbcRunRepository.count() == 0 ){
            try ( InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json") ){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading " + String.valueOf(allRuns.runs().size()) + " runs JSON data and saving to in-memory collection.");
                jdbcRunRepository.saveAll(allRuns.runs());
            } catch ( IOException e ){
                System.out.println(e.getMessage());
                throw new RuntimeException("Failed to read 'data/runs.json' file: " + e.getLocalizedMessage());
            }
        } else {
            log.info("Not loading Runs from json file.");
        }
        
    }
}
