package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")) {
//            for (var middlename : List.of("", "middlename")) {
//                for (var lastname : List.of("", "lastname")) {
//                                    result.add(new ContactData().withFirstname(firstname).withMiddlename(middlename).withLastname(lastname).withCompany(CommonFunctions.randomString(5)).withAddress(CommonFunctions.randomString(5)).withMobile(CommonFunctions.randomString(5)).withEmail(CommonFunctions.randomString(5)));
//                                }
//                            }
//                        }
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//        var breader = new BufferedReader(reader)
//            ) {
//            var line = breader.readLine();
//            while (line != null) {
//                json = json + line;
//                line = breader.readLine();
//            }
//        }
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }
    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30)));
    }


    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }


    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstname'", "", "", "", "", "", "", "")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContactInGroup(ContactData contact){
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(new ContactData()
                .withId(newRelated.get(newRelated.size()-1).id())
                .withFirstname(newRelated.get(newRelated.size()-1).firstname())
                .withLastname(newRelated.get(newRelated.size()-1).lastname())
                .withAddress(newRelated.get(newRelated.size()-1).address())
                .withMobile(newRelated.get(newRelated.size()-1).mobile()));
        expectedList.sort(compareById);
        oldRelated.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);
    }

    @Test
    public void canAddContactToGroup(){
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
            if(app.hbm().getContactCount() == 0){
                app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "company", "address", "mobile", "email"));
            };
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        var rnd = new Random();
        var group = app.hbm().getGroupList().get(rnd.nextInt(app.hbm().getGroupList().size()));
        var contacts = app.hbm().getContactsNotInGroup();
        if (contacts.size() == 0){
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "company", "address", "mobile", "email"));
        }
        var contact = app.hbm().getContactsNotInGroup().get(rnd.nextInt(app.hbm().getContactsNotInGroup().size()));
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactInGroup(contact,group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact);
        Assertions.assertEquals(newRelated, expectedList);

    }


}