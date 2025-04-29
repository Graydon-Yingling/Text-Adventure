package Locations;

import Actors.Actor;
import Enemies.Undead;
import Fights.FightSequence;

import java.util.List;

public class StartingForest implements Location {

    boolean hasEntered;

    @Override
    public String getLocationName() {return "The Forest";}

    @Override
    public void enterLocation(Actor player) throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("You travel back into the hear of a now-quiet forest...");
        }else {
            System.out.println();
            System.out.println("Slowly but surely, you make you way into the dense forest...");
            Thread.sleep(1500);
            System.out.println("Twigs snap, leaves rustle, and birds scatter as you continue deeper into the trees and brush");
            Thread.sleep(2000);
            System.out.println();
            System.out.println("Suddenly, a snarling beast, not much different from one you have faced before appears from the shrubbery!");
            FightSequence nextFight = new FightSequence();
            nextFight.fight(player, new Undead("Hound", 25, 1, 4, 80));
            hasEntered = true;
        }

        LocationInteraction goTo = new LocationInteraction();
        goTo.mainInteract(LocationRegistry.get(getLocationName()), player);
    }

    @Override
    public List<String> getAvailableActions() {return List.of("DONE");}
}
