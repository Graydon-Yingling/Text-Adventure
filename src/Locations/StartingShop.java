package Locations;

import java.util.List;

public class StartingShop implements Location{

    boolean hasEntered = false;

    @Override
    public String getLocationName() {return "Broken Toe";}

    @Override
    public void enterLocation() throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("Welcome back to the 'ol Broken Toe, don't forget to watch your step!");
        }else {
            System.out.println();
            System.out.println("You enter a small, scarce shop...");
            System.out.println("The shopkeeper welcomes you: Welcome to my shop, the Broken Toe, grab whatever you need, and watch your step on the way in!");
            this.hasEntered = true;
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("The Clearing Outpost");
    }
}