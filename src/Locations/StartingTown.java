package Locations;

import java.util.List;

public class StartingTown implements Location{

    @Override
    public String getLocationName() {return "The Clearing Outpost";}

    @Override
    public void enterLocation() throws InterruptedException {
        System.out.println();
        System.out.println("You enter the western part of a small town just beyond the forest clearing...");
        Thread.sleep(1000);
        System.out.println();
        System.out.println("At first glance, you notice a shop called 'The Broken Toe', a tavern named 'Tammy's Tavern', and some houses...with unlocked doors");
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of();
    }
}
