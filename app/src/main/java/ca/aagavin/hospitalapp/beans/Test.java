package ca.aagavin.hospitalapp.beans;


public class Test {

    private int testId;
    private int patientId;
    private int nurseId;
    private int bpl;
    private int bph;
    private int temperature;



    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getBpl() {
        return bpl;
    }

    public void setBpl(int bpl) {
        this.bpl = bpl;
    }

    public int getBph() {
        return bph;
    }

    public void setBph(int bph) {
        this.bph = bph;
    }

    public int getTemp() {
        return temperature;
    }

    public void setTemp(int temp) {
        this.temperature = temp;
    }

    public static String[] getColumns(){
        return new String[] {"testId", "patientId", "nurseId", "bpl", "bph", "temperature"};
     }
}
