package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;



public class ParsingJSONResponseData {

	
//	@Test(priority=1)
	void testJsonResponse() 
	{
		
		//Approch 1
		
		/*given()
				.contentType(ContentType.JSON)
	
			.when()
				.get("https://reqres.in/api/users?page=2")
	
	
			.then()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.body("data[4].avatar", equalTo("https://reqres.in/img/faces/11-image.jpg"));
		 */
		
		//Approch 2
		
		/*Response res = given()
				.contentType(ContentType.JSON)
				
				.when()
				.get("https://reqres.in/api/users?page=2");
		
		
		Assert.assertEquals(res.getStatusCode(),200); //Validation 1
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		
		String BookName = res.jsonPath().get("data[3].avatar").toString();
		Assert.assertEquals(BookName, "https://reqres.in/img/faces/10-image.jpg");
		
		*/
		
	}
	
	@Test(priority=2)
	void testJsonResponseBodyData() 
	{
		
		
		Response res = 
				
		given()
			.contentType(ContentType.JSON)
				
		.when()
			.get("https://reqres.in/api/users?page=2");
		
		
	/*	Assert.assertEquals(res.getStatusCode(),200); //Validation 1
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		
		String BookName = res.jsonPath().get("data[3].avatar").toString();
		Assert.assertEquals(BookName, "https://reqres.in/img/faces/10-image.jpg");
	*/	
			
		// USING JSONOBJECT CLASS
		JSONObject jo = new JSONObject(res.asString()); //converting response to json object type
		
		
		//PRint all LAStname
		
		boolean status = false;
		
		for(int i =0; i<jo.getJSONArray("data").length();i++)
		{
			String lastname = jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();			
			if(lastname.equals("Lawson"))
			{
				status=true;
				break;
				
			}
		}
		
		Assert.assertEquals(status, true);
	
		
		
	}
		
	
}
