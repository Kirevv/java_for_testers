package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String middlename;
    public String lastname;
    public String nickname;
    public String title = "title";
    public String company;
    public String address;
    public String home = "12345";
    public String mobile;
    public String work = "12345";
    public String fax = "12345";
    public String email;
    public String email2 = "email2";
    public String email3 = "email3";
    public String homepage = "page";

    public ContactRecord() {}

    public ContactRecord(int id, String firstname, String middlename, String lastname, String nickname, String company, String address, String mobile, String email) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

}