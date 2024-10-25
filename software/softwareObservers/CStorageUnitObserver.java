package softwareObservers;

import hardware.devices.AbstractDevice;
import hardware.devices.CoinStorageUnit;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.CoinStorageUnitObserver;

import SCSSoftware.CoinRunner;

/**
 * This class inherits from a observer interface
 */
public class CStorageUnitObserver implements CoinStorageUnitObserver {
	CoinRunner coinrunner;

	public CStorageUnitObserver(CoinRunner coinrunner) {
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
	public void coinsFull(CoinStorageUnit unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void coinAdded(CoinStorageUnit unit) {
		coinrunner.addValidCoin();

	}

	@Override
	public void coinsLoaded(CoinStorageUnit unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void coinsUnloaded(CoinStorageUnit unit) {
		// TODO Auto-generated method stub

	}

}
