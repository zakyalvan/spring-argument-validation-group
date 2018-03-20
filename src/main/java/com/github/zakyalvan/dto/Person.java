package com.github.zakyalvan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zakyalvan.validate.FullInfoDetails;
import com.github.zakyalvan.validate.PersonBasicInfo;
import com.github.zakyalvan.validate.WithBirthInfo;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    @NotBlank(groups = {PersonBasicInfo.class, WithBirthInfo.class, FullInfoDetails.class})
    private String fullName;

    @NotNull(groups = {PersonBasicInfo.class, WithBirthInfo.class, FullInfoDetails.class})
    private Gender gender;

    @NotNull(groups = {WithBirthInfo.class, FullInfoDetails.class})
    private String birthPlace;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(groups = {WithBirthInfo.class, FullInfoDetails.class})
    private Date birthDate;

    @Email(groups = {FullInfoDetails.class})
    private String emailAddress;

//    public Person() {}
//    @JsonCreator
//    public Person(@JsonProperty("fullName") String fullName, @JsonProperty("gender") Gender gender, @JsonProperty("birthPlace") String birthPlace, @JsonProperty("birthDate") Date birthDate, @JsonProperty("emailAddress") String emailAddress) {
//        this.fullName = fullName;
//        this.gender = gender;
//        this.birthPlace = birthPlace;
//        this.birthDate = birthDate;
//        this.emailAddress = emailAddress;
//    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthDate=" + birthDate +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
