import java.util.Objects;

public class FlyStrategy implements TravelStrategy {
    @Override
    public void move(String location){
        System.out.println("The Hero flies to the " + location);
    }
}
