package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new ContactData("name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com"));
        }
        app.contacts().modifyContact(new ContactData().withFirstname("modified firstname"));
    }
}
