package online.saikat.runnerz;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import online.saikat.runnerz.run.Location;
import online.saikat.runnerz.run.Run;

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
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.MINUTES), 20, Location.INDOOR );
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(run.toString());
		};
	}

}
