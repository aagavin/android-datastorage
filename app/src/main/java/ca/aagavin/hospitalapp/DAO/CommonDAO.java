package ca.aagavin.hospitalapp.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ca.aagavin.hospitalapp.beans.Doctor;
import ca.aagavin.hospitalapp.beans.Nurse;
import ca.aagavin.hospitalapp.dbhelpers.DatabaseHelper;

public class CommonDAO {

    private SQLiteDatabase _db;
    private DatabaseHelper _dbHelper;

    public CommonDAO(Context context) {
        this._dbHelper = new DatabaseHelper(context);
        this._db = this._dbHelper.getWritableDatabase();
    }

    public void close(){
        this._db.close();
    }

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

    private void _insert(String tableName, ContentValues contentValues){
        this._db.insert(tableName, null, contentValues);

    }

}
