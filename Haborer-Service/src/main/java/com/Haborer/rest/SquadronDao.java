package com.Haborer.rest;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;

import javax.ws.rs.core.Response;

import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.Request;  

public class SquadronDao { 

	
	public List<? extends Item> getSquadron(String squadron) {
		//TODO get all entities from collection called squadron
		return null;
	}
	public List<? extends Item> getSquadronRequestsFrom(String squadronFrom) {
		// TODO get all requests with fromSquadron=squadronFrom and pass them
		return null;
	}
	public List<? extends Item> getSquadronRequestsTo(String squadronTO) {
		// TODO get all requests with squadronTO=squadronTO and pass them
		return null;
	}
	public Response addNewRequests(ItemRequestsFactory factory) {
		ArrayList<Request> requests=factory.manageRequests();
		//mongo saving the request
		return null;
	}
	public Response updateRequest(Request request) {
		//TODO update request status in mongoDb,IF change to taken=>switch collection,same for returned
		return null;
	}
	public <T extends Item> Response deleteItem(T item) {
		// TODO Find the item in db with squadron property,and delete him from db
		return null;
	}
	public <T extends Item> Response updateItem(T item) {
		// TODO Find the item in db with squadron property,and update him in(mostly count item) db
		return null;
	}
	public <T extends Item> Response addItem(T item) {
		// TODO Add the item to the collection named item.squadron
		return null;
	}
	public List<String> getAllSqadronNames() {
		// TODO return all squadrons names from db
		return null;
	}    
}