package edu.kit.informatik.orlog.entity;

/**
 * This class represents the god favor Thrymr's Theft.
 *
 * @author uxgle
 * @version 1.0
 */
public class ThrymrsTheftFavor implements GodFavorInterface {

	private int level;
	private int levelDeft;
	private int godFavorCost;

	/**
	 * The constructor instantiates a new Thrymr's Theft favor with the given level.
	 *
	 * @param level The given level
	 */
	public ThrymrsTheftFavor(int level) {
		setLevel(level);
	}

	@Override
	public void process(PlayerInterface owner, PlayerInterface target) {
		GodFavorInterface targetGodFavor = target.getGodFavor();
		targetGodFavor.setLevel(targetGodFavor.getLevel() - levelDeft);
	}

	@Override
	public int getPriority() {
		return 3;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		
		if(level < 1 ) {
			this.levelDeft = 0;
			this.godFavorCost = 0;
		}else {
			this.levelDeft = 1 + (level - 1);
			this.godFavorCost = 4 + (3  * (level - 1));
		}
	}

	@Override
	public int getLevel() {
		return level;
	}
	
}
