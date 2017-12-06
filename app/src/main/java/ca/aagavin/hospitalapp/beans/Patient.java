package ca.aagavin.hospitalapp.beans;

/*
 * Created by aaron
 */

public class Patient {

    private int patientId;
    private String firstname;
    private String lastname;
    private String department;
    private int doctorId;
    private int room;

    public int getId() {
        return patientId;
    }

    public void setId(int id) {
        this.patientId = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public static String[] getColumns(){
        return new String[] {"patientId", "firstname", "lastname", "department", "doctorId", "room"};
    }
}
