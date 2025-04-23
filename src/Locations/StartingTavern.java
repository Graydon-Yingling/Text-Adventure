package Locations;

import java.util.List;

public class StartingTavern implements Location{

    boolean hasEntered = false;

    @Override
    public String getLocationName() {return "Tammy's Tavern";}

    @Override
    public void enterLocation() throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("You enter a cozy and welcoming tavern...");
            System.out.println("Welcome back to my tavern, let me know what you would like to do!");
        }else {
            System.out.println();
            System.out.println("You enter a cozy and welcoming tavern...");
            System.out.println("The tavern keeper introduces herself: Good day and welcome to my tavern! My name is Tammy and I've got food and beds, take your pick!");
            this.hasEntered = true;
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("The Clearing Outpost");
    }
}