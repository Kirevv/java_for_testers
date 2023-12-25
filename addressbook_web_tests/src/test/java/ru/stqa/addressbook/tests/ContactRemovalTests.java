package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com", "", "", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveContactFromGroup(){
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "company", "address", "mobile", "email", "", "", "", "", "", ""));
        };
        var rnd = new Random();
        var indexForGroup = rnd.nextInt(app.hbm().getGroupList().size());
        if (app.hbm().getContactsInGroup(app.hbm().getGroupList().get(indexForGroup)).isEmpty()){
            app.contacts().addContactInGroup(app.hbm().getContactList().get(0), app.hbm().getGroupList().get(indexForGroup));
        }
        var indexForContact = rnd.nextInt(app.hbm().getContactsInGroup(app.hbm().getGroupList().get(indexForGroup)).size());
        var group = app.hbm().getGroupList().get(indexForGroup);
        var contact = app.hbm().getContactsInGroup(group).get(indexForContact);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(indexForContact);
        Assertions.assertEquals(expectedList, newRelated);
        }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com", "", "", "", "", "", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

}
