import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

import java.rmi.UnknownHostException;
import java.util.List;
import java.util.Set;

public class Test {
//MongoClient mc = new MongoClient(new MongoClientURI("mongodb://Borer:12Borer@ds043200.mlab.com:43200/borer"));
//DB db = mc.getDatabase("Men");
//    public MongoClient getMc() {
//        return mc;
//    }
//
//    public void setMc(MongoClient mc) {
//        this.mc = mc;
//    }

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Borer:12Borer@ds043200.mlab.com:43200/borer"));
//        Insert a new object
        DB db=mongoClient.getDB("borer");
        DBCollection collection=db.getCollection("Men");
        DBObject nor= new BasicDBObject("name","b");
//        collection.insert(nor);
        collection.remove(nor);
        mongoClient.close();

    }
}
