package dungeonRooms;

import java.util.Random;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;
import game.Position;
import game.Startup;

public class Floor {

	private Room[][] floor;

	private Position position;

	private String[][] maps;

	private int mapIndex;

	private Random randy;
	
	

	public Floor(int numMaps) {
		position = new Position(1, 1, map1.length);
		String[][] maplist  = new String[][] {map1, map2, map3, mapMid};
		
		maps = new String[numMaps][];
		for (int i = 0; i < numMaps; i++) {
			maps[i] = maplist[i];
		}
		
		floor = setGrid(maps[0], position);

		mapIndex = 0;
		randy = new Random();
	}

	public Position getPosition() {
		return position;
	}

//	/**
//	 * Gets a random array of strings out of a 2D array of strings
//	 * @param maps 2D array of Strings
//	 * @return Random map from the given 2D array of Strings
//	 */
//	private String[] RandomMap(String[][] maps) {
//		int length = maps.length;
//		Random r = new Random();
//		return maps[r.nextInt(length)];
//	}
//	
	/**
	 * Method for when the player rests.
	 * 
	 * @param p     Given player
	 * @param cat   given cat a player can encounter
	 * @param e     instance of enemies the player can fight
	 * @param turns Number of terms since the last time you rested.
	 * @return return true if the player rested or not
	 */
	public boolean rest(PlayerInfo p, PetCat cat, EnemyEncounter e, int turns) {

		if (turns >= 5) {
			int chance = randy.nextInt(100);
			System.out.println("You set up a small camp to rest for a while.");
			int restore = randy.nextInt(40) + 20;
			p.restoreHealth(restore);
			System.out.println("You restore " + restore + " HP");
			System.out.println("");

			if (chance < 30) {
				System.out.println("Enemies attack you in your sleep!");
				e.BattleRoll(p);
			} else if (chance < 70) {
				cat.sleepingCat(p);
			}
			return true;
		} else {
			int print = turns % 5;
			print = 5 - print;

			System.out.println("You can't rest again for " + print + " turns.");
			return false;
		}

	}

	public void move(PlayerInfo p, PetCat cat, EnemyEncounter e) {
		int yPos = position.getY();
		int xPos = position.getX();
		seeAdjacentRooms();

		System.out.println("[w] North: " + floor[yPos - 1][xPos].description());
		System.out.println("[a] West:  " + floor[yPos][xPos - 1].description());
		System.out.println("[s] South: " + floor[yPos + 1][xPos].description());
		System.out.println("[d] East:  " + floor[yPos][xPos + 1].description());

		String move = Startup.getScan().next();
		System.out.println("----------------------");

		Room nextRoom = null;
//This might be bad code because of the amount of repetition but i really don't care about this case. it's not that bad.
		switch (move) {

		case "w":
			nextRoom = floor[yPos - 1][xPos];
			if (nextRoom.canVisit()) {
				position.moveUp(-1);
				nextRoom.enter(p, cat, e);
				p.increaseTurnsSinceRest();
			} else {
				System.out.println("You can't go there!");
			}
			break;

		case "s":
			nextRoom = floor[yPos + 1][xPos];
			if (nextRoom.canVisit()) {
				position.moveUp(1);
				nextRoom.enter(p, cat, e);
				p.increaseTurnsSinceRest();
			} else {
				System.out.println("You can't go there!");
			}
			break;
		case "a":
			nextRoom = floor[yPos][xPos - 1];
			if (nextRoom.canVisit()) {
				position.moveSide(-1);
				nextRoom.enter(p, cat, e);
				p.increaseTurnsSinceRest();
			} else {
				System.out.println("You can't go there!");
			}
			break;
		case "d":
			nextRoom = floor[yPos][xPos + 1];
			if (nextRoom.canVisit()) {
				position.moveSide(1);
				nextRoom.enter(p, cat, e);
				p.increaseTurnsSinceRest();
			} else {
				System.out.println("You can't go there!");
			}
			break;
		default:
			System.out.println("Invalid move");
			break;
		}

		// after every move, check if you are on a stair tile.
		// If you are on a stair tile, ask if you want to move downwards.
		// The when you go down the last set of stairs you win the game, so
		try {
			if (floor[position.getY()][position.getX()].getClass() == Stairs.class) {
				Stairs stair = (Stairs) floor[position.getY()][position.getX()];
				// System.out.println("You should be here");
				if (stair.moveFloor(p)) {
					System.out.println(
							"You use your boss key to open up the door. The door reveals the stairs behind it. You follow the stairs leading down.");
					floor = setGrid(maps[mapIndex + 1], position);
					mapIndex++;
				}

			}
		} catch (IndexOutOfBoundsException outOfBounds) {
			// TODO player wins right here
			p.beatGame();
			// System.out.println("Congrats! You beat the game!");
		}

	}

	private void seeAdjacentRooms() {
		int yPos = position.getY();
		int xPos = position.getX();
		floor[yPos][xPos].seeRoom();
		floor[yPos - 1][xPos].seeRoom();
		floor[yPos + 1][xPos].seeRoom();
		floor[yPos][xPos + 1].seeRoom();
		floor[yPos][xPos - 1].seeRoom();
	}

	private Room[][] setGrid(String[] map, Position pos) {
		int height = map.length;
		int length = map[0].length();
		Room[][] floor = new Room[height][length];

		Room newRoom = new Wall();
		for (int y = 0; y < height; y++) {
			String row = map[y];

			for (int x = 0; x < length; x++) {
				char c = row.charAt(x);

				if (c == 'x') {
					// set room as wall
					newRoom = new Wall();
				} else if (c == '.') {
					// set room as random room
					newRoom = new RandomRoom();
				} else if (c == '*') {
					// set room as treasure room
					newRoom = new TreasureRoom();
				} else if (c == 't') {
					newRoom = new TreasureRoom();
				} else if (c == '%') {
					// set room as healing room
					newRoom = new HealingRoom();
				}

				else if (c == 's') {
					// set room as start room
					newRoom = new StartRoom();
					pos.setPosition(x, y, height);

				} else if (c == 'b') {
					// set room as boss room
					newRoom = new BossRoom();
				} else if (c == '#') {
					// set room as stairs
					newRoom = new Stairs();
				}

				floor[y][x] = newRoom;

			}
		}

		return floor;
	}

	/**
	 * Prints the current map of the floor. if a room is seen, it will show up on
	 * the map as its ID, otherwise it will be a '?'
	 */
	public void printMap() {
		seeAdjacentRooms();
		int x = position.getX();
		int y = position.getY();

		// Print key\
		System.out.println("Key: x = Wall; ? = Not yet seen room; ! = Current Position; S = Starting Room");
		System.out.println(". = Random Room; * = Treasure Room; % = Healing Room; B = Boss Room; # = Stairs");

		// System.out.println(position.getLocation());

		for (int k = 0; k < floor[0].length; k++) {
			System.out.print('x');
		}
		System.out.println("");

		for (int j = 1; j < floor.length - 1; j++) {
			System.out.print('x');

			for (int i = 1; i < floor[j].length - 1; i++) {

				if (floor[j][i].hasSeenRoom()) {
					if (x == i && y == j)
						System.out.print('!');
					else
						System.out.print(floor[j][i].getRoomID());
				} else
					System.out.print('?');
			}

			System.out.println('x');
		}

		for (int d = 0; d < floor[0].length; d++) {
			System.out.print('x');
		}
		System.out.println("");
	}

	private static final String[] testMap = { 
			"xxxx", 
			"xs#x", 
			"x*bx", 
			"xxxx" };

	private static final String[] brokenMap = { 
			"...", 
			".S.", 
			"..." };

	private static final String[] map1 = { "xxxxxxx", "x#b..%x", "xx*.xxx", "x*x..*x", "x...xxx", "x.*s.*x", "xxxxxxx"

	};

	private static final String[] map2 = { "xxxxxxxxxx", "xs.......x", "x.xx.xxx*x", "x.x*.x%xxx", "x...%x..bx",
			"xx.xxx.x#x", "xx..*..xxx", "Xxxxxxxxxx" };

	private static final String[] map3 = { "xxxxxxxxx", "xx%..x.*x", "x*xx.x.xx", "x...s..xx", "xxxx.x*xx", "xbxx.xxxx",
			"x......#x", "xxxxxxxxx" };

	private static final String[] mapS = { "xxxxx", "xx#xx", "x...x", "x.xbx", "x.%xx", "x...x", "xxx.x", "xsx.x",
			"x...x", "xxtxx", "xxxxx" };

	private static final String[] mapU = { "xxxxxxxxxxx", "x.x.x%x.x.x", "x.........x", "x#x.x.x.xtx", "xx..xbx..xx",
			"xtx..x..xtx", "x.........x", "xxxsx.xtxxx", "xxxxxxxxxxx" };

	private static final String[] mapX = { "xxxxxxx", "xx%.bxx", "xtx.x.x", "x.....x", "x#x.xtx", "xx..sxx",
			"xxxxxxx" };

	private static final String[] mapE7 = { "xxxxxxxxx", "x#.xxx.bx", "x..tx%..x", "x......tx", "xx..x..xx",
			"xxxsxtxxx", "xxxxxxxxx" };

	private static final String[] mapH = { "xxxxxxxxx", "xx.xtx.xx", "xt.....%x", "xx.x.x.xx", "xs...x.bx", "xx.x.x.xx",
			"x.......x", "xxtx#x.xx", "xxxxxxxxx" };

	private static final String[] mapSkull = { "xxxxxxxxx", "xbx.%.x#x", "x.x.x.x.x", "x.......x", "xx.xxx.xx",
			"xx.xsxtxx", "xx.....xx", "xxxxxxxxx" };

	private static final String[] mapMid = { "xxxxxxxxxxxxxxx", "xx.........x.bx", "x...xt.xxx...xx", "x%x..xxx%....tx",
			"xx#x...xx....xx", "xx.tx....x.x..x", "x.......x...x.x", "x...x.x.x.x...x", "xxtxx..s....xtx",
			"xxxxxxxxxxxxxxx"

	};

	private static final String[] mapLarge = { 
			"xxxxxxxxxxxxxxx", 
			"xx.txxxx.....xx", 
			"x..x..b..x.x#.x",
			"x.....xxx..xx.x", 
			"x..x%xt.......x", 
			"x.x.xxx....xt.x", 
			"x.....x...x.x.x", 
			"x.xtx.........x",
			"x.xx...x......x", 
			"x..xs..x..xx..x", 
			"x....x...x%...x", 
			"x...xxx...x..xx", 
			"x.x..x..x...xtx",
			"xtx...........x", 
			"xxxxxxxxxxxxxxx" };

}
