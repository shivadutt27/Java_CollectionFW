package dutTechGames.adventureGames;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdventureGame {

	private static final String GAME_LOCATIONS = """
			 road,at the end of the road, W: hill, E:well house,S:valley,N:forest
            hill,on top of hill with a view in all directions,N:forest, E:road
            well house,inside a well house for a small spring,W:road,N:lake,S:stream
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
            forest,at the edge of a thick dark forest,S:road,E:lake
            lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
            stream,near a stream with a rocky bed,W:valley, N:well house
			""";
	
	private enum Compass{
		E,W,N,S;
		
		private static final String[] DIRECTIONS = {"East","West","North","South"};
		
		private String getDirection() {
			return DIRECTIONS[this.ordinal()];
		}
	}
	
	private record Location(String description, Map<Compass,String> nextPlaces) {}
	
	private String lastPLace;
	private Map<String,Location> adventureMap = new HashMap<>();
	
	public AdventureGame() {}
	
	private void loadLocations(String data) {
		
		
		String[] splitInLineData = data.split("\n");
		
		List<String> splitInLineDataList =Arrays.asList(splitInLineData);
		
		splitInLineDataList.replaceAll(String::trim);
		
		for(String str : splitInLineDataList) {
			String[] parts = str.split(",", 3);
			Map<Compass,String> nextPlaces = loadDirection(parts[2]);
			Location location = new Location(parts[1],nextPlaces);
			adventureMap.put(parts[0],location);
		}
		
		adventureMap.forEach((k,v) -> System.out.printf("%s:%s%n",k,v));
	}
	
	private Map<Compass,String> loadDirection(String places){
		Map<Compass, String> nextPlaces = new HashMap<>();
		
		String[] splits = places.split(",");
		for(String split : splits) {
			String[] steps = split.trim().split(":");
			nextPlaces.put(Compass.valueOf(steps[0].trim()), steps[1].trim());
		}
		return nextPlaces;
	}
	
	public void playGame() {
		loadLocations(GAME_LOCATIONS);
	}
}