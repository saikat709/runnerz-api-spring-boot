package online.saikat.runnerz.run;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

//@Repository
public interface JdbcDataRunRepository extends CrudRepository<Run, Integer> {

    @Query("select * from run where miles = :miles")
    List<Run> findAllByMiles(Integer miles);

    List<Run> findAllByLocation(String location);

}