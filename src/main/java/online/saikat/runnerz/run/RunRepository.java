package online.saikat.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    public RunRepository() {
    }

    List<Run> getAll(){
        return runs;
    }

    Optional<Run> findById(Integer id){
        return runs.stream()
            .filter(run->run.id() == id )
            .findFirst();
    }

    void create(Run run){
        runs.add(run);
    }

    void update(Integer id, Run run){
        Optional<Run> existingRun = findById(id);
        if( existingRun.isPresent() ){
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete( Integer id ){
        runs.removeIf( run -> run.id() == id);
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, "First Run",  LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.MINUTES), 20, Location.INDOOR ));
        runs.add(new Run(2, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.MINUTES), 20, Location.INDOOR ));
    }
}
