package SCSSoftware;

import java.util.ArrayList;

import hardware.devices.SelfCheckoutStation;

public class AttendantBlocksStation {
	
	private ArrayList<SelfCheckoutStation> scsList;
	private ArrayList<SelfCheckoutStation> blockList;
	
	public AttendantBlocksStation() {
		this.blockList = new ArrayList<SelfCheckoutStation>();
	}
	
	public void addToBlockList(SelfCheckoutStation scs)
	{
		blockList.add(scs);
	}
	
	public void clearBlockList()
	{
		blockList.clear();
	}
	
	public void blockSCSList()
	{
		blockList.forEach((scs) -> blockSCS(scs));
	}
	
	public void blockAll()
	{
		scsList.forEach((scs) -> blockSCS(scs));
	}
	
	public void unblockAll()
	{
		scsList.forEach((scs) -> unblockSCS(scs));
	}
	
	public void blockSCS(SelfCheckoutStation scs)
	{
		scs.screen.disable();
		scs.baggingArea.disable();
		scs.cardReader.disable();
		scs.mainScanner.disable();
		scs.handheldScanner.disable();
		scs.banknoteInput.disable();
		scs.coinSlot.disable();
	}
	
	public void unblockSCS(SelfCheckoutStation scs)
	{
		scs.screen.enable();
		scs.baggingArea.enable();
		scs.cardReader.enable();
		scs.mainScanner.enable();
		scs.handheldScanner.enable();
		scs.banknoteInput.enable();
		scs.coinSlot.enable();
	}
}
