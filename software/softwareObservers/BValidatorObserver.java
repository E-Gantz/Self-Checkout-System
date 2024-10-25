package softwareObservers;

import java.util.Currency;

import hardware.devices.AbstractDevice;
import hardware.devices.BanknoteValidator;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.BanknoteValidatorObserver;

import SCSSoftware.BanknoteRunner;

/**
 * This class inherits from a observer interface
 */
public class BValidatorObserver implements BanknoteValidatorObserver {

	BanknoteRunner runner;

	public BValidatorObserver(BanknoteRunner runner) {
		this.runner = runner;
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
	 * This class overwrites validbanknoteAdded to verify that banknote is of the
	 * accepted currency & value with the hardware everytime the observer is called
	 * 
	 * @param validator
	 * @param currency
	 * @param value
	 */
	@Override
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
		// A valid banknote is detected
		runner.validNote(currency, value);
	}

	@Override
	public void invalidBanknoteDetected(BanknoteValidator validator) {
		// TODO Auto-generated method stub

	}

}
