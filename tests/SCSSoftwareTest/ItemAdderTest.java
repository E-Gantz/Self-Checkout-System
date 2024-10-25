package SCSSoftwareTest;

import static org.junit.Assert.*;
import org.junit.*;
import hardware.Barcode;
import hardware.BarcodedItem;
import hardware.Numeral;
import hardware.devices.BarcodeScanner;
import hardware.devices.ElectronicScale;
import hardware.devices.SelfCheckoutStation;
import hardware.products.BarcodedProduct;

import SCSSoftware.ItemAdder;
import SCSSoftware.ItemPlacer;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

import java.math.BigDecimal;
import java.util.Currency;

public class ItemAdderTest {
	public BarcodeScanner scanner;
	public ItemAdder adder;
	public ProductInventory inventory;
	public ProductCart cart;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
	public Barcode bc1 = new Barcode(code1); // 001
	public Barcode bc2 = new Barcode(code2); // 002
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public BarcodedItem item2 = new BarcodedItem(bc2, 4);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
	public ItemPlacer placer;
	public ElectronicScale scale;
	public int cartSize;
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
		inventory = new ProductInventory();
		inventory.addInventory(bc1, prod1);
		inventory.addInventory(bc2, prod2);
		cart = new ProductCart();
		placer = new ItemPlacer(scanner, cart, station.handheldScanner);
		scale = station.baggingArea;
		scale.attach(placer);
		adder = new ItemAdder(inventory, cart, placer, station.mainScanner, station.handheldScanner);
		scanner.attach(adder);
		station.handheldScanner.attach(adder);
		cartSize = cart.getItemNames().size();
	}

	@After
	public void tearDown() {
		scanner.detachAll();
		scale.detachAll();
		scanner = null;
		adder = null;
		scale = null;
		placer = null;
		cart = null;
		inventory = null;
		cartSize = 0;
		c = null; // yes i know i dont have to do this one every time
		station = null;
	}

	@Test
	public void itemPriceAddedToCart() {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		scale.add(item1);
		assertTrue(prod1.getPrice().compareTo(cart.getTotalPrice()) == 0);
	}

	@Test
	public void itemNameAddedToCart() {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		scale.add(item1);
		assertTrue(
				cart.getItemNames().contains(prod1.getDescription() + " " + "$" + (prod1.getPrice().toPlainString())));
	}

	@Test
	public void scannerDisabledAfterScan() {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		assertTrue(scanner.isDisabled());
	}

	@Test
	public void itemRemoved() {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		cart.removeFromCart(prod1);
		assertEquals(cart.getTotalExpectedWeight(), 0, 0.1);
	}

	@Test
	public void handScannerDisabledAfterScan() {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		assertTrue(station.handheldScanner.isDisabled());
	}

	@Test
	public void itemPriceAddedToCartHandheld() {
		station.handheldScanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		scale.add(item1);
		assertTrue(prod1.getPrice().compareTo(cart.getTotalPrice()) == 0);
	}

	@Test
	public void itemNameAddedToCartHandheld() {
		station.handheldScanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		scale.add(item1);
		assertTrue(
				cart.getItemNames().contains(prod1.getDescription() + " " + "$" + (prod1.getPrice().toPlainString())));
	}

	@Test
	public void itemRemovedHandheld() {
		station.handheldScanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		cart.removeFromCart(prod1);
		assertEquals(cart.getTotalExpectedWeight(), 0, 0.1);
	}

}
