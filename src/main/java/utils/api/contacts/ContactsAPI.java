package utils.api.contacts;

import entities.contacts.Contact;
import io.restassured.response.Response;
import utils.api.BaseAPI;

public class ContactsAPI extends BaseAPI {

    public ContactsAPI() {
        super();
        setEndpointName(cs.api.getEndpointPathByName("contacts"));
    }

    @Override
    public Response post() {
        return null;
    }

    @Override
    public Response get() {
        return null;
    }

    @Override
    public Response patch() {
        return null;
    }

    @Override
    public Response put() {
        return null;
    }

    public Contact responseAsContact(Response response){
        return response.as(Contact.class);
    }
    public static void main(String[] args) {
        ContactsAPI contactsAPI = new ContactsAPI();
        System.out.println(contactsAPI.getURL());
    }
}
