package SCSSoftware;

import java.util.HashMap;

import hardware.Card.CardData;
import hardware.IllegalNormalPhaseSimulationException;
import hardware.InvalidArgumentSimulationException;
import hardware.devices.AbstractDevice;
import hardware.devices.CardReader;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.CardReaderObserver;

/**
 * This class handles transactions with memberships within the
 * selfcheckoutstation
 */
public class Membership implements CardReaderObserver {
	private HashMap<String, MemberCard> members;
	private MemberCard memberCard;

	public Membership(HashMap<String, MemberCard> map) {
		this.members = map;
		this.memberCard = null;
	}

	/**
	 * This overwritten method takes in 2 parameters to determine if a card contains
	 * valid member data
	 * 
	 * @param reader
	 * @param data
	 */
	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		if (data.getType() != "Member") {
			throw new IllegalNormalPhaseSimulationException();
		} else {
			this.memberCard = members.get(data.getNumber());
			if (memberCard == null) {
				throw new NullPointerException("Something went wrong with scanning your card.");
			}
		}
	}

	/**
	 * This getter method returns a MemberCard object
	 * 
	 * @return memberCard
	 */
	public MemberCard getMemberCard() {
		return this.memberCard;
	}

	/**
	 * This method takes in a manually entered number to search for the membercard
	 * in the hashamp
	 * 
	 * @param number
	 */
	public void manualEntry(String number) {
		this.memberCard = members.get(number);
		if (memberCard == null) {
			throw new NullPointerException("There is no member with that membership number.");
		}
	}

	@Override
	public void cardSwiped(CardReader reader) {
		// this just announces something was swiped, not sure if its useful
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cardInserted(CardReader reader) {
		// cannot insert member cards
	}

	@Override
	public void cardRemoved(CardReader reader) {
		// member cards cant be inserted
	}

	@Override
	public void cardTapped(CardReader reader) {
		// member cards cant be tapped, they dont have a chip
	}
}