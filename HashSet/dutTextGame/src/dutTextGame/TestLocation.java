package dutTextGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestLocation {

	private static List<Location> locations;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		addDataToLocation( """
				road,at the end of the road, W: hill, E:well house,S:valley,N:forest
hill,on top of hill with a view in all directions,N:forest, E:road
well house,inside a well house for a small spring,W:road,N:lake,S:stream
valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
forest,at the edge of a thick dark forest,S:road,E:lake
lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
stream,near a stream with a rocky bed,W:valley, N:well house
				""");
		
		
		gameController();
	}
	
	private static void addDataToLocation(String data) {
		
		List<String> dataList = new ArrayList<>();
		String[] newData = data.trim().split("\n");
		dataList.addAll(Arrays.asList(newData));
		
		locations = new ArrayList<>();
	
		Map<String,String> position = new HashMap<>();
		
		for(String d: dataList) {
			String[] dArr = d.split(",",3);
			String pos = dArr[2];
			String[] posArr = pos.split(",");
			Map<String,String> map = new HashMap<>();
			//System.out.println("poasArr len :"+posArr.length);
			int i=0;
			for(String p: posArr) {
				map.put(p.split(":")[0], p.split(":")[1]);
			}
			
			
			locations.add(new Location(dArr[0],dArr[1],map));
			
		}
		//System.out.println("============================");
		
		//locations.forEach(System.out::println);
		
	}
	
	private static void gameController() {
		
		Scanner sc = new Scanner(System.in);
		String inputStr;
		System.out.println("at the end of the road, W: hill, E:well house,S:valley,N:forest x to exit");
		while(true) {
			inputStr = sc.nextLine().toLowerCase();
			if(inputStr.equals("x")) {
				System.out.println("Exiting from Game !!");
				break;
			}else {
				getPositions(inputStr);
			}
			
			
		}
	}
	
	private static String getLocationData(String locationName) {
		String str = "";
		for(Location local: locations) {
			if(local.getName().equals(locationName)) {
				str = local.getDescription()+local.getMap();
			}
		}
		return str;
	}

	private static void getPositions(String inputStr) {
		switch(inputStr) {
		case "n" -> {System.out.println(getLocationData("forest")); break;}
		case "e" -> {System.out.println(getLocationData("well house")); break;}
		case "s" -> {System.out.println(getLocationData("valley")); break;}
		case "w" -> {System.out.println(getLocationData("hill")); break;}
		default -> System.out.println();
		}
	}
}
