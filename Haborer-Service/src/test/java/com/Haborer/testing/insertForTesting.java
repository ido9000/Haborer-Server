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
import com.Haborer.rest.SquadronDao;

public class insertForTesting {

	public static void main(String[] args) {
	   HaborerDBHandler dbHandler=new HaborerDBHandler();		
	   SquadronDao squadronDao=new SquadronDao();
		ArrayList<Item> squadronItems=new ArrayList<>();
		for(int i=0;i<10;i++) {
			squadronItems.add(integrationWithMongoTest.createCountItem("156"));
			squadronItems.add(integrationWithMongoTest.createMakatItem("156"));
		}
		for(Item item:squadronItems) {
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(item),  dbHandler.getCollName("156", CountItem.class.getSimpleName()));
		}
		ItemRequestsFactory factory=new ItemRequestsFactory();
		factory.setFromSquadron("156");
		factory.setToSquadron("155");
		factory.setfDate(new Date());
		factory.settDate(new Date());
		factory.setItems(squadronItems);
		factory.setComments("Fast as possible!");
		squadronDao.addNewRequests(factory);;
	}

}
