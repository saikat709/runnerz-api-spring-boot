package online.saikat.runnerz;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import online.saikat.runnerz.user.User;
import online.saikat.runnerz.user.UserHttpClient;
import online.saikat.runnerz.user.UserRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import online.saikat.runnerz.run.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class RunnerzApplication {

    //private final WelcomeMessage welcomeMessage;

    RunnerzApplication(WelcomeMessage welcomeMessage) {
		 //this.welcomeMessage = welcomeMessage;
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RunnerzApplication.class, args);

		WelcomeMessage welcome = ( WelcomeMessage ) context.getBean("welcomeMessage");
		System.out.println(welcome.getWelcomeMessage());
	}

	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner runner(JdbcRunRepository jdbcRunRepository){
		// this runs after the application is started
		return args -> {
			 Run run = new Run(99, "First Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), 20, Location.INDOOR );
			 Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(run.toString());
			// jdbcRunRepository.create(run);
		};
	}

	@Bean
	WelcomeMessage welcomeBean(){
		return new WelcomeMessage();
	}


	@Bean
	CommandLineRunner checkRestOrHttpClient(UserRestClient userRestClient, UserHttpClient httpClient){
		return args -> {

			List<User> usersUsingRest = userRestClient.findAll();
			System.out.println(usersUsingRest);

			List<User> usersUsingHttp = userHttpClient().findAll();
			System.out.println(usersUsingHttp);

		};
	}

}
