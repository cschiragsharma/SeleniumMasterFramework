package org.selenium.pom.objects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String email;

    public BillingAddress() {

    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress1() {
        return address1;

    }

    public BillingAddress setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public BillingAddress setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    public BillingAddress(String firstName, String lastName, String address1, String address2,
                          String city,String postalCode,String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.postalCode=postalCode;
        this.email=email;

    }



}
