package online.saikat.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class JdbcRunRepository {
    private final JdbcClient jdbcClient;

    public JdbcRunRepository(JdbcClient jdbcClient){
        // we can receive whatever we need in contructor. Spring will manage it. 
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id" )
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        Integer updated = jdbcClient.sql("INSERT INTO RUN(id, title, start_on, stop_on, miles, location) values(?, ?, ?, ?, ?, ?)")
            .param(1, run.id())
            .param(2, run.title())
            .param(3, run.startOn())
            .param(4, run.stopOn())
            .param(5, run.miles())
            .param(6, run.location().toString())
            .update();
        
        Assert.state(updated == 1, "Failed to create run: " + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.id(), run.title(), run.startOn(), run.stopOn(), run.miles(), run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

}
