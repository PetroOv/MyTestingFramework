package utils.api.healtcheck;

import io.restassured.response.Response;
import utils.api.BaseAPI;
import utils.api.contacts.ContactsAPI;

import static io.restassured.RestAssured.given;

public class HealtcheckAPI extends BaseAPI {
    String contentType = "application/json";

    public HealtcheckAPI() {
        super();
        setEndpointName(cs.api.getEndpointPathByName("healthcheck"));
    }

    @Override
    public Response post(String bodyData) {
        return null;
    }

    @Override
    public Response get() {
        return given().contentType(contentType).when().get(getURL());
    }

    @Override
    public Response patch(int id, String bodyData) {
        return null;
    }

    @Override
    public Response put(int id, String bodyData) {
        return null;
    }
}
