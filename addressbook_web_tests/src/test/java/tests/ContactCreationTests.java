package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("name", "name2", "name3", "nick", "company", "address", "89999999977", "mail@mail.com"));
    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithNameOnly() {
        app.contacts().createContact(new ContactData().withFirstname("some firstname"));
    }
}
