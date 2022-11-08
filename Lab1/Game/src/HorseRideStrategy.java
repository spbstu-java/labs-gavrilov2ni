import java.util.Objects;

public class HorseRideStrategy implements TravelStrategy {
    @Override
    public void move(String location){
        System.out.println("The Hero rides to the " + location + " on a horse");
    }
}

