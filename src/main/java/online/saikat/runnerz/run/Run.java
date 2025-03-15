package online.saikat.runnerz.run;

import java.time.LocalDateTime;


// Immutable, with cons and getters, toString methods
public record Run(
    Integer id,
    String title,
    LocalDateTime startOn,
    LocalDateTime stopOn,
    Integer miles,
    Location location
) {}
