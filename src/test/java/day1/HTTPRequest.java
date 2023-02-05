package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {

	int id;

	@Test(priority = 1)
	void Listuser() {

		given()

		.when()
			.get("https://reqres.in/api/users?page=2")

		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();

	}

	@Test(priority = 2)
	void createuser() {

		HashMap data = new HashMap();
		data.put("name", "morpheus");
		data.put("job", "leader");

		id = given()
				.contentType("application/json").body(data)

		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");

		// .then()
		// .statusCode(201)
		// .log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "createuser" })

	void updateuser() {

		HashMap data = new HashMap();
		data.put("name", "Bhavin");
		data.put("job", "QA");

		given()
			.contentType("application/json")
			.body(data)

		.when()
			.put("https://reqres.in/api/users/" + id)

		.then()
			.statusCode(200)
			.log().all();
	}

	
	
	@Test(priority = 4)
	void detleteuser() {

		given()

		.when()
			.delete("https://reqres.in/api/users/" + id)

		.then()	
			.statusCode(204)
			.log().all();

	}

}
