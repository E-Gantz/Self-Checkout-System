package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import hardware.Barcode;
import hardware.BarcodedItem;
import hardware.NullPointerSimulationException;
import hardware.Numeral;
import hardware.devices.BarcodeScanner;
import hardware.devices.SelfCheckoutStation;
import hardware.products.BarcodedProduct;
import SCSSoftware.Checkout;
import SCSSoftware.ProductCart;

public class CheckoutTest {
	private BarcodeScanner scanner;
	private ProductCart pcart;
	private Checkout checkout;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
	public Barcode bc1 = new Barcode(code1); // 001
	public Barcode bc2 = new Barcode(code2); // 002
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public BarcodedItem item2 = new BarcodedItem(bc2, 4);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
	public SelfCheckoutStation station;
	public Currency c;

	@Before
	public void setUp() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = { new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
				new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };

		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		scanner = station.mainScanner;
		pcart = new ProductCart();
		checkout = new Checkout(scanner, station.handheldScanner, pcart);
	}

	@After
	public void tearDown() {
		scanner = null;
		pcart = null;
		checkout = null;
		c = null;
		station = null;
	}

	@Test(expected = NullPointerSimulationException.class)
	public void noCheckoutWhenDisabled() {
		scanner.disable();
		checkout.enable();
	}

	@Test(expected = NullPointerSimulationException.class)
	public void noCheckoutWithEmptyCart() {
		checkout.enable();
	}

	@Test
	public void scannerDisabledDuringCheckout() {
		pcart.addToCart(prod1);
		checkout.enable();
		assertTrue(scanner.isDisabled());
	}

	@Test
	public void checkoutPossibleWithNonEmptyCart() {
		pcart.addToCart(prod1);
		checkout.enable();
		assertTrue(checkout.getState());
	}

	@Test
	public void testEnable() {
		scanner.scan(item1);
		pcart.addToCart(prod1);
		scanner.enable();
		checkout.enable();
		assertTrue(checkout.getState());
	}

	@Test(expected = NullPointerSimulationException.class)
	public void testScannerDissabled() {
		scanner.scan(item1);
		scanner.disable();
		checkout.enable();
	}

	@Test(expected = NullPointerSimulationException.class)
	public void testEmptyCart() {
		scanner.enable();
		checkout.enable();
	}

	@Test
	public void cancelCheckoutGoBackToScanning() {
		pcart.addToCart(prod1);
		checkout.enable();
		checkout.disable();
		assertTrue(!(checkout.getState()) && !(scanner.isDisabled()));
	}

	@Test
	public void amountPaidCorrect() {
		checkout.setAmountPaid(new BigDecimal(5));
		assertTrue(new BigDecimal(5).equals(checkout.getAmountPaid()));
	}

	@Test
	public void correctTotalPrice() {
		pcart.addToCart(prod1);
		checkout.enable();
		assertTrue(new BigDecimal(5).compareTo(checkout.getTotalPrice()) == 0);
	}

}
