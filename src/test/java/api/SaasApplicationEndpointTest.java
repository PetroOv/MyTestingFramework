package api;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class SaasApplicationEndpointTest {
    private final static String HOST = "192.168.1.129";
    private final static String PORT = "8182";
    private final static String URL = "http://" + HOST + ":" + PORT;
    private final static String ENDPOINT = "/application.wadl";
    Response response;
    @Test
    @Description
    public void getApplicationTest(){
        given().get(URL + ENDPOINT).then().body("application.resources.resource.method.size()",equalTo(4));
    }
}
