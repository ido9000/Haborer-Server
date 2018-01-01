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
public DBHandler()
{
    if (db == null) {

        connectionString= new  MongoClientURI(ClientURI);
        mongoClient = new MongoClient(connectionString);
        db = mongoClient.getDatabase(DBName);
        collection=db.getCollection(CollectionName);
    }
}

public void insertObject(DBObject obj) {
    try {
         collection.insertOne(obj);
    }

    catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateObject( DBObject oldObj,DBObject newObj) {
    try{
        DBObject updateObject = new DBObject();
        updateObject.put("$set", newObj);
        collection.update(oldObj, updateObject);
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

public void removeObj(DBObject obj) {
    try {
        collection.remove(obj);
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

}