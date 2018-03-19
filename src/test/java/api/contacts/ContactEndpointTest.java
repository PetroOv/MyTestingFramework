package api.contacts;

import api.contacts.steps.ContactSteps;
import entities.contacts.Contact;
import entities.contacts.Data;
import entities.contacts.Info;
import entities.contacts.Refs;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

public class ContactEndpointTest {

    Response response;
    ContactSteps steps;
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

    @Before
    public void setUp() throws Exception {
        steps = new ContactSteps();
    }

    @Test
    @Description("POST new contact and check if response code is 200. After this DELETE contact")
    @DisplayName("POST method response code test")
    public void postContactRCTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        steps.deleteContact(contact.getId());
        assertThat("Invalid status code", response.getStatusCode(), is(201));

    }

    @Test
    @Description("POST new contact and check response body contact representation.")
    @DisplayName("POST method response body test")
    public void postContactRBTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        steps.deleteContact(contact.getId());
        assertThat("Contact info isn`t equal", contact.getInfo(), is(contactInfo));
    }

    @Test
    @Description("POST new contact and try get it by id")
    @DisplayName("GET object representation after POST")
    public void postContactGetTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        Contact takenContact = steps.getContactById(contact.getId());
        steps.deleteContact(contact.getId());
        assertThat("", takenContact, is(contact));
    }

    @Test
    @Description("Check GET method response code when trying find existing element")
    @DisplayName("GET method 200 status code test")
    public void getContactRCTest() {
        response = steps.getContactByIdResponse(1);
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("Check if GET method return right 'Contact' representation model")
    public void getContactRepresentationTest() {
        response = steps.getContactByIdResponse(1);
        Contact contact = steps.getResponseAsContact(response);
        assertThat("", contact, equalTo(firstContact));
    }

    @Test
    @Description("Check GET method response body 'Info' representation. Expected result -  initial 'Contact' in base.")
    @DisplayName("GET method response body test")
    public void getContactRBTest() {
        response = steps.getContactByIdResponse(1);
        Contact contact = steps.getResponseAsContact(response);
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
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        response = steps.putContactResponse(contact.getId(), newContactInfo.getRequestData());
        steps.deleteContact(contact.getId());
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("After creating new contact PUT new email, first name and last name. Check response body")
    @DisplayName("PUT method response body test")
    public void putContactRBTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        response = steps.putContactResponse(contact.getId(), newContactInfo.getRequestData());
        contact = steps.getResponseAsContact(response);
        steps.deleteContact(contact.getId());
        assertThat("", contact.getInfo(), equalTo(newContactInfo));
    }

    @Test
    @Description("After creating new contact PATCH new email. Check response body to PATCH method")
    @DisplayName("PATCH response body test")
    public void patchContactRCTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        response = steps.patchContactResponse(contact.getId(), newContactEmail.getRequestData());
        steps.deleteContact(contact.getId());
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("After creating new contact PATCH new email. Check that response code is 200")
    @DisplayName("PATCH method status code test")
    public void patchContactRBTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact contact = steps.getResponseAsContact(response);
        response = steps.patchContactResponse(contact.getId(), newContactEmail.getRequestData());
        contact = steps.getResponseAsContact(response);
        steps.deleteContact(contact.getId());
        Info expectedContactInfo = contactInfo;
        expectedContactInfo.setEmail(newContactEmail.getEmail());
        assertThat("", contact.getInfo(), equalTo(expectedContactInfo));
    }

    @Test
    @Description("DELETE 'Contact' by id. Check response body")
    @DisplayName("DELETE method response body test")
    public void deleteContactRBTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact expectedContact = steps.getResponseAsContact(response);
        response = steps.deleteContact(expectedContact.getId());
        Contact contact = steps.getResponseAsContact(response);
        assertThat("", contact, equalTo(expectedContact));
    }

    @Test
    @Description("DELETE 'Contact' by id. Check that response code is 200")
    @DisplayName("DELETE method status code test")
    public void deleteContactRCTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact expectedContact = steps.getResponseAsContact(response);
        response = steps.deleteContact(expectedContact.getId());
        Contact contact = steps.getResponseAsContact(response);
        assertThat("", response.getStatusCode(), is(200));
    }

    @Test
    @Description("Get actual representation to check that it was removed")
    @DisplayName("DELETE changes test")
    public void deleteContactNotPresentTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact expectedContact = steps.getResponseAsContact(response);
        response = steps.deleteContact(expectedContact.getId());
        response = steps.getContactsResponse();
        Contact contact = steps.getResponseAsContact(response);
        assertThat("", contact.getData(), not(arrayContaining(expectedContact.getData()[0])));
    }

    @Test
    @Description("GET method to contacts endpoint with 'firstName' parameter")
    @DisplayName("Get contacts with first name param regex")
    public void getContactsByFNParams() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact expectedContact = steps.getResponseAsContact(response);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put("firstName", contactInfo.getFirstName());
        response = steps.getContactsResponse(requestParameters);
        steps.deleteContact(expectedContact.getId());
        Contact contact = steps.getResponseAsContact(response);
        assertThat("", contact.getData(), hasItemInArray(expectedContact.getData()[0]));
    }

    @Test
    @Description("Try to use OPTIONS method. Check that status code is 405")
    @DisplayName("OPTIONS method test")
    public void optionsContactsTest() {
        response = steps.postContactResponse(contactInfo.getRequestData());
        Contact expectedContact = steps.getResponseAsContact(response);
        response = steps.getContactOptions(expectedContact.getId());
        steps.deleteContact(expectedContact.getId());
        assertThat("", response.getStatusCode(), is(405));
    }
}
