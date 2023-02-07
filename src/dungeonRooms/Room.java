package dungeonRooms;

import java.util.Random;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public abstract class Room {

	private boolean visited;

	private int numVisited;

	private boolean ifCanVisit;

	private Random rand;

	/**
	 * Variable whether or not the player has seen the room. the player sees the
	 * room if they are adjacent to it.
	 */
	private boolean seen;

	public Room(boolean canVisit) {
		visited = false;
		numVisited = 0;
		ifCanVisit = canVisit;
		rand = new Random();
		seen = false;
	}

	/**
	 * Method to call when going into a room. Calls the abstract method inroom
	 * before the room counts as being visited before.
	 * 
	 * @param p given player
	 * @param r given pet rat
	 * @param e given instance of enemy encounter
	 */
	protected void enter(PlayerInfo p, PetCat r, EnemyEncounter e) {
		inRoom(p, r, e);
		visited = true;
		numVisited++;

	}

	public boolean canVisit() {
		return ifCanVisit;
	}

	protected int timesVisited() {
		return numVisited;
	}

	protected boolean hasVisited() {
		return visited;
	}

	/**
	 * The room will give out a number which corresponds to an event that happens
	 * outside of this method.
	 * 
	 * @param p       given player
	 * @param dungeon the instance of a dungeon that the player is in
	 */
	public abstract void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e);

	/**
	 * Gets the description of the room for when the player is moving to another
	 * room.
	 * 
	 * @return the description of the room
	 */
	public abstract String description();

	/**
	 * Gets the character that represents which type of room it is. For using when
	 * opening up the map.
	 * 
	 * @return A char representing the type that the room is.
	 */
	public abstract char getRoomID();

	/**
	 * sets the instance variable "seen" to true.
	 */
	public void seeRoom() {
		seen = true;
	}

	public boolean hasSeenRoom() {
		return seen;
	}

	protected Random getRandom() {
		return rand;
	}

}
