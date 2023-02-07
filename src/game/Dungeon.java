package game;

import java.util.InputMismatchException;
import java.util.Random;
import items.BasicItem;
import items.CritKnife;
import items.HardskinShroom;
import items.HealthCrystal;
import items.Potion;
import items.SharpeningStone;
import dungeonRooms.Floor;

/**
 * Dungeon class holds everything that happens within the dungeon. Consists of
 * the 'State' method that gives the option of moving around the dungeon or
 * opening the player's inventory.
 * 
 * @author eriks
 *
 */
public class Dungeon {

	private PlayerInfo player;

	private EnemyEncounter enemies;

	private PetCat cat;

	private Floor level;

	public Dungeon(PlayerInfo playa, int numMaps) {
//		dungeon = dunType;
		player = playa;
		level = new Floor(numMaps);
		cat = new PetCat();
		enemies = new EnemyEncounter();
//		randy = new Random();		
	}

	/**
	 * This is the main feedback loop for traveling around the dungeon. It starts
	 * with an option to either move or open your inventory. after moving, it will
	 * randomly select an event that occurs. That event could be going into battle,
	 * finding a treasure chest, or going into a special room
	 * 
	 */
	public void state() {

		while (player.getHealth() > 0 && !player.hasBeatGame()) {
			// while player HP is greater than 0
			System.out.println("");
			System.out.println("");
			System.out.println("All possibilities await. What do you do?");
			System.out.println("Current Position: " + level.getPosition().getLocation());
			System.out.println("[1] Move, [2] Open inventory, [3] Rest");

			try {
				int nextOption = Startup.getScan().nextInt();
				System.out.println("----------------------");
				// modding by two ensures that if you type in a number, you will always do an
				// option.
				if (nextOption > 3)
					throw new InputMismatchException();

				if (nextOption == 1) {
					System.out.println("You look around the room for paths forward.");

					level.move(player, cat, enemies);
					// turnsSinceRest++;

//					System.out.println("New position: " + level.getPosition().getLocation());
				}

				else if (nextOption == 2) {
					player.Inventory(player, level);
				} else if (nextOption == 3) {
					// rest
					// if you actually rested then set the turn counter to zero
					// rest returns true if you actually rested.
					if (level.rest(player, cat, enemies, player.turnsSinceRest()))
						player.rested();

				}

			} catch (InputMismatchException e) {
				System.out.println("Wrong input, silly. Try that one again.");
				System.out.println("");
				Startup.getScan().nextLine();
			}

		}
	}

	/**
	 * Static method for giving the player a range of items.
	 * 
	 * @param player The player recieving the item
	 * @param repeat How many items the player wants to recieve
	 * @param find   The string printed for when the player finds the treasure chest
	 * @param flavor true if you want extra flavor text to be printed.
	 */
	public static void treasureRoom(PlayerInfo player, int repeat, String find, boolean flavor) {
		// add in critknife and dowsing stick later. I don't want to deal with it right
		// now~~
		BasicItem[] list = new BasicItem[] { new Potion(), new HardskinShroom(), new SharpeningStone(), new CritKnife(),
				new HealthCrystal() };
		Random randy = new Random();
		System.out.println(find);

		if (repeat > 1) {
			String out = "You find: \n";
			for (int i = 0; i < repeat; i++) {
				BasicItem add = list[randy.nextInt(list.length)];
				player.addItem(add);
				out += add.getName() + "\n";
			}
			System.out.println(out);
		} else {
			BasicItem add = list[randy.nextInt(list.length)];
			player.addItem(add);
			add.printTreasure(flavor);
		}

	}

}
