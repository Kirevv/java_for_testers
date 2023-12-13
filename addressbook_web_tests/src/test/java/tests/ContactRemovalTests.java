package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new ContactData("name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com"));
        }
        int contactCount = app.contacts().getCountContact();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCountContact();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }

}
