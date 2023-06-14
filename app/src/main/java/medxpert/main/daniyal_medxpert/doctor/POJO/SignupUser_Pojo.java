package medxpert.main.daniyal_medxpert.doctor.POJO;

public class SignupUser_Pojo {

    private String firstName;
    private String lastName;
    private String cnic;
    private String specialization;
    private String dateOfBirth;
    private String gender;
    private String countryCode;
    private String phoneNumber;
    private String password;


    public SignupUser_Pojo(String firstName, String lastName, String cnic, String specialization,String dateOfBirth, String gender,
                           String countryCode, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnic = cnic;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.specialization=specialization;
        this.password = password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
}
