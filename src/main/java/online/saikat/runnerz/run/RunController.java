package online.saikat.runnerz.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
public class RunController {
    private final RunRepository runRepository;
    private final JdbcDataRunRepository jpaDataRunRepository;

    public RunController(RunRepository runRepository, JdbcDataRunRepository jpaDataRunRepository){
        this.runRepository = runRepository;
        this.jpaDataRunRepository = jpaDataRunRepository;
    }

    @GetMapping("/run")
    Iterable<Run> findAll(){
        return jpaDataRunRepository.findAll();
    }

    @GetMapping("/run/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = jpaDataRunRepository.findById(id);
        if( run.isEmpty() ){
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/run/")
    void create(@Valid @RequestBody Run run){
        jpaDataRunRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/run/{id}")
    void update( @PathVariable Integer id, @RequestBody Run run ){
         jpaDataRunRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/run/{id}")
    void delete( @PathVariable Integer id ){
        jpaDataRunRepository.deleteById(id);
    }

    @GetMapping("/hello")
    String home(){
        return "This is home";
    }
}
