package game;

import java.util.Random;

public class PetCat {

	private String catName;

	private boolean seenCat;

	private Random rand;

	public PetCat() {
		seenCat = false;
		catName = "a cat";
		rand = new Random();
	}

	public String getName() {
		return catName;
	}

	/**
	 * To be used when the rat is seen in the dungeon
	 */
	public void seeCat(PlayerInfo p) {
		if (!seenCat) {
			System.out.println("You see " + catName + " prowling through the dungeon. You should give it a name!");
			System.out.print("What will you name it?");
			// Startup.getScan().next();
			catName = Startup.getScan().next();

			System.out.println("Creative name. " + catName + " runs off, continuing their quest in the dungeon.");
			System.out.println("Hopefully we will see " + catName + " again.");
			seenCat = true;
		} else {
			System.out.println("Hey, there's " + catName + "!");
			System.out.println(
					"Seeing " + catName + " make its way through the dungeon fills you with determination! \n");
			getTreasure(p);
		}

	}

	/**
	 * Method to be used when the rat appears when the player is resting
	 */
	public void sleepingCat(PlayerInfo p) {
		if (!seenCat) {
			System.out.println(
					"As you are resting you see a cat crawl up beside the fire you built. You should give it a name!");
			System.out.println("What will you name it?");
			catName = Startup.getScan().next();
			System.out.println("Creative name. " + catName + " runs off after you finish taking a break.");
			seenCat = true;
		} else {
			System.out.println("While you are resting, " + catName + " comes and sits besides your fire.");
			getTreasure(p);
			System.out.println("\nYou pet " + catName
					+ ". After you're done resting, you both continue your quest in the dungeon.");
		}
	}

	private void getTreasure(PlayerInfo p) {
		String s = catName + " brought something for you. What is it?";
		if (rand.nextInt() < 55) {
			Dungeon.treasureRoom(p, 1, s, true);
		}
	}
}
