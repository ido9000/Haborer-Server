package com.Haborer.DB;
import java.rmi.UnknownHostException;
import java.util.List;
import java.util.Set;
import org.bson.BSONObject;
import org.bson.Document;
import com.mongodb.*;

public abstract class DBHandler {
	protected static MongoClientURI mongoClientURI=new MongoClientURI(Utilities.ClientURI);
	protected static MongoClient mongoClient = null;
	protected static DB mdb = null;
	
	public DBHandler() {
	    if (mdb == null) {
	        mongoClient = new MongoClient(mongoClientURI);
	        mdb = mongoClient.getDB(Utilities.DBName);
	    }
	}
	
	public void insertObj(DBObject obj, String CollectionName) {
	    try{
	         DBCollection collection = mdb.getCollection(CollectionName);
	         collection.insert(obj);   
	        }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void updateObj(DBObject oldObj ,DBObject newObj, String CollectionName) {
	    try{   
	         DBCollection collection = mdb.getCollection(CollectionName);
	         collection.update(oldObj,newObj);
	       }
	   catch (Exception e) {
	       e.printStackTrace();
	   }
	}
	
	
	public void removeObj(DBObject obj, String collectionName) {
	    try{   
	        DBCollection collection = mdb.getCollection(collectionName);
	        collection.remove(obj);
	       }
	   catch (Exception e) {
	       e.printStackTrace();
	   }
	    }
	
	public Set<String> getCollectionsNames() {
		return mdb.getCollectionNames();
	}
	public DBCollection getCollection(String collectionName) {
		return mdb.getCollection(collectionName);
	}

	public static DB getMdb() {
		return mdb;
	}

	public static void setMdb(DB mdb) {
		DBHandler.mdb = mdb;
	}
}
