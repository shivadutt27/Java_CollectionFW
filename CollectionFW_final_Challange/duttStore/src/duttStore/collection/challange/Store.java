package duttStore.collection.challange;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;


class Cart{
	
	
	enum Type{VIRTUAL,PHYSICAL}
	
	private int id = 0;
	private Map<String,Integer> products;
	private Date date;
	private Type type;
	
	public Cart(Type type) {
		this.type = type;
		this.products = new HashMap<>();
		this.date = Date.from(Instant.now());
		this.id+=1;
	}
	
	public void addItem(InventoryItem item, int qty) {
		if(item.reserveItem(qty)) {
			products.merge(item.getProduct().sku(),qty,(k,v)->k+v);
		}
	}
	
	public void removeItem(InventoryItem item, int qty) {
		// In Given method we are removing the specified amount of items from the cart
		// If specified quantity > current quantity in cart
			//that means remove all the items in the cart and release all the items from inventory
		// If specified qty < current cart qty
			//Remove the given qty and release the items of same given qty
		
		//Lets find out how many items are present in the cart
		Integer current = this.products.get(item.getProduct().sku());
		//System.out.println("%n current qty is: %s".formatted(current));
		
		if(current<=qty) {
			products.remove(item.getProduct().sku());
		}else {
			products.put(item.getProduct().sku(), current-qty);
		}
		System.out.println("Before Release: "+item.getQtyReserved());
		item.releaseItem(Math.min(qty, current));
		System.out.println("After Release: "+item.getQtyReserved());
	}
	
	public void printSalesSlip(Map<String,InventoryItem> inventory) {
		/*
		 * Calculate the price of all the items present in cart and print it
		 * Take a local variable total initialize it with value 0
		 * Iterate over each product in products map
		 * adding the price to total 
		 * 
		 * */
		int total = 0;
		System.out.println("-----Welcome to Dutta Traders ---------------");
		Set<Entry<String,Integer>> productSet = products.entrySet(); // we get the set of entry of key-value elements of products map
		
		//Iterate over the elements of set
		//lets do the index based iteration
		for(Map.Entry<String,Integer> product: productSet) {
			// Find out the price of single item 
			// Using key (String) as sku, we find the item
			InventoryItem item = inventory.get(product.getKey()); // Here we get the item from its sku
			double price = item.getSalesPrice(); // Here we have the price of that item, now we want qty of those items
			int totalQty = product.getValue(); // Here we have the total quantity of item
			double totalPrice = price*totalQty; // price of a single item * total qty of items
			total+=totalPrice;
		}
		System.out.println("Total Price in your cart is: "+total);
	}
	

	@Override
	public String toString() {
		return "Cart [id=" + id + ", products=" + products + ", date=" + date + ", type=" + type + "]";
	}
	
	
}

public class Store {
	
	private static Random randPrice = new Random();
	private Map<String,InventoryItem> inventory;
	private Set<Cart> carts = new HashSet<>();
	private Map<Category, Map<String,InventoryItem>> aisleInventory;
	
	public static void main(String[] args) {
		//System.out.println("InventoryList is -------");
		Store store = new Store();
		store.stockStore();
		//store.inventoryList();
		store.stockAisles();
		//store.listProductsByCategory();
		store.manageStoreCart();
	}
	
	Category produce = new Category("Produce");
	Category dairy = new Category("Dairy");
	Category cereal = new Category("Cereal");
	Category meat = new Category("Meat");
	Category beverage = new Category("beverage");
	public void stockStore() {
		inventory = new HashMap<>();
//		Category produce = new Category("Produce");
//		Category dairy = new Category("Dairy");
//		Category cereal = new Category("Cereal");
//		Category meat = new Category("Meat");
//		Category beverage = new Category("beverage");
		List<Product> products = new ArrayList<>(List.of(
				new Product("A100","apple","local",produce),
				new Product("B100","banana","local",produce),
				new Product("P100","pear","local",produce),
				new Product("L103","lemon","local",produce),
				new Product("M201","milk","farm",dairy),
				new Product("Y001","yogurt","farm",dairy),
				new Product("C333","cheese","farm",dairy),
				new Product("R777","rice chex","Nabisco",cereal),
				new Product("G111","granola","Nat Valley",cereal),
				new Product("BB11","ground beef","butcher",meat),
				new Product("BC77","coke","coca cola",beverage),
				new Product("BC88","coffee","value",beverage),
				new Product("BC99","tea","herbal",beverage)
				));
		
		products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p,10,randPrice.nextDouble(1, 5.0))));
	}
	
	public void manageStoreCart() {
		/*
		 * As it is manage store cart so cart type will be physical
		 * After instantiating the physical cart 
		 * we will add the item from shelf i.e get the item from aisle Inventory
		 * */
		Cart cart1 = new Cart(Cart.Type.PHYSICAL);
		InventoryItem item1 = aisleInventory.get(produce).get("apple");
		cart1.addItem(item1, 16);
		carts.add(cart1);
		
		var item2 = aisleInventory.get(beverage).get("coffee");
		var item3 = aisleInventory.get(produce).get("pear");
		
		cart1.addItem(item2, 18);
		cart1.addItem(item3, 20);
		
		cart1.removeItem(item3, 5);
		System.out.println(cart1);
		
		//cart1.removeItem(item3, 2);
		
		//System.out.println("---------------- \n"+cart1);
		
	}
	
	public void checkoutCart() {}
	
	public void abandonCart() {}
	
	public void inventoryList() {
		inventory.values().forEach(System.out::println);
	}

	/*
	 * In this method we are instantiating the aisleInventory Map
	 * As this contains Category as key and a nested map contains product name as key and InventoryItem as value
	 * By Iterating over inventory Map we can get the Category of Item which we will use as key
	 * Now we need to build a logic to put the same category items in the Map
	 *
	 * */
	public void stockAisles() {
		aisleInventory = new HashMap<>();
		for(InventoryItem item: inventory.values()) {
			Category aisle = item.getProduct().category();
			
			Map<String,InventoryItem> productMap = aisleInventory.get(aisle);
			if(productMap == null) {
				productMap = new TreeMap<>();
			}
			productMap.put(item.getProduct().name(), item);
			aisleInventory.putIfAbsent(aisle, productMap);
		}
	}
	
//	public void stockAisles2() {
//		aisleInventory = new HashMap<>();
//		Map<String,InventoryItem> productMap = new TreeMap<>();
//		for(InventoryItem item: inventory.values()) {
//			Category aisle = item.getProduct().category();
//			
//			productMap.put(item.getProduct().name(), item);
//			aisleInventory.put(aisle, productMap);
//		}
//		
//	}
	
	public void listProductsByCategory() {
		System.out.println("-----------list for aisle-------------");
		
		for(var aisle: aisleInventory.entrySet()) {
			System.out.println("------------\n"+aisle.getKey().getCategory()+"\n-----------");
			aisle.getValue().forEach((k,v) -> System.out.println(v.getProduct().name()));
		}
	}
}