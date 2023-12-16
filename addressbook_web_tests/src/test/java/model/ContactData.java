package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String nickname, String company,
                          String address, String mobile, String email
) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.address, this.mobile, this.email);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.nickname, this.company, this.address, this.mobile, this.email);
    }

    public ContactData withMiddlename(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.nickname, this.company, this.address, this.mobile, this.email);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.nickname, this.company, this.address, this.mobile, this.email);
    }

    public ContactData withNickname(String nickname) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, nickname, this.company, this.address, this.mobile, this.email);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, company, this.address, this.mobile, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, address, this.mobile, this.email);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.address, mobile, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.address, this.mobile, email);
    }
}