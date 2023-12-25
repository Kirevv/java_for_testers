package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    //все контакты
    @Test
    void testAllPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "name", "name2", "name3", "nick", "company", "address", "89999999999", "mail@mail.com", "", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getAllPhones();
        for (var contact: contacts) {
            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));
        }
    }


    // один контакт
    @Test
    void testThreeEntities() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5),
                    CommonFunctions.randomString(5)));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);

        var phones = app.contacts().getPhones(contact);
        var expected1 = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected1, phones);

        var emails = app.contacts().getEmails(contact);
        var expected2 = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected2, emails);

        var addresses = app.contacts().getAddresses(contact);
        var expected3 = Stream.of(contact.address(), contact.address2())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected3, addresses);
    }






//    @Test
//    void testPhones() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData("", CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5)));
//        }
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        var phones = app.contacts().getPhones(contact);
//        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
//                .filter(s -> s != null && ! "".equals(s))
//                .collect(Collectors.joining("\n"));
//        Assertions.assertEquals(expected, phones);
//    }
//
//    @Test
//    void testEmails() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData("", CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5)));
//        }
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        var emails = app.contacts().getEmails(contact);
//        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
//                .filter(s -> s != null && ! "".equals(s))
//                .collect(Collectors.joining("\n"));
//        Assertions.assertEquals(expected, emails);
//    }
//
//    @Test
//    void testAddresses() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData("", CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5), CommonFunctions.randomString(5)));
//        }
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        var addresses = app.contacts().getAddresses(contact);
//        var expected = Stream.of(contact.address(), contact.address2())
//                .filter(s -> s != null && ! "".equals(s))
//                .collect(Collectors.joining("\n"));
//        Assertions.assertEquals(expected, addresses);
//    }

}
