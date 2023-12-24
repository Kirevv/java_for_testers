package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        returnToHomePage();
        selectContact(contact);
        selectGroupFromHomePage2(group);
        submitAddContactInGroup();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        returnToHomePage();
        selectGroupFromHomePage1(group);
        selectContact(contact);
        removeSelectedContactInGroup();
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    public void removeAllContacts() {
        returnToHomePage();
        selectAllContacts();
        removeSelectedContact();
        returnToHomePage();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
    }


    //private void submitContactCreation() {click(By.name("submit"));}
    private void submitContactCreation() {
        manager.driver.findElement(By.cssSelector("input[type=submit]")).click();
    }


    private void initContactCreation() {
        click(By.linkText("add new"));
    }


    //private void approveDeletion() {manager.driver.switchTo().alert().accept();}


    private void removeSelectedContact() {
        click(By.cssSelector("input[value=Delete]"));
    }


    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }


    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href=\"edit.php?id=%s\"]", contact.id())));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public int getCountContact() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var contactFirstname = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var contactLastname = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(contactFirstname)
                    .withLastname(contactLastname));
        }
        return contacts;
    }

    private void submitAddContactInGroup() {
        click(By.name("add"));
    }

    private void selectGroupFromHomePage1(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectGroupFromHomePage2(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }
    private void removeSelectedContactInGroup(){
        click(By.name("remove"));
    }

}
