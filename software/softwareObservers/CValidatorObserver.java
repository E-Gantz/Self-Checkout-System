package softwareObservers;

import java.math.BigDecimal;

import hardware.devices.AbstractDevice;
import hardware.devices.CoinValidator;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.CoinValidatorObserver;

import SCSSoftware.CoinRunner;

public class CValidatorObserver implements CoinValidatorObserver {
	CoinRunner coinrunner;

	public CValidatorObserver(CoinRunner coinrunner) {
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

	/**
	 * This class overwrites validCoinDetected to verify that Coin is of the value
	 * with the validator hardware everytime the observer is called
	 * 
	 * @param validator
	 * @param value
	 */
	@Override
	public void validCoinDetected(CoinValidator validator, BigDecimal value) {
		coinrunner.validCoin(value);
		coinrunner.addValidCoin();
	}

	@Override
	public void invalidCoinDetected(CoinValidator validator) {
		// TODO Auto-generated method stub

	}

}
