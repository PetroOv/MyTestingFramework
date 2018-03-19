package api.contacts.steps;

import entities.contacts.Contact;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.api.contacts.ContactsAPI;

import java.util.Map;

public class ContactSteps {
    Response response;
    ContactsAPI api = new ContactsAPI();

    @Step
    public Response getContactsResponse(){
        response = api.get();
        printResponseData();
        return response;
    }
    @Step
    public Contact getContacts(){
        response = api.get();
        printResponseData();
        return getResponseAsContact(response);
    }
    @Step
    public Response getContactsResponse(Map<String, String> requestParameters){
        response = api.findContactBy(requestParameters);
        printResponseData();
        return response;
    }
    @Step
    public Contact getContacts(Map<String, String> requestParameters){
        response = api.findContactBy(requestParameters);
        printResponseData();
        return getResponseAsContact(response);
    }
    @Step
    public Response getContactByIdResponse(int id){
        response = api.getById(id);
        printResponseData();
        return response;
    }
    @Step
    public Contact getContactById(int id){
        response = api.getById(id);
        printResponseData();
        return api.responseAsContact(response);
    }
    @Step
    public Contact postContact(Contact contact) {
        response = api.post(contact.getInfo().getRequestData());
        printResponseData();
        return api.responseAsContact(response);
    }
    @Step
    public Contact postContact(String contactInfo) {
        response = api.post(contactInfo);
        printResponseData();
        return api.responseAsContact(response);
    }
    @Step
    public Response getContactOptions(int id){
        response = api.options(id);
        printResponseData();
        return response;
    }
    @Step
    public Response postContactResponse(Contact contact) {
        response = api.post(contact.getInfo().getRequestData());
        getResponseHead();
        getResponse();
        return response;
    }
    @Step
    public Response postContactResponse(String contactInfo) {
        response = api.post(contactInfo);
        getResponseHead();
        getResponse();
        return response;
    }

    @Step
    public Response putContactResponse(int id, String contactData){
        response = api.put(id, contactData);
        printResponseData();
        return response;
    }
    @Step
    public Contact putContact(int id, String contactData){
        response = api.put(id, contactData);
        printResponseData();
        return api.responseAsContact(response);
    }
    @Step
    public Response patchContactResponse(int id, String contactData){
        response = api.patch(id, contactData);
        printResponseData();
        return response;
    }
    @Step
    public Contact patchContact(int id, String contactData){
        response = api.patch(id, contactData);
        printResponseData();
        return api.responseAsContact(response);
    }

    @Step
    public Response deleteContact(Contact contact) {
        response = api.delete(contact.getId());
        printResponseData();
        return response;
    }

    @Step
    public Response deleteContact(int id) {
        response = api.delete(id);
        printResponseData();
        return response;
    }
    @Step
    public Contact getResponseAsContact(Response response){
        return api.responseAsContact(response);
    }

    private void printResponseData() {
        getResponseHead();
        getResponse();
    }

    @Attachment(value = "Response", type = "text/json")
    private String getResponse() {
        return response.prettyPrint();
    }

    @Attachment(value = "Response Header", type = "text/plain")
    private String getResponseHead() {
        System.out.println(response.statusLine());
        return response.statusLine();
    }
}
