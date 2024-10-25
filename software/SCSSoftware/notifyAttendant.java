package SCSSoftware;

import hardware.devices.BanknoteDispenser;
import hardware.devices.BanknoteStorageUnit;
import hardware.devices.CoinDispenser;
import hardware.devices.CoinStorageUnit;
import hardware.devices.ElectronicScale;
import hardware.devices.OverloadException;
import hardware.devices.ReceiptPrinter;

import softwareObservers.notifyAttendantObserver;

public class notifyAttendant {
	
//------------------------ INITIALIZED VARIABLES ------------------//
	//Observer Objects.
	private notifyAttendantObserver nAttendantObserver;
	
	//Class Objects.
	private ElectronicScale electronicScale;
	private ReceiptPrinter printer;
	private CoinDispenser coinDispenser;
	private BanknoteDispenser bankNoteDispenser;
	
	
//------------------------- CONSTRUCTOR -------------------------//	
	
	public notifyAttendant(ReceiptPrinter printer, ElectronicScale scale, CoinDispenser coinDispenser, BanknoteDispenser banknoteDispenser) {
		this.electronicScale = scale;
		this.coinDispenser = coinDispenser;
        this.bankNoteDispenser = banknoteDispenser;
		this.printer = printer;
		
		this.nAttendantObserver = new notifyAttendantObserver(this);
	}

//------------------------- ATTACH OBSERVER -------------------------------//
	
	//Attach our observers (All observers in one class)
	private void attachObservers() {
        printer.attach(nAttendantObserver);
        electronicScale.attach(nAttendantObserver);
        coinDispenser.attach(nAttendantObserver);
        bankNoteDispenser.attach(nAttendantObserver);
	}
	
//------------------------- RECEIPT PRINTER METHODS -------------------------------//	
	
	public ReceiptPrinter addPaper() {
		try {
			printer.addPaper(100);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return printer;
	}
	
	public ReceiptPrinter checkInk(int quantity) {
		return null; 
	}
	
//------------------------- BANK NOTE DISPENSER METHODS -------------------------------//		
	
	public void refillBanknote(int banknoteDenomination, int refillAmount) {
		
	}
	
	public void emptyBanknoteStorageUnit(BanknoteStorageUnit banknoteStorageUnit) {
		
	}
	
//------------------------- COIN DISPENSER METHODS -------------------------------//			
	
	public void refillCoin(int coinDenomination, int refillAmount) {
		
	}
	
	public void emptyCoinStorageUnit(CoinStorageUnit banknoteStorageUnit) {
		
	}
	
//------------------------- ELECTRONIC SCALE METHODS -------------------------------//	
	
	public ElectronicScale weightCheck(int units) {
		return null;
	}
	
	
}
