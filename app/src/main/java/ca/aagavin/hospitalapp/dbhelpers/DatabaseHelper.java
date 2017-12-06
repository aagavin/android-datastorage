package ca.aagavin.hospitalapp.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "HospitalManager.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String PATIENT_TABLE_CREATE =
            "CREATE TABLE Patient (" +
                "   patientId   INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   firstname   VARCHAR(255)    NOT NULL UNIQUE," +
                "   lastname    VARCHAR(255)    NOT NULL UNIQUE," +
                "   department  VARCHAR(150)    NOT NULL," +
                "   doctorId    INT             NOT NULL," +
                "   room        INT             NOT NULL" +
                "   );";
        String TEST_TABLE_CREATE =
            "CREATE TABLE Test (" +
                "   testId      INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   patientId   INT            NOT NULL," +
                "   nurseId     INT            NOT NULL," +
                "   bpl         INT            NOT NULL," +
                "   bph         INT            NOT NULL," +
                "   pulse       INT            NOT NULL," +
                "   bpm         INT            NOT NULL" +
                "   );";
        String NURSE_TABLE_CREATE =
            "CREATE TABLE Nurse (" +
                "   nurseId     INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   firstname   VARCHAR(255)  NOT NULL UNIQUE," +
                "   lastname    VARCHAR(255)  NOT NULL," +
                "   department  VARCHAR(255)  NOT NULL," +
                "   password    VARCHAR(255)  NOT NULL" +
                "   );";
        String DOCTOR_TABLE_CREATE =
            "CREATE TABLE Doctor (" +
                "   doctorId    INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   firstname   VARCHAR(255)     NOT NULL  UNIQUE," +
                "   lastname    VARCHAR(255)    NOT NULL," +
                "   department  VARCHAR(255)      NOT NULL," +
                "   password    VARCHAR(255)     NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(PATIENT_TABLE_CREATE);
        sqLiteDatabase.execSQL(TEST_TABLE_CREATE);
        sqLiteDatabase.execSQL(NURSE_TABLE_CREATE);
        sqLiteDatabase.execSQL(DOCTOR_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PATIENT");
        this.onCreate(sqLiteDatabase);

    }
}
