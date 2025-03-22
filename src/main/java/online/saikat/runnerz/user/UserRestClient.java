package online.saikat.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

// https://jsonplaceholder.typicode.com/todos

@Component
public class UserRestClient {
    private final RestClient restClient;


    public UserRestClient(RestClient.Builder builder){
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);

        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .defaultHeader("User-agent", "This is passed to each request.")
                .requestFactory(requestFactory)
                //.requestFactory(new JdkClientHttpRequestFactory())
                //.requestInterceptor( request -> { request.getHeaders().set("User-agent", "Simple intercapter")} )
                .build();
    }

    public List<User> findAll(){
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public User findById(int id){
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }

    // and more

}
