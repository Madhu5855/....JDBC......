public class Patient {
     String patientId;
     String name;
     int age;
     String gender;
     String phoneNumber;

     Patient(String patientId, String name, int age, String gender, String phoneNumber) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    // Getters & Setters
    String getPatientId() {
        return patientId; }
    void setPatientId(String patientId) {
        this.patientId = patientId; }

    String getName() {
        return name; }
    void setName(String name) {
        this.name = name; }

     int getAge() {
        return age; }
     void setAge(int age) {
        this.age = age; }

     String getGender() {
        return gender; }
     void setGender(String gender) {
        this.gender = gender; }

     String getPhoneNumber() {
        return phoneNumber; }
     void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber; }

   public  String toString() {
        return String.format("%-10s %-15s %-5d %-10s %-10s",
                patientId, name, age, gender, phoneNumber);
    }
}

