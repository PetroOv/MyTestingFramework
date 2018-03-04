package api;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class SaasHealthcheckEndpointTest {
    private final static String HOST = "192.168.1.129";
    private final static String PORT = "8182";
    private final static String URL = "http://" + HOST + ":" + PORT;
    private final static String ENDPOINT = "/healthcheck";

    @Test
    public void healthCheckTest(){
        given().when().get(URL + ENDPOINT).then().assertThat().statusCode(200).body(is("live"));
    }
}
