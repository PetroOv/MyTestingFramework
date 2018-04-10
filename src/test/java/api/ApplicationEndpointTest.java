package api;

import io.qameta.allure.Description;
import org.junit.Test;
import utils.api.application.ApplicationAPI;

import static org.hamcrest.CoreMatchers.equalTo;

public class ApplicationEndpointTest {
    ApplicationAPI api = new ApplicationAPI();

    @Test
    @Description
    public void getApplicationTest() {
        api
                .get()
                .then()
                .body("application.resources.resource.method.size()", equalTo(4));
    }
}
