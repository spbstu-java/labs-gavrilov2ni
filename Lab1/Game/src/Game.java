import java.util.Objects;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Hero hero = new Hero();

        while(true){
            Scanner in = new Scanner(System.in);
            System.out.println("==========================\n" +
                    "Choose a travel method:\n"+
                    "Walk\n" +
                    "Ride (on a horse)\n" +
                    "Fly\n\n" +
                    "? - Where is the Hero now?\n\n" +
                    "exit\n" +
                    "==========================");
            String method = in.next();

            if (Objects.equals(method, "exit")) {break;}

            TravelStrategy strategy = null;
            switch (method.toLowerCase()){
                case("walk") -> strategy = new WalkStrategy();
                case("ride") -> strategy = new HorseRideStrategy();
                case("fly") -> strategy = new FlyStrategy();
                case("?") -> System.out.println("The Hero is in the " + hero.getLocation() + " now");
                default -> System.out.println("You must write one of the methods above!!!");
            }

            if (!Objects.equals(strategy, null)) {
                hero.setTravelStrategy(strategy);
                hero.move();
            }
        }
    }
}
