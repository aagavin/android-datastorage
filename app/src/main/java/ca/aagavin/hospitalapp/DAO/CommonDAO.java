package ca.aagavin.hospitalapp.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.aagavin.hospitalapp.beans.Doctor;
import ca.aagavin.hospitalapp.beans.Nurse;
import ca.aagavin.hospitalapp.beans.Patient;
import ca.aagavin.hospitalapp.dbhelpers.DatabaseHelper;

public class CommonDAO {

    private SQLiteDatabase _db;
    private DatabaseHelper _dbHelper;

    public CommonDAO(Context context) {
        this._dbHelper = new DatabaseHelper(context);
        this.open();
    }

    public void open() { this._db = this._dbHelper.getWritableDatabase();}
    public void close(){
        this._db.close();
    }


    /************************ PRIVATE METHODS ******************************/

    private void _insert(String tableName, ContentValues contentValues){
        this._db.insert(tableName, null, contentValues);
    }

    private void _update(String tableName, ContentValues contentValues, String filter){
        this._db.update(tableName,contentValues, filter, null);
    }


    /************************ CREATE ******************************/

    public void createEntity(Doctor entity){
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", entity.getFirstname());
        contentValues.put("lastname", entity.getLastname());
        contentValues.put("department", entity.getDepartment());
        contentValues.put("password", entity.getPassword());
        this._insert("Doctor", contentValues);
    }

    public void createEntity(Nurse entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", entity.getFirstname());
        contentValues.put("lastname", entity.getLastname());
        contentValues.put("department", entity.getDepartment());
        contentValues.put("password", entity.getPassword());
        this._insert("Nurse", contentValues);
    }

    public void createEntity(Patient entity){
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", entity.getFirstname());
        contentValues.put("lastname", entity.getLastname());
        contentValues.put("department", entity.getDepartment());
        contentValues.put("doctorId", entity.getDoctorId());
        contentValues.put("room", entity.getRoom());
        this._insert("Patient", contentValues);
    }


    /************************ READ ******************************/


    public Doctor getEntity(long id){
        Doctor doctor = new Doctor();
        Cursor cursor = this._db.query(true,"Doctor", Doctor.getColumns(),"doctorId = " + id,null, null, null, null, null);

        cursor.moveToFirst();
        
        doctor.setDoctorId(cursor.getInt(0));
        doctor.setFirstname(cursor.getString(1));
        doctor.setLastname(cursor.getString(2));
        doctor.setDepartment(cursor.getString(3));
        doctor.setPassword(cursor.getString(4));

        cursor.close();
        return doctor;
    }

    public List<Patient> getAllPatients(){
        Cursor cursor = this._db.rawQuery("SELECT * FROM Patient", null);

        List<Patient> patientList = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                Patient p = new Patient();
                p.setId(cursor.getInt(0));
                p.setFirstname(cursor.getString(1));
                p.setLastname(cursor.getString(2));
                p.setDepartment(cursor.getString(3));
                p.setDoctorId(cursor.getInt(4));
                p.setRoom(cursor.getInt(5));

                patientList.add(p);

                cursor.moveToNext();

            }
        }


        return patientList;
    }

    /************************ UPDATE ******************************/

    public void updatePatient(Patient patient){
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", patient.getFirstname());
        contentValues.put("lastname", patient.getLastname());
        contentValues.put("department", patient.getDepartment());
        contentValues.put("doctorId", patient.getDoctorId());
        contentValues.put("room", patient.getRoom());

        this._update("Patient",contentValues,"patientId="+patient.getId());
    }


    /************************ LOGIN ******************************/

    public Doctor loginDoctor (String firstname, String passsword){
        Doctor doctor = new Doctor();
        Cursor cursor = this._db.query("Doctor", Doctor.getColumns(), "firstname = ? AND password = ?", new String[] {firstname, passsword}, null, null, null, null);

        cursor.moveToFirst();

        cursor.getCount();

        doctor.setDoctorId(cursor.getInt(0));
        doctor.setFirstname(cursor.getString(1));
        doctor.setLastname(cursor.getString(2));
        doctor.setDepartment(cursor.getString(3));
        doctor.setPassword(cursor.getString(4));

        cursor.close();
        return doctor;
    }

    public Nurse loginNurse(String firstname, String passsword){
        Nurse nurse = new Nurse();
        Cursor cursor = this._db.query("Nurse", Nurse.getColumns(), "firstname = ? AND password = ?", new String[] {firstname, passsword}, null, null, null, null);

        cursor.moveToFirst();

        nurse.setNurseId(cursor.getInt(0));
        nurse.setFirstname(cursor.getString(1));
        nurse.setLastname(cursor.getString(2));
        nurse.setDepartment(cursor.getString(3));
        nurse.setPassword(cursor.getString(4));

        cursor.close();
        return nurse;
    }


}
