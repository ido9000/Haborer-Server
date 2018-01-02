package com.Haborer.testing;
import java.util.Map;
import java.util.Set;
import org.bson.BSONObject;
import org.bson.Document;

import com.Haborer.DB.DBHandler;
import com.Haborer.DB.HaborerDBHandler;
import com.mongodb.*;

public class dbtest {
    public static void main(String[] args) {
        BasicDBObject document = new BasicDBObject();
        BasicDBObject documento = new BasicDBObject();
        document.put("pop", 34);
        documento.put("pop", 35);
        documento.put("name", "avi");
        HaborerDBHandler handler = new HaborerDBHandler();
        
        /*Test Insert Object: */ 
        handler.insertObj(document, "Men");
        
        /*Test Update Object: */
        handler.updateObj(document, documento, "Men");

        /*Test Remove Object: */
        handler.removeObj(document, "Men");

        System.out.print("Finish Test1...");
    }
}