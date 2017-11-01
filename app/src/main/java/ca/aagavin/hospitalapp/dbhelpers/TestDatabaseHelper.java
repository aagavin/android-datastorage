package ca.aagavin.hospitalapp.dbhelpers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*
 * Created by aaron on 10/31/17.
 */

public class TestDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactsManager";

//    private static final String TABLE_PATIENT = "patient";
    private static final String TABLE_TEST = "test";
//    private static final String TABLE_NURSE = "Nurse";
//    private static final String TABLE_DOCTOR = "Doctor";

    public TestDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String PATIENT_TABLE_CREATE =
                "CREATE TABLE "+TABLE_TEST+"(" +
                "   testId      INT     PRIMARY KEY," +
                "   patientId   INT     NOT NULL," +
                "   nurseId     INT     NOT NULL," +
                "   bpl         INT     NOT NULL," +
                "   bph         INT     NOT NULL," +
                "   pulse       INT     NOT NULL" +
                "   bpm       INT     NOT NULL" +
                "   );";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PATIENT");
        this.onCreate(sqLiteDatabase);

    }
}
