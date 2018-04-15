package api.contacts;

import com.github.javafaker.Faker;
import entities.contacts.Contact;
import entities.contacts.Data;
import entities.contacts.Info;
import entities.contacts.Refs;

public class TestData {
    private Info testInfo;
    private Contact testContact;
    Faker faker;



    public TestData() {
        testInfo = new Info();
        testContact = new Contact();
        faker = new Faker();
    }

    public TestData fillData(){
        testInfo.setFirstName(faker.name().firstName());
        testInfo.setLastName(faker.name().lastName());
        testInfo.setEmail(faker.internet().emailAddress());
        return this;
    }

    public TestData setEmail(String email){
        testInfo.setEmail(email);
        return this;
    }
    public TestData setFirstName(String firstName){
        testInfo.setFirstName(firstName);
        return this;
    }
    public TestData setLastName(String lastName){
        testInfo.setLastName(lastName);
        return this;
    }

    public Info getTestInfo(){
        return testInfo;
    }

    public Contact getFirstContact() {
        return null;
    }

    public Info getFirstContactInfo() {
        return null;
    }

    public Info getNewContactInfo() {
        return null;
    }

    public String getNewContactEmail() {
        return null;
    }
}
