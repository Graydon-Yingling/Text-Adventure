package Locations;

import Actors.Actor;

import java.util.List;

public class StartingForest implements Location {

    @Override
    public String getLocationName() {
        return "The Forest";
    }

    @Override
    public void enterLocation(Actor player) throws InterruptedException {

    }

    @Override
    public List<String> getAvailableActions() {return List.of("The Clearing Outpost");}
}
