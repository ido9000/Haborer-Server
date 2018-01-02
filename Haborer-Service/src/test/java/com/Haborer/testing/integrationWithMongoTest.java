package com.Haborer.testing;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Haborer.DB.HaborerDBHandler;
import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.MakatItem;
import com.Haborer.rest.SquadronDao;
import com.mongodb.BasicDBObject;

class integrationWithMongoTest {
	private static  HaborerDBHandler dbHandler;
	private SquadronDao squaDao=new SquadronDao();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dbHandler=new HaborerDBHandler();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dbHandler.getMdb().createCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("156", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("157", CountItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("156", MakatItem.class.getSimpleName()), new BasicDBObject());
		dbHandler.getMdb().createCollection(dbHandler.getCollName("157", MakatItem.class.getSimpleName()), new BasicDBObject());


	}

	@AfterEach
	void tearDown() throws Exception {
		dbHandler.getCollection(dbHandler.getCollName("155", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("156", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("157", CountItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("155", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("156", MakatItem.class.getSimpleName())).drop();
		dbHandler.getCollection(dbHandler.getCollName("157", MakatItem.class.getSimpleName())).drop();
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
		ObjectId countId=countItem.getItemId();
		ObjectId makatId=makatItem.getItemId();
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

}
