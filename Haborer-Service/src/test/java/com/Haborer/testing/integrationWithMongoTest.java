package com.Haborer.testing;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.xml.crypto.Data;

import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Haborer.DB.HaborerDBHandler;
import com.Haborer.DB.Utilities;
import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.MakatItem;
import com.Haborer.Entities.Request;
import com.Haborer.Entities.RequestStatus;
import com.Haborer.Entities.User;
import com.Haborer.rest.SquadronDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

class integrationWithMongoTest {
	private static  HaborerDBHandler dbHandler;
	private SquadronDao squaDao=new SquadronDao();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dbHandler=new HaborerDBHandler();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		dbHandler.closeConnection();
	}

	@BeforeEach
	void setUp() throws Exception {
		Utilities.RequestCollectionName="Requests-Test";
		Utilities.UsersCollectionName="Users-Test";
		dbHandler.getMdb().createCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("Test156", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("Test157", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("Test156", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("Test157", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(Utilities.RequestCollectionName, new BasicDBObject());
		dbHandler.getMdb().createCollection(Utilities.UsersCollectionName, new BasicDBObject());



	}

	@AfterEach
	void tearDown() throws Exception {
		dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("Test156", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("Test157", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("Test156", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("Test157", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(Utilities.RequestCollectionName).drop();
		dbHandler.getCollection(Utilities.UsersCollectionName).drop();
		Utilities.RequestCollectionName="Requests";
		Utilities.UsersCollectionName="Users";


	}
	@Test
	public void testGetSquadron() {
		List<Item> squadronItems=new ArrayList<>();
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		for(int i=0;i<10;i++) {
			squadronItems.add(createCountItem("Test155"));
			squadronItems.add(createMakatItem("Test155"));
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(squadronItems.get(i*2)), dbHandler.getCollName("Test155", CountItem.class.getSimpleName()));
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(squadronItems.get(i*2+1)), dbHandler.getCollName("Test155", MakatItem.class.getSimpleName()));

		}
		assertEquals(10, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(10, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		List<? extends Item> squadronItemList=squaDao.getSquadron("Test155");
		assertEquals(squadronItems.size(), squadronItemList.size());
		for(int i=0;i<squadronItemList.size();i++) {
			assertTrue(squadronItems.get(i).equals(squadronItemList.get(i)));
		}
			
	}

	@Test
	void testGetAllSquadronsNames() {
		List<String> squadNames=squaDao.getAllSqadronNames();
		assertEquals(3, squadNames.size());
		assertTrue(squadNames.contains("Test155"));
		assertTrue(squadNames.contains("Test156"));
		assertTrue(squadNames.contains("Test157"));

	}
	@Test
	public void testAddItem() {
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		CountItem countItem=createCountItem("Test155");
		MakatItem makatItem=createMakatItem("Test155");
		squaDao.addItem(countItem);
		squaDao.addItem(makatItem);
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		String countId=countItem.get_id();
		String makatId=makatItem.get_id();
		DBObject dbObject=dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).findOne();
		JSONObject object= new JSONObject(JSON.serialize(dbObject));
		CountItem item=(CountItem) EntitiesJsonToObjectsParser.parseToItem(object.toString(),false);
		assertEquals(countId, item.get_id());
	}
	@Test
	public void testDeleteItem() {
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		CountItem countItem=createCountItem("Test155");
		MakatItem makatItem=createMakatItem("Test155");
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(countItem), dbHandler.getCollName("Test155", CountItem.class.getSimpleName()));
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(makatItem), dbHandler.getCollName("Test155", MakatItem.class.getSimpleName()));
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		squaDao.deleteItem(countItem);
		squaDao.deleteItem(makatItem);
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());

	}
	@Test
	public void testUpdateItem() {
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		CountItem countItem=createCountItem("Test155");
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(countItem), dbHandler.getCollName("Test155", CountItem.class.getSimpleName()));
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		countItem.setItemCategory("categoryTest");
		squaDao.updateItem(countItem);
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		DBObject dbObject=dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).findOne();
		JSONObject object= new JSONObject(JSON.serialize(dbObject));
		CountItem item=(CountItem) EntitiesJsonToObjectsParser.parseToItem(object.toString(),false);
		assertEquals("categoryTest", item.getItemCategory());
	}
	@Test
	public void testUpdateRequest() {
		assertEquals(0, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		Request request=createNewRequest();
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(request),Utilities.RequestCollectionName);
		assertEquals(1, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		request.setStatus(RequestStatus.APPROVED);
		squaDao.updateRequest(request);
		assertEquals(1, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		DBObject dbObject=dbHandler.getCollection(Utilities.RequestCollectionName).findOne();
		JSONObject object= new JSONObject(JSON.serialize(dbObject));
		Request item=(Request) EntitiesJsonToObjectsParser.parseToRequest(object.toString());
		assertEquals(RequestStatus.APPROVED, item.getStatus());
	}
	@Test
	public void testGetSquadronRequestFrom() {
		assertEquals(0, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		for(int i=0;i<20;i++) {
			Request request=createNewRequest();
			if(i%2==0) {
				request.setFromSquadron("Test155");
			}else {
				request.setFromSquadron("Test156");

			}
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(request),Utilities.RequestCollectionName);

		}
		assertEquals(20, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		List<Request> requests=squaDao.getSquadronRequestsFrom("Test155");
		assertEquals(10, requests.size());
		for(Request request:requests) {
			assertEquals("Test155", request.getFromSquadron());
		}


	}
	@Test
	public void testComplicatedRequestUpdateMakatItem() {
		assertEquals(0, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		Request request=createNewRequest();
		request.setFromSquadron("Test155");
		request.setToSquadron("Test156");
		request.setStatus(RequestStatus.APPROVED);
		request.setItem(createMakatItem("Test155"));
		squaDao.addItem(request.getItem());
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(request),Utilities.RequestCollectionName);
		assertEquals(1, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test156", MakatItem.class.getSimpleName())).count());

		request.setStatus(RequestStatus.TAKEN);
		squaDao.updateRequest(request);
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test156", MakatItem.class.getSimpleName())).count());

		request.setStatus(RequestStatus.RETURNED);
		squaDao.updateRequest(request);

		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", MakatItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test156", MakatItem.class.getSimpleName())).count());

		
	}
	
	@Test
	public void testComplicatedRequestUpdateCountItem() {
		assertEquals(0, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		Request request=createNewRequest();
		request.setFromSquadron("Test155");
		request.setToSquadron("Test156");
		request.setStatus(RequestStatus.APPROVED);
		request.setItem(createCountItem("Test155"));
		squaDao.addItem(request.getItem());
		((CountItem)request.getItem()).setItemCount(5);
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(request),Utilities.RequestCollectionName);
		assertEquals(1, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test156", CountItem.class.getSimpleName())).count());

		request.setStatus(RequestStatus.TAKEN);
		squaDao.updateRequest(request);
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test156", CountItem.class.getSimpleName())).count());
		
		DBObject dbObjectSquad155=dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).findOne();
		DBObject dbObjectSquad156=dbHandler.getCollection(dbHandler.getCollName("Test156", CountItem.class.getSimpleName())).findOne();

		JSONObject dbJsonObjectSquad155= new JSONObject(JSON.serialize(dbObjectSquad155));
		JSONObject dbJsonObjectSquad156= new JSONObject(JSON.serialize(dbObjectSquad156));

		CountItem itemSquad155= (CountItem) EntitiesJsonToObjectsParser.parseToItem(dbObjectSquad155.toString(), false);
		CountItem itemSquad156= (CountItem) EntitiesJsonToObjectsParser.parseToItem(dbObjectSquad156.toString(), false);
		assertEquals(10, itemSquad155.getItemCount());
		assertEquals(5, itemSquad156.getItemCount());

		request.setStatus(RequestStatus.RETURNED);
		squaDao.updateRequest(request);

		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("Test156", CountItem.class.getSimpleName())).count());

		 dbObjectSquad155=dbHandler.getCollection(dbHandler.getCollName("Test155", CountItem.class.getSimpleName())).findOne();
		 dbJsonObjectSquad155= new JSONObject(JSON.serialize(dbObjectSquad155));
		 itemSquad155= (CountItem) EntitiesJsonToObjectsParser.parseToItem(dbObjectSquad155.toString(), false);
		assertEquals(15, itemSquad155.getItemCount());

		
	}
	
	
	@Test
	public void testAddNewRequests() {
		assertEquals(0, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		squaDao.addNewRequests(createItemRequestsFactory());
		assertEquals(20, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		assertEquals(20, squaDao.getSquadronRequestsTo("Test156").size());
		assertEquals(20, squaDao.getSquadronRequestsFrom("Test155").size());


	}
	@Test
	public void testLogin() {
		assertEquals(0, dbHandler.getCollection(Utilities.UsersCollectionName).count());
		User user=getUser();
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(user),Utilities.UsersCollectionName);
		assertEquals(1, dbHandler.getCollection(Utilities.UsersCollectionName).count());
		User shouldBeNotEqual=squaDao.login("ss", "waa");
		User shouldBeEqual=squaDao.login("ido", "nissan");
		assertTrue(!user.equals(shouldBeNotEqual));
		assertTrue(user.equals(shouldBeEqual));



	}
	@Test
	public void testGetSquadronRequestTo() {
		assertEquals(0, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		for(int i=0;i<20;i++) {
			Request request=createNewRequest();
			if(i%2==0) {
				request.setToSquadron("Test155");
			}else {
				request.setToSquadron("Test156");

			}
			dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(request),Utilities.RequestCollectionName);

		}
		assertEquals(20, dbHandler.getCollection(Utilities.RequestCollectionName).count());
		List<Request> requests=squaDao.getSquadronRequestsTo("Test155");
		assertEquals(10, requests.size());
		for(Request request:requests) {
			assertEquals("Test155", request.getToSquadron());
		}


	}
	
	public static CountItem createCountItem(String squadron) {
		CountItem item=new CountItem();
		item.setSquadron(squadron);
		item.setItemCount(15);
		return item;
	}
	public static MakatItem createMakatItem(String squadron) {
		MakatItem item=new MakatItem();
		item.setSquadron(squadron);
		return item;
	}
	public static Request createNewRequest() {
		Request request=new Request();
		request.setStatus(RequestStatus.APPROVED);
		request.setComments("Testing");
		return request;
	
		
	}
	public static User getUser() {
		User user=new User();
		user.setUserName("ido");
		user.setPassword("nissan");
		return user;
	}
	public static ItemRequestsFactory createItemRequestsFactory() {
		ArrayList<Item> squadronItems=new ArrayList<>();
		for(int i=0;i<10;i++) {
			squadronItems.add(createCountItem("Test155"));
			squadronItems.add(createMakatItem("Test155"));
		}

		ItemRequestsFactory factory=new ItemRequestsFactory();
		factory.setFromSquadron("Test155");
		factory.setToSquadron("Test156");
		factory.setfDate(new Date());
		factory.settDate(new Date());
		factory.setItems(squadronItems);
		factory.setComments("Fast as possible!");
		
		
		
		
		
		
		
		
		return factory;
		
	}

	

}
