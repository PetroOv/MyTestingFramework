package api;

import io.restassured.response.Response;
import org.junit.Test;
import utils.api.healtcheck.HealtcheckAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HealthcheckEndpointTest {
    HealtcheckAPI api = new HealtcheckAPI();

    @Test
    public void healthCheckTest(){
        Response response = api.get();
        assertThat("", response.getStatusCode(), is(200));
        assertThat("", response.body().asString(), is("live"));
    }
}
