package com.Haborer.db;
import com.Haborer.db.Utilities;
import java.rmi.UnknownHostException;
import java.util.List;
import java.util.Set;
import org.bson.BSONObject;
import org.bson.Document;
import com.mongodb.*;

public class DBHandler {

private static MongoURI connectionString = null;
private static Mongo mongoClient = null;
private static DB mdb = null;

public DBHandler() {
    if (mdb == null) {
        connectionString= new MongoURI(Utilities.ClientURI);
        mongoClient = new Mongo(connectionString);
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


public void removeObj(DBObject obj, String CollectionName) {
    try{   
        DBCollection collection = mdb.getCollection(CollectionName);
        collection.remove(obj);
       }
   catch (Exception e) {
       e.printStackTrace();
   }
    }
}
