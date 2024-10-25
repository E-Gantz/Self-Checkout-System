package softwareObservers;

import hardware.devices.AbstractDevice;
import hardware.devices.CoinSlot;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.CoinSlotObserver;

import SCSSoftware.CoinRunner;

/**
 * This class inherits from a observer interface
 */
public class CSlotObserver implements CoinSlotObserver {
	CoinRunner coinrunner;

	public CSlotObserver(CoinRunner coinrunner) {
		this.coinrunner = coinrunner;
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
	public void coinInserted(CoinSlot slot) {

	}

}