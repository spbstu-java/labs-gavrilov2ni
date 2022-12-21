import java.util.Objects;

@MultipleCallAccess
public class Address {
    private String street;
    private int house;
    private int apartment;

    public Address(String street, int house, int apartment) {
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    @MultipleCall(2)
    private void printAddress(){
        System.out.println(this.street + " st, " + this.house + ", " + this.apartment);
    };
    @MultipleCall(3)
    private void selectNextApart() {
        ++this.apartment;
        System.out.println("Apartment changed!");
    }

    @MultipleCall
    protected void printIncompleteAddress() {
        System.out.println(this.street + " st, " + this.house);
    }
    @MultipleCall(5)
    protected void selectNextHouse() {
        ++this.house;
        System.out.println("House changed!");
    }

    @MultipleCall(0)
    public void printStreet() {
        System.out.println(this.street + " st");
    }
    @MultipleCall(6)
    public void selectNextStreet() {
        if (Objects.equals(this.street, "Pushkin")) {
            this.street = "Lenin";
        } else {
            this.street = "Pushkin";
        }
    }
}
