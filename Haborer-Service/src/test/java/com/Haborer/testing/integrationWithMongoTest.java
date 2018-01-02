package com.Haborer.testing;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Haborer.DB.HaborerDBHandler;
import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.MakatItem;
import com.Haborer.Entities.Request;
import com.Haborer.Entities.RequestStatus;
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
		dbHandler.getMdb().createCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("156", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("157", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("156", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("157", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection("Requests", new BasicDBObject());


	}

	@AfterEach
	void tearDown() throws Exception {
		dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("156", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("157", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("156", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("157", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection("Requests").drop();

	}
	

	@Test
	void testGetAllSquadronsNames() {
		List<String> squadNames=squaDao.getAllSqadronNames();
		assertEquals(3, squadNames.size());
		assertTrue(squadNames.contains("Squadron 155"));
		assertTrue(squadNames.contains("Squadron 156"));
		assertTrue(squadNames.contains("Squadron 157"));

	}
	@Test
	public void testAddItem() {
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).count());
		CountItem countItem=createCountItem("155");
		MakatItem makatItem=createMakayItem("155");
		squaDao.addItem(countItem);
		squaDao.addItem(makatItem);
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).count());
		String countId=countItem.get_id();
		String makatId=makatItem.get_id();
		DBObject dbObject=dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).findOne();
		JSONObject object= new JSONObject(JSON.serialize(dbObject));
		CountItem item=(CountItem) EntitiesJsonToObjectsParser.parseToItem(object.toString());
		assertEquals(countId, item.get_id());
	}
	@Test
	public void testDeleteItem() {
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).count());
		CountItem countItem=createCountItem("155");
		MakatItem makatItem=createMakayItem("155");
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(countItem), dbHandler.getCollName("155", CountItem.class.getSimpleName()));
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(makatItem), dbHandler.getCollName("155", MakatItem.class.getSimpleName()));
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).count());
		squaDao.deleteItem(countItem);
		squaDao.deleteItem(makatItem);
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).count());

	}
	@Test
	public void testUpdateItem() {
		assertEquals(0, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		CountItem countItem=createCountItem("155");
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(countItem), dbHandler.getCollName("155", CountItem.class.getSimpleName()));
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		countItem.setItemCategory("categoryTest");
		squaDao.updateItem(countItem);
		assertEquals(1, dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).count());
		DBObject dbObject=dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).findOne();
		JSONObject object= new JSONObject(JSON.serialize(dbObject));
		CountItem item=(CountItem) EntitiesJsonToObjectsParser.parseToItem(object.toString());
		assertEquals("categoryTest", item.getItemCategory());
	}
	@Test
	public void testUpdateRequest() {
		assertEquals(0, dbHandler.getCollection("Requests").count());
		Request request=createNewRequest();
		dbHandler.insertObj(EntitiesJsonToObjectsParser.objectToDBObject(request),"Requests");
		assertEquals(1, dbHandler.getCollection("Requests").count());
		request.setStatus(RequestStatus.APPROVED);
		squaDao.updateRequest(request);
		assertEquals(1, dbHandler.getCollection("Requests").count());
		DBObject dbObject=dbHandler.getCollection("Requests").findOne();
		JSONObject object= new JSONObject(JSON.serialize(dbObject));
		Request item=(Request) EntitiesJsonToObjectsParser.parseToRequest(object.toString());
		assertEquals(RequestStatus.APPROVED, item.getStatus());
	}
	
	
	public CountItem createCountItem(String squadron) {
		CountItem item=new CountItem();
		item.setSquadron("155");
		return item;
	}
	public MakatItem createMakayItem(String squadron) {
		MakatItem item=new MakatItem();
		item.setSquadron("155");
		return item;
	}
	public Request createNewRequest() {
		Request request=new Request();
		request.setStatus(RequestStatus.APPROVED);
		request.setComments("Testing");
		return request;
	
		
	}

}
