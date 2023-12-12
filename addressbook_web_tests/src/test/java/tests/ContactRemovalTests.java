package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com"));
        }
        app.contacts().removeContact();
    }

}
