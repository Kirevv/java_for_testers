package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        selectContact();
        removeSelectedContact();
        approveDeletion();
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


    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    private void initContactCreation() {
        click(By.linkText("add new"));
    }


    private void approveDeletion() {
        manager.driver.switchTo().alert().accept();
    }


    private void removeSelectedContact() {
        click(By.cssSelector("input[value=Delete]"));
    }


    private void selectContact() {
        click(By.name("selected[]"));
    }


}
