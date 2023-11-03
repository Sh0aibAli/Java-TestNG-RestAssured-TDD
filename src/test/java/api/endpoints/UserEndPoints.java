package api.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class UserEndPoints {

	static String json_schema_path = ".\\testData\\json_schema.json";
	static String faker_json_path = ".\\testData\\faker_json_schema.json";

	//method created for getting url's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
		return routes;
	}

	public static Response createUser(User payload)
	{
		
		String post_url = getURL().getString("post_url");

		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url);

//		response.then().assertThat().body(matchesJsonSchemaInClasspath("json_schema.json"));
		response.then().assertThat().body(matchesJsonSchema(new File(json_schema_path)));

//		response.then().assertThat().statusCode(HttpStatus.SC_CREATED);
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
		return response;
	}

	public static Response readUser(String userName)
	{
		String get_url = getURL().getString("get_url");

		Response response=given()
						.pathParam("username",userName)
		.when()
			.get(get_url);
		response.then().assertThat().body(matchesJsonSchema(new File(faker_json_path)));
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
		return response;
	}

	public static Response verifyDeletedUser(String userName)
	{
		String get_url = getURL().getString("get_url");

		Response response=given()
				.pathParam("username",userName)
				.when()
				.get(get_url);
		response.then().assertThat().body(matchesJsonSchema(new File(json_schema_path)));
		response.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url = getURL().getString("update_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(update_url);
		response.then().assertThat().body(matchesJsonSchema(new File(json_schema_path)));
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
		Response response = given()
			.pathParam("username", userName)
			
		.when()
			.delete(delete_url);
		response.then().assertThat().body(matchesJsonSchema(new File(json_schema_path)));
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
		return response;
	}
}
