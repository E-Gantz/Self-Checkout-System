package SCSSoftwareTest;

import static org.junit.Assert.*;
import org.junit.*;
import hardware.Barcode;
import hardware.BarcodedItem;
import hardware.Numeral;
import hardware.devices.BarcodeScanner;
import hardware.devices.ElectronicScale;
import hardware.devices.EmptyException;
import hardware.devices.OverloadException;
import hardware.devices.ReceiptPrinter;
import hardware.devices.SelfCheckoutStation;
import hardware.products.BarcodedProduct;

import SCSSoftware.ItemAdder;
import SCSSoftware.ItemPlacer;
import SCSSoftware.MemberCard;
import SCSSoftware.Membership;
import SCSSoftware.PrintReceipts;
import SCSSoftware.PrinterMaintenance;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

public class ReceiptTest {
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
	private HashMap<String, MemberCard> members;
	private Membership membership;
	private MemberCard card1;
	ReceiptPrinter printer;
	PrintReceipts receiptPrintout;
	PrinterMaintenance printmaint;
	public SelfCheckoutStation station;
	public Currency c;

	@Before
	public void setUp() throws OverloadException {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = { new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
				new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };

		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		printer = station.printer;
		cart = new ProductCart();
		printmaint = new PrinterMaintenance();
		printer.attach(printmaint);
		printer.addInk(1);
		printer.addPaper(1);
		card1 = new MemberCard("00001", "jim bob");
		members = new HashMap<String, MemberCard>();
		members.put("00001", card1);
		membership = new Membership(members);
		membership.manualEntry("00001");
		receiptPrintout = new PrintReceipts(cart, printer, membership);
	}

	@After
	public void tearDown() {
		printer = null;
		cart = null;
		card1 = null;
		members = null;
		membership = null;
		receiptPrintout = null;
		c = null;
		station = null;
	}

	@Test
	public void OneItemReceipt() throws EmptyException, OverloadException {
		cart.addToCart(prod1);
		receiptPrintout.printReceipt();
		String returnedReceipt = printer.removeReceipt();
		assertEquals(returnedReceipt, "Bread $5\n00001");
	}

	@Test
	public void TwoItemReceipt() throws EmptyException, OverloadException {
		cart.addToCart(prod1);
		cart.addToCart(prod2);
		receiptPrintout.printReceipt();
		String returnedReceipt = printer.removeReceipt();
		assertEquals(returnedReceipt, "Bread $5\nMilk $10\n00001");
	}
}
