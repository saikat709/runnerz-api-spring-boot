package online.saikat.runnerz.run;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface JdbcDataRunRepository extends CrudRepository<Run, Integer> { }