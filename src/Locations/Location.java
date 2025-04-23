package Locations;

import Actors.Actor;
import java.util.List;

public interface Location {
    String getLocationName();
    void enterLocation(Actor player) throws InterruptedException;
    List<String> getAvailableActions();
}