package dutTextGame;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Location {

	private String name, description;
	private Map<String,String> map = new HashMap<>();
	private List<Location> locationList;
	
	public Location(String name, String description, Map<String,String> map) {
		this.name = name;
		this.description = description;
		this.map = map;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Map<String,String> getMap(){
		return this.map;
	}
	
	public List<Location> getLocationList(){
		return this.locationList;
	}
	
	@Override
	public String toString() {
		String finalStr = "%s,%s".formatted(name,description);
		Set<String> keySet = map.keySet();
		for(String k: keySet) {
			finalStr+=","+k+":"+map.get(k);
		}
		return finalStr;
		
		
	}
}
