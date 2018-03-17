package api.contacts;

import entities.contacts.Contact;
import entities.contacts.Data;
import entities.contacts.Info;
import entities.contacts.Refs;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

public class ContactEndpointTest {
    private final static String HOST = "192.168.1.129";
    private final static String PORT = "8182";
    private final static String URL = "http://" + HOST + ":" + PORT;
    private final static String ENDPOINT = "/api/v1/contacts/";
    Response response;
    private Info contactInfo = new Info("first_name.last_name@gmail.com", "first_name", "last_name");
    private Info newContactInfo = new Info("new_first_name.last_name@gmail.com", "new_first_name", "new_last_name");
    private Info newContactEmail = new Info();
    private Contact firstContact = new Contact();
    private Contact firstIncorrectContact = new Contact();
    private Info firstContactInfo = new Info("john.doe@unknown.com", "John", "Doe");

    {
        newContactEmail.setEmail(("only_new_email@gmail.com"));
        firstContact.setData(new Data[]{new Data(1, firstContactInfo, new Refs("http://host:port/api/v1/contacts/1"))});
        firstIncorrectContact.setData(new Data[]{new Data(1, firstContactInfo, new Refs("http://host:port/api/v1/contacts/2"))});
        firstIncorrectContact.setMessage("");
        firstIncorrectContact.setStatus(200);
        firstContact.setStatus(200);
        firstContact.setMessage("");
    }


    @Test
    @Description("POST new contact and check if response code is 200. After this DELETE contact")
    @DisplayName("POST method response code test")
    public void postContactRCTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        assertThat("Invalid status code", response.getStatusCode(), is(201));

    }

    @Test
    @Description("POST new contact and check response body contact representation.")
    @DisplayName("POST method response body test")
    public void postContactRBTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        assertThat("Contact info isn`t equal", contact.getInfo(), is(contactInfo));
    }

    @Test
    @Description("POST new contact and try get it by id")
    @DisplayName("GET object representation after POST")
    public void postContactGetTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        Contact takenContact = getContactById(contact.getId()).as(Contact.class);
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        assertThat("", takenContact, is(contact));
    }

    @Test
    @Description("Check GET method response code when trying find existing element")
    @DisplayName("GET method 200 status code test")
    public void getContactRCTest() {
        response = getContactById(1);
        getResponseHead();
        getResponse();
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("Check if GET method return right 'Contact' representation model")
    public void getContactRepresentationTest() {
        response = getContactById(1);
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        assertThat("", contact, equalTo(firstContact));
    }

    @Test
    @Description("Check GET method response body 'Info' representation. Expected result -  initial 'Contact' in base.")
    @DisplayName("GET method response body test")
    public void getContactRBTest() {
        response = getContactById(1);
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        assertThat("", contact.getInfo(), equalTo(firstContactInfo));
    }

    @Test
    @Description("Checks new object to have initialize all field that uses in PUT method")
    public void putWholeRepresentationTest() throws IllegalAccessException {
        assertThat("", newContactInfo.checkNullFields());
    }

    @Test
    @Description("After creating new contact PUT new email, first name and last name.Check that status code is 200")
    @DisplayName("PUT method status code test")
    public void putContactRCTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        response = putContact(contact.getId(), newContactInfo.getRequestData());
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("After creating new contact PUT new email, first name and last name. Check response body")
    @DisplayName("PUT method response body test")
    public void putContactRBTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        response = putContact(contact.getId(), newContactInfo.getRequestData());
        contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        assertThat("", contact.getInfo(), equalTo(newContactInfo));
    }

    @Test
    @Description("After creating new contact PATCH new email. Check response body to PATCH method")
    @DisplayName("PATCH response body test")
    public void patchContactRCTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        response = patchContact(contact.getId(), newContactEmail.getRequestData());
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("After creating new contact PATCH new email. Check that response code is 200")
    @DisplayName("PATCH method status code test")
    public void patchContactRBTest() {
        response = postContact(contactInfo.getRequestData());
        Contact contact = response.as(Contact.class);
        response = patchContact(contact.getId(), newContactEmail.getRequestData());
        contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        deleteContactById(contact.getId());
        Info expectedContactInfo = contactInfo;
        expectedContactInfo.setEmail(newContactEmail.getEmail());
        assertThat("", contact.getInfo(), equalTo(expectedContactInfo));
    }

    @Test
    @Description("DELETE 'Contact' by id. Check response body")
    @DisplayName("DELETE method response body test")
    public void deleteContactRBTest() {
        response = postContact(contactInfo.getRequestData());
        Contact expectedContact = response.as(Contact.class);
        response = deleteContactById(expectedContact.getId());
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        assertThat("", contact, equalTo(expectedContact));
    }

    @Test
    @Description("DELETE 'Contact' by id. Check that response code is 200")
    @DisplayName("DELETE method status code test")
    public void deleteContactRCTest() {
        response = postContact(contactInfo.getRequestData());
        Contact expectedContact = response.as(Contact.class);
        response = deleteContactById(expectedContact.getId());
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("Get actual representation to check that it was removed")
    @DisplayName("DELETE changes test")
    public void deleteContactNotPresentTest() {
        response = postContact(contactInfo.getRequestData());
        Contact expectedContact = response.as(Contact.class);
        response = deleteContactById(expectedContact.getId());
        response = getContacts();
        Contact contact = response.as(Contact.class);
        getResponseHead();
        getResponse();
        assertThat("", contact.getData(), not(arrayContaining(expectedContact.getData()[0])));
    }

    @Test
    @Description("GET method to contacts endpoint with 'firstName' parameter")
    @DisplayName("Get contacts with first name param regex")
    public void getContactsByFNParams() {
        response = postContact(contactInfo.getRequestData());
        Contact expectedContact = response.as(Contact.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put("firstName", contactInfo.getFirstName());
        response = getContactsBy(requestParameters);
        getResponseHead();
        getResponse();
        deleteContactById(expectedContact.getId());
        Contact contact = response.as(Contact.class);
        assertThat("", contact.getData(), hasItemInArray(expectedContact.getData()[0]));
    }

    @Test
    @Description("Try to use OPTIONS method. Check that status code is 405")
    @DisplayName("OPTIONS method test")
    public void optionsContactsTest() {
        response = postContact(contactInfo.getRequestData());
        Contact expectedContact = response.as(Contact.class);
        response = optionsContact(expectedContact.getId());
        getResponseHead();
        getResponse();
        deleteContactById(expectedContact.getId());
        assertThat("", response.getStatusCode(), is(405));
    }

    @Step
    private Response getContactsBy(Map<String, String> requestsParams) {
        return given().
                params(requestsParams).
                when().
                get(URL + ENDPOINT);
    }

    @Step
    private Response getContacts() {
        return given().
                contentType("application/json").
                when().
                get(URL + ENDPOINT);
    }

    @Step
    private Response putContact(int id, String bodyData) {
        return given().
                contentType("application/json").
                body(bodyData).
                when().
                put(URL + ENDPOINT + id);
    }

    @Step
    private Response patchContact(int id, String bodyData) {
        return given().
                contentType("application/json").
                body(bodyData).
                when().
                patch(URL + ENDPOINT + id);
    }

    @Step
    private Response getContactById(int id) {
        return given().
                when().
                get(URL + ENDPOINT + id);
    }

    @Step
    private Response deleteContactById(int id) {
        return given().
                delete(URL + ENDPOINT + id);
    }

    @Step
    private Response postContact(String bodyData) {
        return given().
                contentType("application/json").
                body(bodyData).
                when().
                post(URL + ENDPOINT);
    }

    @Step
    private Response optionsContact(int id) {
        return given().
                options(URL + ENDPOINT + id);
    }

    @Step
    private Response headContact(int id) {
        return given().
                head(URL + ENDPOINT + id);
    }


    @Attachment(value = "Response", type = "text/json")
    public String getResponse() {
        return response.prettyPrint();
    }

    @Attachment(value="Response Header", type="text/plain")
    public String getResponseHead(){
        System.out.println(response.statusLine());
        return response.statusLine();}

    private String setUrl(String url) {
        return url.
                replace("host:port", HOST + ":" + PORT);
    }

}
