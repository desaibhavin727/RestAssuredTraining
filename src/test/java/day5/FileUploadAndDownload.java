package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadAndDownload {

	@Test(priority=1)
	void SingleFileUpload() {
		
			File myFile = new File("path/text.txt");

		given()
			.multiPart("file", myFile)			//important line
			.contentType("multipart/form-data")  //imp
			
	
		.when()
			.post("www.mywebsite.com/uploadfile")
		
			
		.then()
			.statusCode(200)
			.body("fileName", equalTo("Text.twt"))
			.log().all();
			
	}
	
	@Test(priority=2)
	void MultipleFileUpload() //wont work for all kind api
	
	{
		
		File myFile1 = new File("path/text1.txt");
		File myFile2 = new File("path/text2.txt");
		
		
		File filearr[]= {myFile1, myFile2};
		
		given()
			.multiPart("files", filearr)			//important line
			.contentType("multipart/form-data")  //imp
		
		
		.when()
			.post("www.mywebsite.com/uploadfilemultiple")
		
		
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Text1.twt"))
			.body("[1].fileName", equalTo("Text2.twt"))
			.log().all();
		
	}
	
	
	@Test(priority=3)
	void fileDownload() 
	{
		
		given()
		
		.when()
			.get("www.download.com/download/text.txt")
		
		
		.then()
			.statusCode(200)
			.log().body();
		
	}

}
