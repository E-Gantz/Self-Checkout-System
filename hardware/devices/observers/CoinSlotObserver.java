package hardware.devices.observers;

import hardware.devices.CoinSlot;

/**
 * Observes events emanating from a coin slot.
 */
public interface CoinSlotObserver extends AbstractDeviceObserver {
	/**
	 * An event announcing that a coin has been inserted.
	 * 
	 * @param slot
	 *            The device on which the event occurred.
	 */
	void coinInserted(CoinSlot slot);
}
