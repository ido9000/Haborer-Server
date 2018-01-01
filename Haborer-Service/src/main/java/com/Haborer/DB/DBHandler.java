package com.Haborer.db;
import db.Utilities;
import java.rmi.UnknownHostException;
import java.util.List;
import java.util.Set;
import org.bson.BSONObject;
import org.bson.Document;
import com.mongodb.*;

public class DBHandler {

// private static MongoClientURI connectionString = null;
// private static MongoClient mongoClient = null;
//private static MongoDatabase mdb = null;;
private static MongoURI connectionString = null;
private static Mongo mongoClient = null;
private static DB mdb = null

public DBHandler() {
    if (mdb == null) {
        connectionString= new MongoURI(Utilities.ClientURI);
        mongoClient = new Mongo(connectionString);
        mdb = mongoClient.getDB(Utilities.DBName);
    }
}

public void insertObject(DBObject obj, String CollectionName) {
    try{
         DBCollection collection = mdb.getCollection(CollectionName);
         collection.insert(obj);   
        }
    catch (Exception e) {
        e.printStackTrace();
    }
}

// public void updateObject(Document oldObj ,Document newObj, String CollectionName) {
//     try{   
//         mdb.getCollection(CollectionName).updateOne(oldObj,{$set:newObj});
//        }
//    catch (Exception e) {
//        e.printStackTrace();
//    }
// }
    //     collection=mdb.getCollection(CollectionName);;tionName);;
    //     collection=mdb.getCollection(CollectionName);    updateObject.put("$set", newObj);
    //     collection.update(oldObj, updateObject);
    // }
    // catch (Exception e) {
    //     e.printStackTrace();
    // }

// public void removeObj(Document obj, String CollectionName) {
//     try{   
//         mdb.getCollection(CollectionName).removeOne(obj);
//        }
//    catch (Exception e) {
//        e.printStackTrace();
//    }
//     }
}
