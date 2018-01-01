package com.Haborer.rest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.Request;  

@Path("/UserService") 
public class UserService {  
	SquadronDao squadronDao = new SquadronDao();  
   
   @GET
   @Path("/Squadron/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronItems(@PathParam("Squadronid") int squadronId){
      return squadronDao.getSquadron(String.valueOf(squadronId));
   }
   
   @GET
   @Path("/Squadron/Requests/From/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronRequestsFrom(@PathParam("Squadronid") int squadronId){
      return squadronDao.getSquadronRequestsFrom(String.valueOf(squadronId));
   }
   
   @GET
   @Path("/Squadron/Requests/To/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronRequestsTo(@PathParam("Squadronid") int squadronId){
      return squadronDao.getSquadronRequestsTo(String.valueOf(squadronId));
   }
   @GET
   @Path("/Sqaudron/GetAllSquadrons")
   @Produces(MediaType.APPLICATION_JSON)
   public List<String> getAllSquadronsNames(){
	   return squadronDao.getAllSqadronNames();
   }
   
   @PUT
   @Path("/Squadron/AddItem")
   @Consumes(MediaType.APPLICATION_JSON)
   public<T extends Item> Response addItem(T item) {
	   return squadronDao.addItem(item);
	   
   }
   
   @PUT
   @Path("/Squadron/NewRequest")
   @Consumes(MediaType.APPLICATION_JSON)
   public<T extends Item> Response addRequests(ItemRequestsFactory factory) {
	   return squadronDao.addNewRequests(factory);
	   
   }
   @POST
   @Path("/Sqaudron/RequestRespond")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateRequest(Request request) {
	   return squadronDao.updateRequest(request);
	   
   }
   @DELETE
   @Path("Squadron/DeleteItem")
   @Consumes(MediaType.APPLICATION_JSON)
   public <T extends Item> Response deleteItem(T item) {
	   return squadronDao.deleteItem(item);
   }
   @POST
   @Path("Squadron/UpdateItem")
   @Consumes(MediaType.APPLICATION_JSON)
   public <T extends Item> Response updateItem(T item) {
	   return squadronDao.updateItem(item);
   }

}