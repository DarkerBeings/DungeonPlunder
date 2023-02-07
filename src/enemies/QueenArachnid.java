package enemies;

public class QueenArachnid extends BasicBoss {

	public QueenArachnid() {
		super("Queen Arachnid", 25, 175, 75);
	}

	@Override
	public String BossIntro() {
		return "You enter a room entangled in webs. A pair of glowing eyes light up behind you."
				+ "\nYou prepare yourself for a fight.";
	}

	@Override
	public String BossDeath() {
		return "The oversized spider writhers in pain as dies";
	}

	@Override
	public String revisitRoom() {
		return "The carcass of the Queen Arachnid is curled up on the floor. Best leave it alone.";
	}

}
