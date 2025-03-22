package online.saikat.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

// Immutable, with constructor and getters, toString methods, no setters
// works well with jdbc data, but not with jpa data

public record Run(
    @Id Integer id,
    @NotEmpty String title,

    LocalDateTime startOn,
    LocalDateTime stopOn,

    @Positive Integer miles,

    Location location
){
    public Run {
        if ( stopOn != null && startOn != null && !stopOn.isAfter(startOn) ){
            throw new IllegalArgumentException("Completed before starting?!!");
        }
    }
}
