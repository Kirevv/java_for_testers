package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    //все контакты
    @Test
    void testAllPhones() {
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
    void testPhones() {
         var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

}
