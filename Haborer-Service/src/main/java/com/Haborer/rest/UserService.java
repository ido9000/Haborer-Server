package com.Haborer.rest;
import java.util.List;

import javax.ws.rs.Consumes;
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

@Path("/UserService") 
public class UserService {  
	SquadronDao SquadronDao = new SquadronDao();  
   
   @GET
   @Path("/Squadron/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronItems(@PathParam("Squadronid") int squadronId){
      return SquadronDao.getSquadron(String.valueOf(squadronId));
   }
   
   @GET
   @Path("/Squadron/Requests/From/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronRequestsFrom(@PathParam("Squadronid") int squadronId){
      return SquadronDao.getSquadronRequestsFrom(String.valueOf(squadronId));
   }
   
   @GET
   @Path("/Squadron/Requests/To/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronRequestsTo(@PathParam("Squadronid") int squadronId){
      return SquadronDao.getSquadronRequestsTo(String.valueOf(squadronId));
   }
   
   @PUT
   @Path("/Squadron/AddItem")
   @Consumes(MediaType.APPLICATION_JSON)
   public<T extends Item> Response addItem(Class<T> item) {
	   return null;
	   
   }
   
   @PUT
   @Path("/Squadron/NewRequest")
   @Consumes(MediaType.APPLICATION_JSON)
   public<T extends Item> Response addRequests(ItemRequestsFactory factory) {
	   return SquadronDao.addNewRequests(factory);
	   
   }

}