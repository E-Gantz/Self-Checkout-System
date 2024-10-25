package gui.warningscreens;

import java.util.ArrayList;
import java.util.Currency;

import hardware.devices.EmptyException;
import hardware.devices.OverloadException;
import hardware.devices.ReceiptPrinter;
import hardware.devices.SelfCheckoutStation;

// Station detects that the ink in a receipt printer is low.
public class PrintReceiptsCheckInk {
	private ReceiptPrinter printer;

	public PrintReceiptsCheckInk(ReceiptPrinter printer) {
		this.printer = printer;
	}

	// check the printer status, if empty judge the message
	public boolean isOutOfInk(){
		boolean flag = false;
		try {
			printer.print('\t');
		} catch (EmptyException e) {
			// TODO Auto-generated catch block
			// empty
			if(e.toString().contains("no")&&e.toString().contains("ink")){
				flag = true;
			}			
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
		}
		return flag;
	}


}
