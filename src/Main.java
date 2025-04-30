import Enemies.Beast;
import Events.SearchArea;
import Locations.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.gameStart();
        SearchArea area = new SearchArea(game.getPlayer(), 3, 3, 1, 2, 1, 0);
        area.search();
        System.out.println();
        System.out.println("You, a wandering traveler, notice a small town in the distance and begin to approach");
        Thread.sleep((1000));
        System.out.println();
        System.out.println();
        System.out.println("A wolf jumps out and attacks!");
        System.out.println();
        Thread.sleep(1000);
        game.startFight(new Beast("Wolf", 12, 2, 90));
        System.out.println();
        System.out.println("Whew! You now move towards the town");
        game.enterLocation(LocationRegistry.get("The Clearing Outpost"));
    }
}