package edu.kit.informatik.orlog.entity;

/**
 * This class encapsulates the God's favor Theft item.
 *
 * @author uxgle
 * @version 1.0
 */
public class TheftItem implements ItemInterface{

    private String itemId;
    private int godPowerGenerator ;

	/**
	 * The constructor instantiates a new Theft item with the given id and the amount of god power generation points.
	 *
	 * @param itemId The given id
	 * @param godPowerGenerator The given amount of god power generation points
	 */
    public TheftItem(String itemId, int godPowerGenerator) {
    	this.itemId = itemId;
    	this.godPowerGenerator = godPowerGenerator;
    }
    
	@Override
	public String getItemId() {
		return itemId;
	}
	
	@Override
	public int getGodPowerGenerator() {
		return godPowerGenerator;
	}

	@Override
	public void process(PlayerInterface owner, PlayerInterface target) {
		// TODO Auto-generated method stub
		
	}
	
}
