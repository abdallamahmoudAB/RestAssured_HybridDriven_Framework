package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

// Perform CRUD Operations for User API

public class UserEndPointsUsingPropertiesFile {

    // load and read data from properties file
    static ResourceBundle getURL(){
        // load properties file
        ResourceBundle routes = ResourceBundle.getBundle("routes"); // name of properties file
        return routes;
    }

    public static Response createUser(User payload){

       String post_url = getURL().getString("post_url");

           Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                    .when()
                .post(post_url);

           return response;
    }

    public static Response ReadUser(String userName){

        String get_url = getURL().getString("get_url");

        Response response = given()
                .pathParam("username", userName)

                .when()
                .get(get_url);

        return response;
    }

    public static Response UpdateUser(String userName, User payload){

        String update_url = getURL().getString("update_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)

                .when()
                .put(update_url);

        return response;
    }

    public static Response DeleteUser(String userName){

        String delete_url = getURL().getString("delete_url");

        Response response = given()
                .pathParam("username", userName)

                .when()
                .delete(delete_url);

            return response;
    }

}
