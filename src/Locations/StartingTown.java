package Locations;
import Actors.Actor;

import java.util.List;

public class StartingTown implements Location{

    private boolean hasEntered = false;

    @Override
    public String getLocationName() {return "The Clearing Outpost";}

    @Override
    public void enterLocation(Actor player) throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("You enter The Clearing Outpost...");
        }else {
            System.out.println();
            System.out.println("You enter the western part of a small town just beyond the forest clearing...");
            Thread.sleep(1000);
            System.out.println();
            System.out.println("At first glance, you notice a shop called 'The Broken Toe' and a tavern named 'Tammy's Tavern'");
            this.hasEntered = true;
        }
        LocationInteraction goTo = new LocationInteraction();
        goTo.mainInteract(LocationRegistry.get(getLocationName()), player);
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("Tammy's Tavern", "The Broken Toe", "Forest Entrance");
    }
}