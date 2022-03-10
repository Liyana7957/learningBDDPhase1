package files;

public class payload {
	public static String Address = "29, side layout, cohen 09";
	public static String NewAddress = "jainKokila";
	
	public static String addBody(){
		
		return "{\r\n" + 
				"	\"location\": {\r\n" + 
				"	\"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"             },\r\n" + 
				"\"accuracy\": 50,\r\n" + 
				"\"name\": \"Frontline house\",\r\n" + 
				"\"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"\"address\": \""+Address+"\",\r\n" + 
				"\"types\": [\"shoe park\",\"shop\" ],\r\n" + 
				"\"website\":\"http://google.com\",\r\n" + 
				"\"language\": \"French-IN\"\r\n" + 
				"}";
	}
	
	public static String updateBody(String PlaceId){
		
		return "{\r\n" + 
				"    \"place_id\" :\""+PlaceId+"\",\r\n" + 
				"    \"address\": \""+NewAddress+"\",\r\n" + 
				"    \"key\": \"qaclick123\"\r\n" + 
				"}";
	}
	
	public static String deleteRequestPayload(String PlaceId){
		return "{\r\n" + 
				"    \"place_id\" : \""+PlaceId+"\"\r\n" + 
				"}";
	}

}
