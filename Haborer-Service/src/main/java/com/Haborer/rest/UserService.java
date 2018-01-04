package com.Haborer.rest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.Haborer.Entities.CountItem;
import com.Haborer.Entities.EntitiesJsonToObjectsParser;
import com.Haborer.Entities.Item;
import com.Haborer.Entities.ItemRequestsFactory;
import com.Haborer.Entities.MakatItem;
import com.Haborer.Entities.Request;
import com.Haborer.Entities.User;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;  

@Path("/UserService") 
public class UserService {  
	private SquadronDao squadronDao = new SquadronDao();  

   @GET
   @Path("/Squadron/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<? extends Item> getSquadronItems(@PathParam("Squadronid") int squadronId){
      return squadronDao.getSquadron(String.valueOf(squadronId));
   }
   
   @GET
   @Path("/Squadron/Requests/From/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Request> getSquadronRequestsFrom(@PathParam("Squadronid") int squadronId){
      return squadronDao.getSquadronRequestsFrom(String.valueOf(squadronId));
   }
   
   @GET
   @Path("/Squadron/Requests/To/{Squadronid}")
   @Produces(MediaType.APPLICATION_JSON)
   public List <Request> getSquadronRequestsTo(@PathParam("Squadronid") int squadronId){
      return squadronDao.getSquadronRequestsTo(String.valueOf(squadronId));
   }
   @GET
   @Path("/Sqaudron/GetAllSquadrons")
   @Produces(MediaType.APPLICATION_JSON)
   public List<String> getAllSquadronsNames(){
	   System.out.println("Receving request");
	   return squadronDao.getAllSqadronNames();
   }
   
   @POST
   @Path("/Squadron/AddItem")
   @Consumes(MediaType.TEXT_PLAIN)
   public Response addItem(String itemJson) {
	   return squadronDao.addItem(EntitiesJsonToObjectsParser.parseToItem(itemJson,true));	   
   }
   
   

   @POST
   @Path("/Squadron/NewRequest")
   @Consumes(MediaType.TEXT_PLAIN)
   public Response addRequests(String factoryJson) {
	   return squadronDao.addNewRequests(EntitiesJsonToObjectsParser.getItemRequstsFactory(factoryJson));
	   
   }



   @POST
   @Path("/Sqaudron/RequestRespond")
   @Consumes(MediaType.TEXT_PLAIN)
   public Response updateRequest(String requestJson) {
	   return squadronDao.updateRequest(EntitiesJsonToObjectsParser.parseToRequest(requestJson));
	   
   }
   
   @POST
   @Path("/Squadron/Login")
   @Consumes(MediaType.TEXT_PLAIN)
   public User login(@Context HttpHeaders headers) {
	   String userName=headers.getRequestHeader("userName").get(0);
	   String password=headers.getRequestHeader("password").get(0);

	   return squadronDao.login(userName,password);
	   
   }

   @POST
   @Path("Squadron/DeleteItem")
   @Consumes(MediaType.TEXT_PLAIN)
   public Response deleteItem(String itemJson) {
	   return squadronDao.deleteItem(EntitiesJsonToObjectsParser.parseToItem(itemJson,false));
   }
   @POST
   @Path("Squadron/UpdateItem")
   @Consumes(MediaType.TEXT_PLAIN)
   public Response updateItem(String itemJson) {
	   return squadronDao.updateItem(EntitiesJsonToObjectsParser.parseToItem(itemJson,true));
   }

}