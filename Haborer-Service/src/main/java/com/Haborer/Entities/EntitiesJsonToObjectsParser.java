package com.Haborer.Entities;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		   
		   public static Item  parseToItem(String itemJson) {
			   MakatItem makatItem;
			   CountItem countItem;

			   try {
					makatItem = mapper.readValue(new JSONObject(itemJson).toString(), MakatItem.class);
					makatItem.setDateAdded(new Date());
					return makatItem;
			   } catch (JSONException | IOException err1) {
				   try {
					   countItem=mapper.readValue(new JSONObject(itemJson).toString(), CountItem.class);
					   countItem.setDateAdded(new Date());
					   return countItem;
				   }catch (JSONException | IOException err2) {
					   System.out.println("Wrong structure of fule");
					   
				   }
				   return null;
				   
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

}
