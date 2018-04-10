package utils.api;

import io.restassured.response.Response;
import utils.common.ConfigurationSetup;

public abstract class BaseAPI {

    protected final ConfigurationSetup cs;
    private String endpointName = "";
    private String URL;


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

    public void setURL(String URL) {
        this.URL = URL;
    }


    public abstract <T> Response post(T bodyData);

    public abstract Response get();

    public abstract<T> Response patch(int id, T bodyData);

    public abstract<T> Response put(int id, T bodyData);

}
