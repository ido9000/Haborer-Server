package com.Haborer.rest;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.Haborer.DB.DBHandler;
import com.Haborer.DB.HaborerDBHandler;
import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.MakatItem;
import com.Haborer.Entities.Request;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;  

public class SquadronDao { 
	private HaborerDBHandler dbHandler=new HaborerDBHandler();
	private ObjectMapper mapper=new ObjectMapper();
	
	public List<? extends Item> getSquadron(String squadron) {
		List<Item> items=new ArrayList<>();
		BasicDBObject query=new BasicDBObject();
		String collectionCount=dbHandler.getCollName(squadron,CountItem.class.getSimpleName());
		String collectionMakat=dbHandler.getCollName(squadron,MakatItem.class.getSimpleName());
		DBCursor itemCountList=dbHandler.getCollection(collectionCount).find();
		DBCursor itemMakatList=dbHandler.getCollection(collectionMakat).find();
		while(itemCountList.hasNext() || itemMakatList.hasNext()) {
			if(itemCountList.hasNext()) {
				DBObject item=itemCountList.next();
				JSONObject object= new JSONObject(JSON.serialize(item));
				items.add(EntitiesJsonToObjectsParser.parseToItem(object.toString()));

			}
			if(itemMakatList.hasNext()) {
				DBObject item=itemMakatList.next();
				JSONObject object= new JSONObject(JSON.serialize(item));
				items.add(EntitiesJsonToObjectsParser.parseToItem(object.toString()));

			}
			
		}
		
		return items;
	}
	public List<Request> getSquadronRequestsFrom(String squadronFrom) {
		return dbHandler.getRequests("fromSquadron",squadronFrom);
		
	}
	public List<Request> getSquadronRequestsTo(String squadronTO) {
		return dbHandler.getRequests("toSquadron",squadronTO);


	}
	public Response addNewRequests(ItemRequestsFactory factory) {
		ArrayList<Request> requests=factory.manageRequests();
		for(Request req:requests) {
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(req), "Requests");
		}
		return Response.status(200).build();
	}
	public Response updateRequest(Request request) {
		String toUpdate=request.get_id();
		BasicDBObject query=new BasicDBObject();
		query.put("_id", toUpdate);
		dbHandler.updateObj(query, EntitiesJsonToObjectsParser.objectToDBObject(request),"Requests");
		return Response.status(200).build();
	}
	public <T extends Item> Response deleteItem(T item) {
		dbHandler.removeObj(EntitiesJsonToObjectsParser.objectToDBObject(item), dbHandler.getCollName(item.getSquadron(), item.getClass().getSimpleName()));
		return Response.status(200).build();
	}
	public <T extends Item> Response updateItem(T item) {
		String toUpdate=item.get_id();
		BasicDBObject query=new BasicDBObject();
		query.put("_id", toUpdate);
		dbHandler.updateObj(query, EntitiesJsonToObjectsParser.objectToDBObject(item), dbHandler.getCollName(item.getSquadron(), item.getClass().getSimpleName()));
		return Response.status(200).build();
	}
	public <T extends Item> Response addItem(T item) {
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(item), dbHandler.getCollName(item.getSquadron(),item.getClass().getSimpleName()));
		return Response.status(200).build();
	}

	public List<String> getAllSqadronNames() {
		List<String> squadronNames=new ArrayList<>();
		dbHandler.getCollectionsNames().forEach(collection->{
			String[] parts= collection.split("-");
			if(parts.length==3 ) {
				if(!squadronNames.contains("Squadron "+parts[1])) {
					squadronNames.add("Squadron "+parts[1]);
				}
			}
				
			});
		
		return squadronNames;
	}    
	

}