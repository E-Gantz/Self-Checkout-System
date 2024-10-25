package SCSSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import hardware.Banknote;
import hardware.Coin;
import hardware.devices.BanknoteDispenser;
import hardware.devices.BanknoteSlot;
import hardware.devices.CoinDispenser;
import hardware.devices.CoinTray;
import hardware.devices.DisabledException;
import hardware.devices.EmptyException;
import hardware.devices.OverloadException;

/**
 * This class is responsible for conducting cash payments (Coin & Banknote).
 * This class will consolidate the total number of coins & banknotes inserted
 * from BanknoteRunner & CoinRunner and then verify if it's total matches with
 * what the total of the transaction. If the total inserted money is greater
 * than the transaction amount, change is then calculated and dispensed.
 * 
 */
public class PaysWithCash {

	private CoinRunner coinrunner;
	private BanknoteRunner banknoterunner;
	private Map<Integer, BanknoteDispenser> banknotedispenser;
	private Map<BigDecimal, CoinDispenser> coindispenser;
	private BigDecimal chargedTotal;
	private BigDecimal totalValue;
	private BanknoteSlot banknoteOutputSlot;
	private CoinTray coinTray;

	/**
	 * Constructs the PaysWithCash class by taking in the various coin & banknote
	 * hardware classes as arguments to validate currency, denomination and where to
	 * store the coin in the machine and to obtain change to be given back.
	 * 
	 * @param coinrunner
	 * @param banknoteRunner
	 * @param banknoteDispenser
	 * @param coinDispenser
	 * @param banknoteOutputSlot
	 * @param coinTray
	 */
	public PaysWithCash(CoinRunner coinrunner, BanknoteRunner banknoteRunner,
			Map<Integer, BanknoteDispenser> banknoteDispenser, Map<BigDecimal, CoinDispenser> coinDispenser,
			BanknoteSlot banknoteOutputSlot, CoinTray coinTray) {
		this.coinrunner = coinrunner;
		this.banknoterunner = banknoteRunner;
		this.coindispenser = coinDispenser;
		this.banknotedispenser = banknoteDispenser;
		this.banknoteOutputSlot = banknoteOutputSlot;
		this.coinTray = coinTray;
	}

	/**
	 * This method will consolidate the total number of coins + total number of
	 * banknotes into one and returns a BigDecimal value
	 * 
	 * @Return totalValue
	 */
	public BigDecimal sumCoinBanknote() {
		BigDecimal totalCoins = coinrunner.sumCoins();
		BigDecimal totalBanknotes = banknoterunner.sumBanknotes();
		totalValue = new BigDecimal(0).add(totalCoins);
		this.totalValue = totalValue.add(totalBanknotes);
		return totalValue;
	}

	/**
	 * This method updates the totalValue with what the customer has paid and
	 * returns the new totalValue
	 * 
	 * @Return totalValue
	 */
	public BigDecimal getChange() {
		chargedTotal = banknoterunner.getCheckoutTotal();

		this.totalValue = totalValue.subtract(chargedTotal);

		return totalValue;
	}

	/**
	 * This method calculates how change is dispensed back to the customer in coins.
	 * High to low values are added to the array to determine the order it is
	 * dispensed, and then deducted and looped back until the totalValue is 0.
	 * Change is then dispensed from the arraylist based on the value that is
	 * contained. Upon success, this method will return the total amount of change
	 * given as a BigDecimal.
	 * 
	 * @Return changeReturned
	 */
	public BigDecimal emitCoins() throws OverloadException, DisabledException {
		for (CoinDispenser dispenser : coindispenser.values()) {
			dispenser.unload();
		}
		BigDecimal changeReturned = BigDecimal.ZERO;
		List<Coin> listOfCoins = new ArrayList<Coin>();
		if (totalValue.compareTo(BigDecimal.ZERO) >= 0) {
			double totalValueDouble = totalValue.doubleValue();
			while (totalValueDouble != 0) {

				if (totalValueDouble - 2.00 >= 0) {
					totalValueDouble -= 2.00;
					listOfCoins.add(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00)));
				} else if (totalValueDouble - 1.00 >= 0) {
					totalValueDouble -= 1.00;
					listOfCoins.add(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00)));
				} else if (totalValueDouble - 0.25 >= 0) {
					totalValueDouble -= 0.25;
					listOfCoins.add(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25)));
				} else if (totalValueDouble - 0.10 >= 0) {
					totalValueDouble -= 0.10;
					listOfCoins.add(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10)));
				} else if (totalValueDouble - 0.05 >= 0) {
					totalValueDouble -= 0.05;
					listOfCoins.add(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05)));
				} else if (totalValueDouble < 0.05 && totalValueDouble > 0) {
					if (totalValueDouble >= 0.03) {
						totalValueDouble = 0.05;
					} else {
						totalValueDouble = 0;
					}
				}
			}
			Coin[] coins = {};
			for (Coin coin : listOfCoins) {
				if (coindispenser.containsKey(coin.getValue())) {
					coindispenser.get(coin.getValue()).load(coin);
				}
			}
			listOfCoins.toArray(coins);

			int count1 = 0;
			while (count1 < 5) {
				while (count1 < 1) {
					try {
						coindispenser.get(BigDecimal.valueOf(2.00)).emit();
						coinTray.accept(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00)));
						coinTray.collectCoins();
						changeReturned = changeReturned.add(BigDecimal.valueOf(2.00));
					} catch (EmptyException e) {
						count1++;

					}
				}
				while (count1 < 2) {
					try {
						coindispenser.get(BigDecimal.valueOf(1.00)).emit();
						coinTray.accept(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00)));
						coinTray.collectCoins();
						changeReturned = changeReturned.add(BigDecimal.valueOf(1.00));
					} catch (EmptyException e) {
						count1++;

					}
				}
				while (count1 < 3) {
					try {
						coindispenser.get(BigDecimal.valueOf(0.25)).emit();
						coinTray.accept(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25)));
						coinTray.collectCoins();
						changeReturned = changeReturned.add(BigDecimal.valueOf(0.25));
					} catch (EmptyException e) {
						count1++;

					}
				}
				while (count1 < 4) {
					try {
						coindispenser.get(BigDecimal.valueOf(0.10)).emit();
						coinTray.accept(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10)));
						coinTray.collectCoins();
						changeReturned = changeReturned.add(BigDecimal.valueOf(0.10));

					} catch (EmptyException e) {
						count1++;

					}
				}
				while (count1 < 5) {
					try {
						coindispenser.get(BigDecimal.valueOf(0.05)).emit();
						coinTray.accept(new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05)));
						coinTray.collectCoins();
						changeReturned = changeReturned.add(BigDecimal.valueOf(0.05));
					} catch (EmptyException e) {
						count1++;

					}
				}
			}
		}
		return changeReturned;
	}

	/**
	 * This method calculates how change is dispensed back to the customer in
	 * banknotes. High to low values are added to the array to determine the order
	 * it is dispensed, and then deducted and looped back until the totalValue is 0.
	 * Change is then dispensed from the arraylist based on the value that is
	 * contained. Upon success, this method will return the total amount of change
	 * given as a BigDecimal.
	 * 
	 * @Return changeReturned
	 */
	public BigDecimal emitBanknotes() throws OverloadException, DisabledException {
		BigDecimal changeReturned = BigDecimal.ZERO;
		List<Banknote> listOfNotes = new ArrayList<Banknote>();
		double totalValueDouble = totalValue.doubleValue();
		if (totalValue.compareTo(BigDecimal.ZERO) >= 0) {
			while (totalValueDouble > 5) {
				if (totalValueDouble - 100 >= 0) {
					totalValueDouble -= 100;
					listOfNotes.add(new Banknote(Currency.getInstance("CAD"), 100));
				} else if (totalValueDouble - 50 >= 0) {
					totalValueDouble -= 50;
					listOfNotes.add(new Banknote(Currency.getInstance("CAD"), 50));
				} else if (totalValueDouble - 20 >= 0) {
					totalValueDouble -= 20;
					listOfNotes.add(new Banknote(Currency.getInstance("CAD"), 20));
				} else if (totalValueDouble - 10 >= 0) {
					totalValueDouble -= 10;
					listOfNotes.add(new Banknote(Currency.getInstance("CAD"), 10));
				} else if (totalValueDouble - 5 >= 0) {
					totalValueDouble -= 5;
					listOfNotes.add(new Banknote(Currency.getInstance("CAD"), 5));
				}
			}
		}
		totalValue = BigDecimal.valueOf(totalValueDouble);

		Banknote[] notes = {};
		for (Banknote note : listOfNotes) {
			if (banknotedispenser.containsKey(note.getValue())) {
				banknotedispenser.get(note.getValue()).load(note);
			}
		}
		listOfNotes.toArray(notes);

		int count = 0;
		while (count < 5) {
			while (count < 1) {
				try {
					banknotedispenser.get(100).emit();
					banknoteOutputSlot.removeDanglingBanknotes();
					changeReturned = changeReturned.add(BigDecimal.valueOf(100));

				} catch (EmptyException e) {
					count++;
				}
			}
			while (count < 2) {
				try {
					banknotedispenser.get(50).emit();
					banknoteOutputSlot.removeDanglingBanknotes();
					changeReturned = changeReturned.add(BigDecimal.valueOf(50));
				} catch (EmptyException e) {
					count++;
				}
			}
			while (count < 3) {
				try {
					banknotedispenser.get(20).emit();
					banknoteOutputSlot.removeDanglingBanknotes();
					changeReturned = changeReturned.add(BigDecimal.valueOf(20));
				} catch (EmptyException e) {
					count++;
				}
			}
			while (count < 4) {
				try {
					banknotedispenser.get(10).emit();
					banknoteOutputSlot.removeDanglingBanknotes();
					changeReturned = changeReturned.add(BigDecimal.valueOf(10));
				} catch (EmptyException e) {
					count++;
				}
			}
			while (count < 5) {
				try {
					banknotedispenser.get(5).emit();
					banknoteOutputSlot.removeDanglingBanknotes();
					changeReturned = changeReturned.add(BigDecimal.valueOf(5));
				} catch (EmptyException e) {
					count++;
				}
			}
			banknoteOutputSlot.emit(notes);
		}
		return changeReturned;
	}

	/**
	 * This method updates how much total change has been returned and returns a
	 * BigDecimal
	 * 
	 * @Return currentTotal
	 */
	public BigDecimal emitChange() throws OverloadException, DisabledException {
		BigDecimal currentTotal = BigDecimal.ZERO;
		currentTotal = currentTotal.add(emitBanknotes());
		currentTotal = currentTotal.add(emitCoins());
		return currentTotal;
	}

}