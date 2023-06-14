package medxpert.main.daniyal_medxpert.patient.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable {
    private String firstName;
    private String lastName;
    private String cnic;
    private String dateOfBirth;
    private String gender;
    private String countryCode;
    private String phoneNumber;
    private String password;
    private List labReports;

    public void addReport(String p){
        labReports.add(p);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnic='" + cnic + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", labReports=" + labReports +
                '}';
    }

    public void removeReport(String p){
        labReports.remove(p);
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

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List getLabReports() {
        return labReports;
    }

    public void setLabReports(ArrayList labReports) {
        this.labReports = labReports;
    }
}
