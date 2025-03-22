package online.saikat.runnerz;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import online.saikat.runnerz.run.*;

@SpringBootApplication
public class RunnerzApplication {

    private final WelcomeMessage welcomeMessage;

    RunnerzApplication(WelcomeMessage welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RunnerzApplication.class, args);

		WelcomeMessage welcome = ( WelcomeMessage ) context.getBean("welcomeMessage");
		System.out.println(welcome.getWelcomeMessage());
	}

	@Bean
	CommandLineRunner runner(JdbcRunRepository jdbcRunRepository){
		// this runs after the application is started
		return args -> {
			 Run run = new Run(99, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.MINUTES), 20, Location.INDOOR );
			 Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(run.toString());
			// jdbcRunRepository.create(run);
		};
	}

}
