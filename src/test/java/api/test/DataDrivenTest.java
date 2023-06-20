package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void postUser(String userID, String userName, String firstName, String lastName, String Email, String Password, String Phone){

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void DeleteUserByName(String userName){

        Response response = UserEndPoints.DeleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}
