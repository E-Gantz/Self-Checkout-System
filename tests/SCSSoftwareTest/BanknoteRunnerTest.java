package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import hardware.Banknote;
import hardware.Barcode;
import hardware.BarcodedItem;
import hardware.Numeral;
import hardware.devices.BanknoteSlot;
import hardware.devices.BanknoteStorageUnit;
import hardware.devices.BanknoteValidator;
import hardware.devices.BarcodeScanner;
import hardware.devices.BidirectionalChannel;
import hardware.devices.DisabledException;
import hardware.devices.OverloadException;
import hardware.devices.SelfCheckoutStation;

import SCSSoftware.BanknoteRunner;
import SCSSoftware.Checkout;
import SCSSoftware.ProductCart;

/**
 * Tests for the CoinRunner class
 */
public class BanknoteRunnerTest {
	private BarcodeScanner scanner;
	public BanknoteRunner banknoteRunner;
	private ProductCart pcart;
	private Checkout checkout;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	private BanknoteSlot bSlot;
	private BanknoteStorageUnit bStorage;
	private BanknoteValidator bValidator;
	private Currency currency = Currency.getInstance("CAD");
	private BidirectionalChannel<Banknote> validatorSource;
	public SelfCheckoutStation station;
	public Currency c;

	/**
	 * Loads SelfCheckoutStation, prepares hardware and all currency denominations
	 * for testing
	 */
	@Before
	public void setup() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = { new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
				new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		scanner = station.mainScanner;
		pcart = new ProductCart();
		checkout = new Checkout(scanner, station.handheldScanner, pcart);
		bSlot = station.banknoteInput;
		bStorage = station.banknoteStorage;
		bValidator = station.banknoteValidator;
		banknoteRunner = new BanknoteRunner(checkout.getTotalPrice(), bSlot, bStorage, bValidator);
	}

	/**
	 * Tests to see if value of item added in cart matches with the value of the
	 * checkout total, passes if true
	 */
	@Test
	public void testGetCheckoutTotal() {
		scanner.scan(item1);
		assertEquals(banknoteRunner.getCheckoutTotal(), checkout.getTotalPrice());
	}

	@Test
	public void testGetPaidTotal() throws DisabledException, OverloadException {
		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
		bSlot.accept(note);
		assertEquals(banknoteRunner.getPaidTotal(), BigDecimal.valueOf(note.getValue()));
	}

	/**
	 * Tests to see if the value inserted banknotes matches with what is recorded in
	 * the checkout machine
	 */
	@Test
	public void testBanknoteCart() throws DisabledException, OverloadException {
		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
		ArrayList<Banknote> banknoteCart = new ArrayList<Banknote>();
		banknoteCart.add(note);
		bSlot.accept(note);
		assertEquals(banknoteRunner.getBanknoteCart().get(0).getValue(), banknoteCart.get(0).getValue());
	}

	/**
	 * Tests to see if the value of all banknotes inserted into the checkout machine
	 * is equal to 10 when summed
	 */
	@Test
	public void testSumBanknotes() throws DisabledException, OverloadException {
		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
		bSlot.accept(note);
		bSlot.accept(note);
		assert (banknoteRunner.sumBanknotes().doubleValue() == 10.0);
	}

}
