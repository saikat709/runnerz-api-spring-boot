package online.saikat.runnerz.run;

public class RunNotFoundException extends RuntimeException {
    
    public RunNotFoundException(){
        super("Run not found.");
    }
    
}
