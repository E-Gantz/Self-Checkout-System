package SCSSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;

import hardware.Coin;
import hardware.devices.AbstractDevice;
import hardware.devices.CoinSlot;
import hardware.devices.CoinValidator;
import hardware.devices.DisabledException;
import hardware.devices.OverloadException;
import hardware.devices.observers.AbstractDeviceObserver;
import hardware.devices.observers.CoinSlotObserver;
import hardware.devices.observers.CoinValidatorObserver;

public class PaysWithCoin implements CoinValidatorObserver{

	private CoinSlot slot;
	private CoinValidator validator;
	private BigDecimal total;
	
	private ArrayList<BigDecimal> coinArray;
	
	public PaysWithCoin(CoinSlot slot, CoinValidator validator)
	{
		this.slot = slot;
		this.validator = validator;
		coinArray = new ArrayList<BigDecimal>();
	}
	
	public void acceptCoin(Coin insertedCoin) throws DisabledException {
		try {
			slot.accept(insertedCoin);
		} catch (DisabledException e) {
			e.printStackTrace();
		} catch (OverloadException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}
	
	public void sumTotal(ArrayList<BigDecimal> list) {
		
		BigDecimal sum = BigDecimal.valueOf(0.00);
		int i = 0;
		
		while(i < list.size()) {
			sum = sum.add(list.get(i));
			i++;
		}
		
		this.setTotalCoins(sum);
	}
	
	 public void setTotalCoins(BigDecimal total)
     //setter method
	 {
		 this.setTotal(total);
	 }

	 public BigDecimal getTotalCoins()
	 {   
		 return total;
	 }

	
	@Override
	public void validCoinDetected(CoinValidator validator, BigDecimal value) {
		getCoinArray().add(value);
	}

	@Override
	public void invalidCoinDetected(CoinValidator validator) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<BigDecimal> getCoinArray() {
		return coinArray;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}