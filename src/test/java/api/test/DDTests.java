package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		Response response1 = UserEndPoints.readUser(userName);
		Assert.assertTrue(response1.asString().contains("testuser")
				&& response1.asString().contains(fname)
				&& response1.asString().contains(lname)
				&& response1.asString().contains(useremail));
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertTrue(response.asString().contains("code")
				&& response.asString().contains("type")
				&& response.asString().contains("message"));
		Assert.assertEquals(response.getStatusCode(), 200);

		Response response1 = UserEndPoints.verifyDeletedUser(userName);
		Assert.assertTrue(response1.asString().contains("error")
				&& response1.asString().contains("User not found"));
	}
}
