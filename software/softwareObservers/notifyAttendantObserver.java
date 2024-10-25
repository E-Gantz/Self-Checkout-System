package softwareObservers;

import hardware.Banknote;
import hardware.Coin;
import hardware.devices.AbstractDevice;
import hardware.devices.BanknoteDispenser;
import hardware.devices.CoinDispenser;
import hardware.devices.ElectronicScale;
import hardware.devices.OverloadException;
import hardware.devices.ReceiptPrinter;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.BanknoteDispenserObserver;
import hardware.devices.observers.CoinDispenserObserver;
import hardware.devices.observers.ElectronicScaleObserver;
import hardware.devices.observers.ReceiptPrinterObserver;

import SCSSoftware.notifyAttendant;

public class notifyAttendantObserver implements ReceiptPrinterObserver, ElectronicScaleObserver, CoinDispenserObserver, BanknoteDispenserObserver {
	
//----------------------------------- INITIALIZED VARIABLES ---------------------------//	
	
	//Object
	notifyAttendant notify;
	
//---------------------------------- CONSTRUCTOR -------------------------------------//		

	public notifyAttendantObserver(notifyAttendant notify) {
		this.notify = notify;
	}
	
//---------------------------------- OVERRIDE METHODS -------------------------------------//	
	
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	//------------ RECEIPT PRINTER OBSERVER METHODS ---------------//
	
	@Override
	public void outOfPaper(ReceiptPrinter printer) {
		notify.addPaper();
	}
	
	@Override
	public void outOfInk(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void paperAdded(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inkAdded(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}

	//------------ ELECTRONIC SCALE OBSERVER METHODS ---------------//
	
	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void overload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void outOfOverload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}

	//------------ COIN DISPENSER OBSERVER METHODS ---------------//

	@Override
	public void coinsFull(CoinDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinsEmpty(CoinDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinAdded(CoinDispenser dispenser, Coin coin) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinRemoved(CoinDispenser dispenser, Coin coin) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinsLoaded(CoinDispenser dispenser, Coin... coins) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinsUnloaded(CoinDispenser dispenser, Coin... coins) {
		// TODO Auto-generated method stub
		
	}


	//------------ BANK NOTE DISPENSER OBSERVER METHODS ---------------//
	
	@Override
	public void moneyFull(BanknoteDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknotesEmpty(BanknoteDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void billAdded(BanknoteDispenser dispenser, Banknote banknote) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknotesUnloaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		// TODO Auto-generated method stub
		
	}

}
