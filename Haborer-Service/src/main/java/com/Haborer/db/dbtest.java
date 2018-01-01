package main.java.com.Haborer.db;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.Document;

import com.mongodb.*;
import com.Haborer.db.DBHandler;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.DBObjectCodec;
import com.mongodb.Mongo;

public class dbtest {
    public static void main(String[] args) {

        // BasicDBObject nor= new BasicDBObject();
        // nor.put("name", "achi");
        // nor.put("age", 25);
        // nor.put(("name"),"johan");
        // Document doc= new Document("pop", 34);
        // BasicDBObject doca = new BasicDBObject(doc);
        // Document doc2= new Document("pop", 35);
        BasicDBObject document = new BasicDBObject();
	    document.put("database", "mkyongDB");
	    document.put("table", "hosting");
        DBHandler handler = new DBHandler();
        handler.insertObject(document, "Men");
//        handler.updateObject(doc,doc2, "Men");
       // handler.removeObj(doc, "Men");
        System.out.print(5555);
    }
}