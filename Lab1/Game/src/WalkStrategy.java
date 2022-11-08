import java.util.Objects;

public class WalkStrategy implements TravelStrategy {
    @Override
    public void move(String location){
        System.out.println("The Hero goes to the " + location);
    }
}

