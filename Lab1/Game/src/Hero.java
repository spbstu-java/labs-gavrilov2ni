import java.util.Objects;

public class Hero {
    TravelStrategy travelStrategy;
    private String location;

    public Hero(){
        this.location = "castle";
        this.setTravelStrategy(new WalkStrategy());
        System.out.println("A hero has appeared in the castle");
    }

    public String getLocation() {
        return location;
    }

    public void move(){
        this.location = (Objects.equals(this.location, "castle")) ? "forest" : "castle";
        travelStrategy.move(this.location);
    }

    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }
}
