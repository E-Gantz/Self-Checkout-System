package hardware.external;

import java.util.HashMap;
import java.util.Map;

import hardware.Barcode;
import hardware.PriceLookupCode;
import hardware.products.BarcodedProduct;
import hardware.products.PLUCodedProduct;
import hardware.products.Product;

/**
 * Represents a cheap and dirty version of a set of databases that the
 * simulation can interact with. The databases have to be populated in order to
 * be usable.
 */
public class ProductDatabases {
	/**
	 * Instances of this class are not needed, so the constructor is private.
	 */
	private ProductDatabases() {}

	/**
	 * The known PLU-coded products, indexed by PLU code.
	 */
	public static final Map<PriceLookupCode, PLUCodedProduct> PLU_PRODUCT_DATABASE = new HashMap<>();

	/**
	 * The known barcoded products, indexed by barcode.
	 */
	public static final Map<Barcode, BarcodedProduct> BARCODED_PRODUCT_DATABASE = new HashMap<>();

	/**
	 * A count of the items of the given product that are known to exist in the
	 * store. Of course, this does not account for stolen items or items that were
	 * not correctly recorded, but it helps management to track inventory.
	 */
	public static final Map<Product, Integer> INVENTORY = new HashMap<>();
}
