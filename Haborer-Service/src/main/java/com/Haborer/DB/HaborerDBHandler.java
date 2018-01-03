package com.Haborer.DB;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.Request;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;

public class HaborerDBHandler extends DBHandler {
	public HaborerDBHandler() {
		super();
	}
	public DBCursor getByQuery(BasicDBObject query,String collectionName){
        DBCollection collection = mdb.getCollection(collectionName);
		return collection.find(query);
	}
	public List<Request> getRequests(String squadronField, String squadronValue){
		List<Request> requests=new ArrayList<>();
		BasicDBObject query=new BasicDBObject();
		query.put(squadronField, squadronValue);
		DBCursor requestList=getByQuery(query,Utilities.RequestCollectionName);
		while(requestList.hasNext()) {
			DBObject item=requestList.next();
			JSONObject object= new JSONObject(JSON.serialize(item));
			requests.add(EntitiesJsonToObjectsParser.parseToRequest(object.toString()));
		}
		return requests;
	}
	public String getCollName(String squadron, String itemType) {
		return "Squadron-"+squadron+"-"+itemType;
	}
}
