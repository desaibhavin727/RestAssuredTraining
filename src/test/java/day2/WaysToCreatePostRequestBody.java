package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
Diffrent way to create post request body
1- using Hashmap
2- using org.json
3- Pojo class
4- external Json file data
*/


//1)- Post Request USing Hashmap 

public class WaysToCreatePostRequestBody {

//@Test(priority=1)
void testUsingHashmap() {
	
	HashMap data = new HashMap();
	
	data.put("name","scott");
	data.put("loacation", "France");
	data.put("phone", "123456");
	
	String courseArr[]= {"C","C++"};
	data.put("courses", courseArr);
	
	
	given()
		.contentType("application/json")
		.body(data)
	
	.when()
		.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type","appliation/json; charset=utf-8")
		.log().all();
	
}

// Deleting Student Record
	
//@Test(priority=2)
void testDelete() {
	
	 given()
	
	.when()
		.delete("https://reqres.in/api/users/3")
	
	.then()
		.statusCode(200);
	
}


//2)- Post Request using org.json Library


//@Test(priority=1)
void testUsingorgJsonLibrary() {
	
	JSONObject data = new JSONObject();
	
	data.put("name", "Scott");
	data.put("loacation", "France");
	data.put("Phone", "123456");
	
	String courseArr[]= {"C","C++"};
	data.put("courses", courseArr);
	
	
	given()
		.contentType("application/json")
		.body(data.toString())      //add tostring
	
	.when()
		.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type","appliation/json; charset=utf-8")
		.log().all();
	
}


@Test(priority=2)
void testdelete() {
	
	 given()
	
	.when()
		.delete("https://reqres.in/api/users/3")
	
	.then()
		.statusCode(200);
	
}

// 3) - Post Request plain old java object

//@Test(priority=1)
void testUsingPOJO() {
	
	
	Pojo_PostRequest data = new Pojo_PostRequest();
	
	data.setName("Scott");
	data.setLoacation("France");
	data.setPhone("123456");
	
	String courseArr[]= {"C","C++"};	
	data.setCourses(courseArr);
	
	
	given()
		.contentType("application/json")
		.body(data)      
	
	.when()
		.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type","appliation/json; charset=utf-8")
		.log().all();
	
}

//@Test(priority=2)
void testdeletePOJO() {
	
	 given()
	
	.when()
		.delete("https://reqres.in/api/users/3")
	
	.then()
		.statusCode(200);
	
}

// 4)- Post Using External Json File


@Test(priority=1)
void testUsingExternalJson() throws FileNotFoundException {
	
	File f = new File(".\\BODY.json");
	
	FileReader fr = new FileReader(f);
	
	JSONTokener jt = new JSONTokener(fr);
	
	JSONObject data = new JSONObject(jt);
	
	given()
		.contentType("application/json")
		.body(data.toString())      //toString
	
	.when()
		.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type","appliation/json; charset=utf-8")
		.log().all();
	
}

@Test(priority=2)
void testdeleteExtenal() {
	
	 given()
	
	.when()
		.delete("https://reqres.in/api/users/3")
	
	.then()
		.statusCode(200);
	
}


	
}
