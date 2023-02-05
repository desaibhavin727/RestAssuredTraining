package day5;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.Strings;


import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;



public class ParsingXmlResponse {

	@Test(priority=1)
	void TestXmlResponse() 
	
		{
		
		//approch 1
		
		/*given()
			
			
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?Page=1")
			
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));   //Index start from 0
		
		*/
	
	
		//Approch 2
		
		Response res =
		
		given()
		
		
		.when()
				.get("http://restapi.adequateshop.com/api/Traveler?Page=1");
		
		
			Assert.assertEquals(res.getStatusCode(),200);
			Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
			
			
			String PageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
			Assert.assertEquals(PageNo, "1");

		
			
			String Name = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
			Assert.assertEquals(Name, "Developer");
			
		
		}
		
	@Test(priority=2)
	void TestXmlResponseBody() 
	
	{
		
		
		Response res =
				
				given()
				
				
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?Page=1");
		
				
				XmlPath xmlobj = new XmlPath(res.asString());
				
				
				//verifying totalnumber of travellers
				List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
				Assert.assertEquals(travellers.size(),10);
	
	
				//verifying travellers name is presen in response
				List<String> traveller_Name = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
				
				
				boolean Status = false;
				for(String travellersName:traveller_Name)
				{
					if(travellersName.equals("Developer"))
					{
						
						Status = true;
						break;
						
					}
					
				}
				
				Assert.assertEquals(Status, true);
				
	
	}	
}
