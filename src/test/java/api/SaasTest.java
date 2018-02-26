package api;

import entities.Contact;
import entities.Info;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SaasTest {
    private final static String HOST = "192.168.1.129";
    private final static String PORT = "8182";
    private final static String URL = "http://" + HOST + ":" + PORT;
    private Info info = new Info("first_name.last_name@gmail.com","first_name","last_name");
    private Info newInfo = new Info("new_first_name.last_name@gmail.com","new_first_name","new_last_name");
    private Info newEmail = new Info();
    {
        newEmail.setEmail(("only_new_email@gmail.com"));
    }


    @Test
    @Description("Post new contact add checks if response body return the same object and all contacts list contains this element. After this delete contact")
    public void postContactTest() {
        Contact contact  = postContact();
        if(contact != null) {
            int id = contact.getData()[0].getId();
            assertThat("Contact info isn`t equal", contact.getData()[0].getInfo(), is(info));
            System.out.println("Contact " + contact + "\n is added");
            assertThat(getById(id), equalTo(contact));
            System.out.println("Database contains contact with ID: " + id);
            assertThat(deleteById(id),equalTo(contact));
            System.out.println("Contact is deleted");
        }
    }

    @Test
    public void patchContactTest(){
        Contact contact  = postContact();
        if(contact != null) {
            int id = contact.getData()[0].getId();
            contact = patchContact(id);
            assertThat(contact.getData()[0].getInfo().getEmail(), is(newEmail.getEmail()));
            assertThat(getById(id).getData()[0].getInfo().getEmail(), is(newEmail.getEmail()));
            System.out.println("Contact email successfully changed to " + newEmail.getEmail());
            deleteById(id);
        }
    }
    @Test
    public void putContactTest(){
        Contact contact  = postContact();
        if(contact != null) {
            int id = contact.getData()[0].getId();
            contact = putContact(id);
            assertThat(contact.getData()[0].getInfo(), is(newInfo));
            assertThat(getById(id).getData()[0].getInfo(), is(newInfo));
            System.out.println("Contact info successfully changed to " + newInfo);
            deleteById(id);
        }
    }

    private Contact putContact(int id) {
        return given().
                contentType("application/json").
                body(newInfo).
                expect().statusCode(200).when().patch(URL + "/api/v1/contacts/" + id).as(Contact.class);
    }

    @Step
    private Contact patchContact(int id){
        return given().
                contentType("application/json").
                body(newEmail).
                expect().statusCode(200).when().patch(URL + "/api/v1/contacts/" + id).as(Contact.class);
    }

    @Step
    private Contact getById(int id){
        return given().
                expect().
                statusCode(200).
                when().
                get(URL + "/api/v1/contacts/" + id).as(Contact.class);
    }

    @Step
    private Contact deleteById(int id){
        return given().delete(URL + "/api/v1/contacts/" + id).as(Contact.class);
    }

    @Step
    private Contact postContact(){
        return given().
                contentType("application/json").
                body(info).
                expect().
                statusCode(201).
                when().
                post(URL + "/api/v1/contacts").as(Contact.class);
    }
    private String setUrl(String url) {
        return url.replace("host:port", HOST + ":" + PORT);
    }
}
