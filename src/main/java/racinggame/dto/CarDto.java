package racinggame.dto;

public class CarDto {
    private final String name;
    private final int location;

    public CarDto(final String name, final int location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }
}
