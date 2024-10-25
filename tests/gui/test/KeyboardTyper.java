package gui.test;

import hardware.devices.AbstractDevice;
import hardware.devices.Keyboard;
import hardware.devices.SupervisionStation;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.KeyboardObserver;

public class KeyboardTyper {
	public class keyboardEyeball implements KeyboardObserver{

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(Keyboard k, char c) {
			typedString = typedString + c;
		}
	}
	
	public String typedString;
	public keyboardEyeball kEyeball;
	
	public void clearTypedString() {
		typedString = "";
	}

	public KeyboardTyper(SupervisionStation svs) {
		clearTypedString();
		svs.keyboard.attach(kEyeball);
	}
	
	public String getTypedString() {
		return typedString;
	}
	
}
