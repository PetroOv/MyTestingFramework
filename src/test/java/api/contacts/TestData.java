package api.contacts;

import entities.contacts.Contact;
import entities.contacts.Data;
import entities.contacts.Info;
import entities.contacts.Refs;

public class TestData {
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

    public Info getContactInfo() {
        return contactInfo;
    }

    public Info getNewContactInfo() {
        return newContactInfo;
    }

    public Info getNewContactEmail() {
        return newContactEmail;
    }

    public Contact getFirstContact() {
        return firstContact;
    }

    public Contact getFirstIncorrectContact() {
        return firstIncorrectContact;
    }

    public Info getFirstContactInfo() {
        return firstContactInfo;
    }
}
