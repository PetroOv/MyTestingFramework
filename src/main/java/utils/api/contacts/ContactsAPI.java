package utils.api.contacts;

import entities.contacts.Contact;
import io.restassured.response.Response;
import utils.api.BaseAPI;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ContactsAPI extends BaseAPI {
    String contentType = "application/json";

    public ContactsAPI() {
        super();
        setEndpointName(cs.api.getEndpointPathByName("contacts"));
    }

    public static void main(String[] args) {
        ContactsAPI contactsAPI = new ContactsAPI();
        System.out.println(contactsAPI.getURL());
    }

    @Override
    public Response get() {
        return given().contentType(contentType).when().get(getURL());
    }

    public Response getById(int id) {
        return given().contentType(contentType).when().get(getURL() + "/" + id);
    }

    public Response findContactBy(Map<String, String> requestsParams) {
        return given().contentType(contentType).
                params(requestsParams).
                when().
                get(getURL());
    }

    @Override
    public Response post(String bodyData) {
        return given().contentType(contentType).body(bodyData).
                when().
                post(getURL());
    }

    public Response delete(int id) {
        return given().contentType(contentType).
                when().
                delete(getURL() + "/" + id);
    }

    public Response options(int id) {
        return given().options(getURL() + "/" + id);
    }

    public Response head(int id) {
        return given().contentType(contentType).head(getURL() + "/" + id);
    }

    @Override
    public Response patch(int id, String bodyData) {
        return given().
                contentType(contentType).
                body(bodyData).
                when().
                patch(getURL() + "/" + id);
    }

    @Override
    public Response put(int id, String bodyData) {
        return given().
                contentType(contentType).
                body(bodyData).
                when().
                patch(getURL() + "/" + id);
    }

    public Contact responseAsContact(Response response) {
        return response.as(Contact.class);
    }
}
