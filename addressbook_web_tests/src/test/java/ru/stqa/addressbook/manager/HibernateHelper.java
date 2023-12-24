package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
            .addAnnotatedClass(ContactRecord.class)
            .addAnnotatedClass(GroupRecord.class)
            .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
            .setProperty(AvailableSettings.USER, "root")
            .setProperty(AvailableSettings.PASS, "")
            .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertGroup(record));
        }
        return result;
    }

    //конвертация для извлечения - creation test
    private static GroupData convertGroup(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    //конвертация для добавления - предусловие removal test
    private static GroupRecord convertGroup(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertContact(record));
        }
        return result;
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    private static ContactData convertContact(ContactRecord record) {
        return new ContactData().withId("" + record.id)
                .withFirstname(record.firstname)
                .withMiddlename(record.middlename)
                .withLastname(record.lastname)
                .withNickname(record.nickname)
                .withCompany(record.company)
                .withAddress(record.address)
                .withMobile(record.mobile)
                .withEmail(record.email);
    }

    private static ContactRecord convertContact(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstname(), data.middlename(), data.lastname(), data.nickname(), data.company(), data.address(), data.mobile(), data.email());
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroup(groupData));
            session.getTransaction().commit();
        });
    }

    public List<GroupData> getGroupsByContact(ContactData contactData) {
        return sessionFactory.fromSession(session -> {
            return convertGroupList(session.get(ContactRecord.class, contactData.id()).groups);
        });
    }

    public List<ContactData> getContactsNotInGroup() {
        var contacts = getContactList();
        contacts.removeIf(contactData -> {
            var group = getGroupsByContact(contactData);
            return (group != null) && (!group.isEmpty());
        });
        return contacts;
    }

}
