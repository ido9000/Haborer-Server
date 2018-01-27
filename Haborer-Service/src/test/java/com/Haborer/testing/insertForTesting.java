package com.Haborer.testing;

import java.util.ArrayList;
import java.util.Date;

import com.Haborer.DB.HaborerDBHandler;
import com.Haborer.DB.Utilities;
import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.MakatItem;
import com.Haborer.Entities.Request;
import com.Haborer.Entities.User;
import com.Haborer.rest.SquadronDao;

public class insertForTesting {

	public static void main(String[] args) {
		
	   HaborerDBHandler dbHandler=new HaborerDBHandler();	
	   
	   SquadronDao squadronDao=new SquadronDao();
		ArrayList<Item> squadronItems=new ArrayList<>();
		for(int i=0;i<10;i++) {
			//squadronItems.add(integrationWithMongoTest.createCountItem("דרג:ד"));
			squadronItems.add(integrationWithMongoTest.createMakatItem("דרג:ד"));
		}
		for(Item item:squadronItems) {
			//dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(item),  dbHandler.getCollName("דרג:ד", CountItem.class.getSimpleName()));
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(item),  dbHandler.getCollName("דרג:ד", MakatItem.class.getSimpleName()));

		}
	/*
	   User user=new User("fName","lName","דרג-ד","userD","passwordD");
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(user),Utilities.UsersCollectionName);
*/
	}

}
