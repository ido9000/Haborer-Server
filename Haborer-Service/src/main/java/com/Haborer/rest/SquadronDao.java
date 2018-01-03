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
import com.Haborer.DB.Utilities;
import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.MakatItem;
import com.Haborer.Entities.Request;
import com.Haborer.Entities.RequestStatus;
import com.Haborer.Entities.User;
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
				items.add(EntitiesJsonToObjectsParser.parseToItem(object.toString(),false));

			}
			if(itemMakatList.hasNext()) {
				DBObject item=itemMakatList.next();
				JSONObject object= new JSONObject(JSON.serialize(item));
				items.add(EntitiesJsonToObjectsParser.parseToItem(object.toString(),false));

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
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(req), Utilities.RequestCollectionName);
		}
		return Response.status(200).build();
	}
	public Response updateRequest(Request request) {
		String toUpdate=request.get_id();
		BasicDBObject query=new BasicDBObject();
		query.put("_id", toUpdate);
		dbHandler.updateObj(query, EntitiesJsonToObjectsParser.objectToDBObject(request),Utilities.RequestCollectionName);
		Item item=request.getItem();
		if(request.getStatus().equals(RequestStatus.TAKEN)) {
			if(request.getItem().getClass().getSimpleName().equals(MakatItem.class.getSimpleName())) {
				deleteItem(item);
				item.setSquadron(request.getToSquadron());
				addItem(item);
			}else {
				item.setSquadron(request.getToSquadron());
				addItem(item);
				item.setSquadron(request.getFromSquadron());
				query.put("_id", item.get_id());
				DBCursor itemList=dbHandler.getByQuery(query,dbHandler.getCollName(item.getSquadron(),item.getClass().getSimpleName()));
				while(itemList.hasNext()) {
					DBObject itemObject=itemList.next();
					JSONObject object= new JSONObject(JSON.serialize(itemObject));
					Item OriginalItem=EntitiesJsonToObjectsParser.parseToItem(object.toString(),false);
					((CountItem)OriginalItem).setItemCount(((CountItem)OriginalItem).getItemCount()-((CountItem)item).getItemCount());
					dbHandler.updateObj(query, EntitiesJsonToObjectsParser.objectToDBObject(OriginalItem),dbHandler.getCollName(item.getSquadron(),item.getClass().getSimpleName()));

				}				
				
			}
		}
		else if(request.getStatus().equals(RequestStatus.RETURNED)) {
			if(request.getItem().getClass().getSimpleName().equals(MakatItem.class.getSimpleName())) {
				deleteItem(item);
				item.setSquadron(request.getFromSquadron());
				addItem(item);
			}else {
				item.setSquadron(request.getToSquadron());
				deleteItem(item);
				item.setSquadron(request.getFromSquadron());
				query.put("_id", item.get_id());
				DBCursor itemList=dbHandler.getByQuery(query,dbHandler.getCollName(item.getSquadron(),item.getClass().getSimpleName()));
				while(itemList.hasNext()) {
					DBObject itemObject=itemList.next();
					JSONObject object= new JSONObject(JSON.serialize(itemObject));
					Item OriginalItem=EntitiesJsonToObjectsParser.parseToItem(object.toString(),false);
					((CountItem)OriginalItem).setItemCount(((CountItem)OriginalItem).getItemCount()+((CountItem)item).getItemCount());
					dbHandler.updateObj(query, EntitiesJsonToObjectsParser.objectToDBObject(OriginalItem),dbHandler.getCollName(item.getSquadron(),item.getClass().getSimpleName()));

				};

			}
		}
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
	public User login(String userName, String password) {
		User user=new User();
		BasicDBObject query=new BasicDBObject();
		query.put("userName", userName);
		query.put("password", password);
		DBCursor users=dbHandler.getByQuery(query, Utilities.UsersCollectionName);
		if(users.hasNext()) {
			
			DBObject userDBObject=users.next();
			JSONObject object= new JSONObject(JSON.serialize(userDBObject));
			user=(User) EntitiesJsonToObjectsParser.parseToUser(object.toString());
			
		}

		return user;
	}    
	

}