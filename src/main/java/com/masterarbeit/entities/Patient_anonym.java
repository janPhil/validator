package com.masterarbeit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Jan on 14.04.2017.
 */
@Entity
public class Patient_anonym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long insuranceNumber;
    private String phoneNumber;
    private Date birthday;
    private String firstName;
    private String lastName;
    private String email;
    private String insurance;
    private boolean privateInsurance;
    private double lastAmountInvoiced;

    public Patient_anonym(){}

    public Patient_anonym(int id, String firstName, String lastName, Date birthday, String email, String insurance, int insPolicyNumber, String phoneNumber, boolean privateInsurance, double lastAmountInvoiced){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.insurance = insurance;
        this.insuranceNumber = insPolicyNumber;
        this.phoneNumber = phoneNumber;
        this.privateInsurance = privateInsurance;
        this.lastAmountInvoiced = lastAmountInvoiced;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(int insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public boolean isPrivateInsurance() {
        return privateInsurance;
    }

    public void setPrivateInsurance(boolean privateInsurance) {
        this.privateInsurance = privateInsurance;
    }

    public double getLastAmountInvoiced() {
        return lastAmountInvoiced;
    }

    public void setLastAmountInvoiced(double lastAmountInvoiced) {
        this.lastAmountInvoiced = lastAmountInvoiced;
    }

}
