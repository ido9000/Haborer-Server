package main.java.com.Haborer.db;
import java.util.Map;
import java.util.Set;
import org.bson.BSONObject;
import org.bson.Document;
import com.Haborer.db.DBHandler;
import com.mongodb.*;

public class dbtest {
    public static void main(String[] args) {
        BasicDBObject document = new BasicDBObject();
        BasicDBObject documento = new BasicDBObject();
        document.put("pop", 34);
        documento.put("pop", 35);
        documento.put("name", "avi");
        DBHandler handler = new DBHandler();
        
        /*Test Insert Object: */ 
        handler.insertObj(document, "Men");
        
        /*Test Update Object: */
        handler.updateObj(document, documento, "Men");

        /*Test Remove Object: */
        handler.removeObj(document, "Men");

        System.out.print("Finish Test1...");
    }
}