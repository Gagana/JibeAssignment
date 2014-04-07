package com.jibe.assignment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/*import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.apphosting.api.DatastorePb.DatastoreService;
import com.google.apphosting.datastore.EntityV4.Key;*/

@Path("/services")
public class ReadFileServlet {

	static int count = 0;
		
	@Context
	ServletContext context;

	@GET
	@Path("/readfile/")
	@Produces(MediaType.APPLICATION_JSON)
	public String DisplayInJSON() throws IOException, JSONException {
				
		String filename = "/WEB-INF/DataFile";
		int numberoflines = 1;
		String text;
		JSONObject JsonText = new JSONObject();
		InputStream istream = context.getResourceAsStream(filename);
				
		try {
			InputStreamReader isreader = new InputStreamReader(istream);
			BufferedReader textReader = new BufferedReader(isreader);

			while ((text = textReader.readLine()) != null) {
				if (numberoflines > 10) {
					break;
				}
		
				JsonText.append("Line-"+numberoflines, text);
				
				numberoflines++;
			}
			textReader.close();
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());

		}
		count++;
	return JsonText.toString();
	}
	
	@GET
	@Path("/getcount/")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetCount() throws JSONException{
		
		JSONObject JsonCount = new JSONObject();
		JsonCount.put("The Readfile firstendpoint has been called-", count+" times!!");
		return JsonCount.toString();

		
		/*		
		 * I also tried Google Appengine Datastore
		 * 
		 * com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				com.google.appengine.api.datastore.Key todoKey = KeyFactory.createKey("ReadFile", "ReadFile");
		        Entity entity;
		      //  datastore.delete(todoKey);
		       try {
		            entity = datastore.get(todoKey);
		           // datastore.delete(todoKey);
		             long count = (long) entity.getProperty("count");
		             //int count = Integer.parseInt(count1);
		             JsonText.put("Counr Before -", count);
		            entity.setProperty("count", count++); 
		            datastore.put(entity);
		            JsonText.put("This is called-", count);
		        } catch (EntityNotFoundException e) {
		        	int count1 = 3;
		        	entity = new Entity("ReadFile", "ReadFile");
		        	entity.setProperty("count", count1);
		        	datastore.put(entity);            
		        	JsonText.put("First Time Exception-", count1);
		        }      
			    		
		        catch (Exception e)
		        {
		        	JsonText.put("Error-",e.toString());
		        }*/

		
		
		}

}
