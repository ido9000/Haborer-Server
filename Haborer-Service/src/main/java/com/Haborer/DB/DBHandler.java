package DB;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import DB.Utilities;
import java.rmi.UnknownHostException;
import java.util.List;
import java.util.Set;

public class DBHandler {

private MongoClientURI connectionString = null;
private MongoClient mongoClient = null;
private MongoDatabase db = null;
private DBCollection collection = null;
public DBHandler() {
    if (db == null) {

        connectionString= new  MongoClientURI(Utilities.ClientURI);
        mongoClient = new MongoClient(connectionString);
        db = mongoClient.getDatabase(Utilities.DBName);
        collection=db.getCollection(CollectionName);
    }
}

public void insertObject(DBObject obj, String CollectionName) {
    try {
         collection=db.getCollection(CollectionName);
         collection.insertOne(obj);
    }

    catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateObject( DBObject oldObj,DBObject newObj, String CollectionName) {
    try{
        collection=db.getCollection(CollectionName);
        DBObject updateObject = new DBObject();
        updateObject.put("$set", newObj);
        collection.update(oldObj, updateObject);
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

public void removeObj(DBObject obj, String CollectionName) {
    try {
        collection=db.getCollection(CollectionName);
        collection.remove(obj);
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

}