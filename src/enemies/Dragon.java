package enemies;

public class Dragon extends BasicBoss {

	public Dragon() {
		super("Dragon", 50, 400, 200);
	}

	@Override
	public String BossIntro() {
		return "You see a mountain of treasure as you enter the room. \n"
				+ "Don't get too excited though, as it is guarded by an angry dragon. \n"
				+ "Fire flares from the dragon's mouth. It looks ready to defend it's treasure. \n"
				+ "Looks like only one of you is making it out alive. \n";
	}

	@Override
	public String BossDeath() {

		return "You deal the final blow, and the dragon roars in agony. \n"
				+ "As you look at the endless treasure you realize that you can't really take it, nor is it worth anything down here. \n"
				+ "You do, however, find a boss key. Hopefully the stairs it opens up isn't too far away.";
	}

	@Override
	public String revisitRoom() {
		return "You can't possibly take all of this treasure. Best leave it alone.";
	}

}
