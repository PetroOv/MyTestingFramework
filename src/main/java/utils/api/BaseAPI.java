package utils.api;

import io.restassured.response.Response;
import utils.common.ConfigurationSetup;

public abstract class BaseAPI {

    protected final ConfigurationSetup cs;
    private String endpointName = "";
    private final String  URL;


    public BaseAPI() {
        cs = new ConfigurationSetup();
        this.URL = cs.environment.getAPIAddress() + cs.api.getAddress();
    }


    protected void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getURL() {
        return URL + endpointName;
    }


    public abstract Response post();

    public abstract Response get();

    public abstract Response patch();

    public abstract Response put();

}
