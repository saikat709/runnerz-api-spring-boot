package online.saikat.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


// Immutable, with constructor and getters, toString methods, no setters
public record Run(
    
    Integer id,

    @NotEmpty
    String title,

    LocalDateTime startOn,
    LocalDateTime stopOn,

    @Positive
    Integer miles,
    Location location


) {
    public Run {
        if ( stopOn != null && startOn != null && !stopOn.isAfter(startOn) ){
            throw new IllegalArgumentException("Completed before starting?!!");
        }
    }
}
