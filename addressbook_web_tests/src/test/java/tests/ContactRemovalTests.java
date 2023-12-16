package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new ContactData("", "name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new ContactData("", "name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCountContact());
    }

}
