package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

// Perform CRUD Operations for User API

public class UserEndPoints {

    public static Response createUser(User payload){

           Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                    .when()
                .post(Routes.post_url);

           return response;
    }

    public static Response ReadUser(String userName){

        Response response = given()
                .pathParam("username", userName)

                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response UpdateUser(String userName, User payload){

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)

                .when()
                .put(Routes.update_url);

        return response;
    }

    public static Response DeleteUser(String userName){

        Response response = given()
                .pathParam("username", userName)

                .when()
                .put(Routes.delete_url);

            return response;
    }

}
