package utils.api.application;

import io.restassured.response.Response;
import utils.api.BaseAPI;

import static io.restassured.RestAssured.given;

public class ApplicationAPI extends BaseAPI {

    public ApplicationAPI() {
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
        return given().get(getURL()).andReturn();
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
