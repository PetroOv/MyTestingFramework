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
        setURL(cs.environment.getAPIAddress());
    }

    @Override
    public <T> Response post(T bodyData) {
        return null;
    }

    @Override
    public Response get() {
        return given().contentType(contentType).when().get(getURL());
    }

    @Override
    public <T> Response patch(int id, T bodyData) {
        return null;
    }

    @Override
    public <T> Response put(int id, T bodyData) {
        return null;
    }

}
