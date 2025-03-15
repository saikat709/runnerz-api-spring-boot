package online.saikat.runnerz;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {
    public String getWelcomeMessage(){
        return "This is a Welcome message from Other file But in the main package. Outside main package wont work.";
    }
}
