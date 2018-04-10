package api.contacts.steps;

import entities.contacts.Contact;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.api.contacts.ContactsAPI;

import java.util.Map;

public class ContactSteps {
    Response response;
    ContactsAPI api = new ContactsAPI();

    @Step
    public Response getContacts(){
        response = api.get();
        return response;
    }

    @Step
    public Response getContacts(Map<String, String> requestParameters){
        response = api.findContactBy(requestParameters);
        return response;
    }

    @Step
    public Response getContactById(int id){
        response = api.getById(id);
        return response;
    }

    @Step
    public Response getContactOptions(int id){
        response = api.options(id);
        return response;
    }
    @Step
    public <T> Response postContact(T contactInfo) {
        response = api.post(contactInfo);
        return response;
    }

    @Step
    public <V> Response putContact(int id, V contactData){
        response = api.put(id, contactData);
        return response;
    }

    @Step
    public <V> Response patchContact(int id, V contactData){
        response = api.patch(id, contactData);
        return response;
    }

    @Step
    public Response deleteContact(Contact contact) {
        response = api.delete(contact.getId());
        return response;
    }

    @Step
    public Response deleteContact(int id) {
        response = api.delete(id);
        return response;
    }
    @Step
    public Contact getResponseAsContact(Response response){
        return api.responseAsContact(response);
    }


}
