package Locations;

import Actors.Actor;

import java.util.List;

public class StartingForestEntrance implements Location {

    boolean hasEntered = false;

    @Override
    public String getLocationName() {
        return "Forest Entrance";
    }

    @Override
    public void enterLocation(Actor player) throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("You're at the forest entrance'...");
        }else {
            System.out.println();
            System.out.println("You walk out of the Clearing Outpost and end up at the entrance of a seemingly beautiful forest.");
            Thread.sleep(1200);
            System.out.println("There's an erie feeling that accompanies you though...");
            hasEntered = true;
        }

        LocationInteraction goTo = new LocationInteraction();
        int choice = goTo.subInteract(LocationRegistry.get(getLocationName()));
        while (true) {
            if (choice == 1) {
                LocationRegistry.get("The Forest").enterLocation(player);
            }else if (choice == 2) {
                LocationRegistry.get("The Clearing Outpost").enterLocation(player);
                return;
            }
        }
    }

    @Override
    public List<String> getAvailableActions() {return List.of("Continue", "Go Back");}
}
