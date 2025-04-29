package Locations;

import java.util.HashMap;
import java.util.Map;

public class LocationRegistry {
    private static final Map<String, Location> locationData = new HashMap<>();

    static {
        locationData.put("The Clearing Outpost", new StartingTown());
        locationData.put("Tammy's Tavern", new StartingTavern());
        locationData.put("The Broken Toe", new StartingShop());
        locationData.put("Forest Entrance", new StartingForestEntrance());
        locationData.put("The Forest", new StartingForest());
    }

    public static Location get(String name) {return locationData.get(name);}
}
