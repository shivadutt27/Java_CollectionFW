package duttStore.collection.challange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
enum EnumCategory{
	
	DAIRY,NON_DAIRY,COFFEE,MERCHANDISE;
	
	private static final Map<Category,String> mappedValue = new HashMap<>();
	
	public static void setValue(Category category,String value) {
		mappedValue.put(category, value);
	}
	
	public static String getValue(Category category) {
		
		String msg = "";
		
		mappedValue.compute(category, (k,v) -> v== null? msg+"Category not present": msg+v);
		
//		List<Category> categories = new ArrayList<>(List.of(Category.values()));
//		if(categories.contains(category)) {
//			return mappedValue.get(category);
//		}else {
//			System.out.println("Category not present !!");
//			return null;
//		}
		
		return msg;
	}
	
}
*/

class Category{
	//private Set<String> categories = new HashSet<>();
	private String category;
	
	public Category(String category) {
		this.category = category;
		//this.categories.add(category);
	}
	
	public String getCategory() {
		return this.category;
	}

	
//	public void addCategory(String category) {
//		if(!categories.contains(category)) {
//			categories.add(category);
//		}else {
//			System.out.println("Category already Present !!");
//		}
//	}
	
//	public void showCategory() {
//		categories.forEach(category -> System.out.println(category));
//	}
	
	@Override
	public String toString() {
		return category;
	}
}

record Product(String sku, String name, String manufacturer,Category category) {}

public class InventoryItem {
	
	private Product product;
	private int qtyTotal;
	private int qtyReserved;
	private int qtyReorder;
	private int qtyLow;
	private double salesPrice;
	
	public InventoryItem(String sku, String name, String manufacturer, 
			String category, double salesPrice, int qtyTotal) {
		
		this.product = new Product(sku, name, manufacturer, new Category(category));
		this.salesPrice = salesPrice;
		this.qtyTotal = qtyTotal;
		this.qtyLow = 30;
	}
	
	public InventoryItem(Product product, int qtyTotal, double salesPrice) {
		this.product = product;
		this.qtyTotal = qtyTotal;
		this.salesPrice = salesPrice;
		this.qtyLow = 30;
	}

	
	public Product getProduct() {
		return this.product;
	}
	
	public int getQtyReserved() {
		return this.qtyReserved;
	}
	
	public double getSalesPrice() {
		return this.salesPrice;
	}
	
	private boolean isQtyLow() {
		return qtyTotal<=qtyLow ? true : false;
	}
	
	public boolean reserveItem(int qtyReserved) {
		if(qtyReserved < qtyLow ) {
			this.qtyReserved = qtyReserved;
			return true;
		}else {
			System.out.println("qtyLow threshold beeps");
			return false;
		}
	}

	public void releaseItem() {
		if(qtyReserved > 0) {
			this.qtyReserved = 0;
		}else {
			System.out.println("There is no reservedItem");
		}
		
	}
	
	public void releaseItem(int qty) {
		this.qtyReserved -= qty;
	}
	
	public void sellItem() {
		qtyTotal = qtyTotal-qtyReserved;
		this.qtyReserved=0;
	}
	
	public void placeInventoryOrder() {
		if(isQtyLow()) {
			this.qtyReorder = qtyLow;
		}
	}
	
	@Override
	public String toString() {
		return "%s:-> QtyReserverd: %s, totalQty: %s, Price: %.2f".formatted(product.name(),qtyReserved,qtyTotal,salesPrice);
	}
}