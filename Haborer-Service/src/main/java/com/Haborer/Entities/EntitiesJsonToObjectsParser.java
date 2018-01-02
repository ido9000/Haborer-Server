package com.Haborer.Entities;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public final class EntitiesJsonToObjectsParser {
		private static ObjectMapper mapper=new ObjectMapper();

	   public static ItemRequestsFactory getItemRequstsFactory(String factoryJson) {
		   try {
			return mapper.readValue(new JSONObject(factoryJson).toString(), ItemRequestsFactory.class);
		} catch (JSONException | IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	   }
		   
		   public static Item  parseToItem(String itemJson,boolean forAdding) {
			   JSONObject obj=new JSONObject(itemJson);
			   if(obj.keySet().contains("itemMakat")) {
				   MakatItem makatItem;
				try {
					makatItem = mapper.readValue(obj.toString(), MakatItem.class);
				} catch (JSONException | IOException e) {
					System.out.println(e.getMessage());

					return null;
				}
					if(forAdding) {
						makatItem.setDateAdded(new Date());
						}
					return makatItem;
			   }else {
				   CountItem countItem;
				try {
					countItem = mapper.readValue(obj.toString(), CountItem.class);
				} catch (JSONException | IOException e) {
					System.out.println(e.getMessage());
					return null;
				}
				if(forAdding) {
					   countItem.setDateAdded(new Date());

				}
				   return countItem;
			   }
			   
			   
			   
		}
		   public static Request parseToRequest(String requestJson) {
			   try {
				return mapper.readValue(new JSONObject(requestJson).toString(), Request.class);
			} catch (JSONException | IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
			public static  DBObject objectToDBObject(Object item) {
				try {
					return (DBObject)JSON.parse(mapper.writeValueAsString(item));
				} catch (JsonProcessingException e) {
					System.out.println(e.getMessage());

				}
				return null;
			}

}
