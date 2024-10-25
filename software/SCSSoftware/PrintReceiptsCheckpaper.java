package SCSSoftware;

import java.util.ArrayList;
import java.util.Currency;

import hardware.devices.EmptyException;
import hardware.devices.OverloadException;
import hardware.devices.ReceiptPrinter;
import hardware.devices.SelfCheckoutStation;

/**
 * facilitates the system detecting that the printer is out of paper
 */
public class PrintReceiptsCheckpaper {
	private ReceiptPrinter printer;

	/**
	 * constructor
	 * @param printer
	 * the self checkout system's printer
	 */
	public PrintReceiptsCheckpaper(ReceiptPrinter printer) {
		this.printer = printer;
	}

	/**
	 * @return true if the printer is out of paper
	 */
	public boolean isOutOfPaper(){
		boolean flag = false;
		try {
			printer.print('\t');
		} catch (EmptyException e) {
			// TODO Auto-generated catch block
			// empty
			if(e.toString().contains("no")&&e.toString().contains("paper")){
				flag = true;
			}			
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
		}
		return flag;
	}


}
