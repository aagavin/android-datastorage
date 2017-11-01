package ca.aagavin.hospitalapp.dbhelpers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*
 * Created by aaron on 10/31/17.
 */

public class NurseDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactsManager";

//    private static final String TABLE_PATIENT = "patient";
//    private static final String TABLE_TEST = "test";
    private static final String TABLE_NURSE = "nurse";
//    private static final String TABLE_DOCTOR = "Doctor";

    public NurseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String PATIENT_TABLE_CREATE =
                "CREATE TABLE "+TABLE_NURSE+"(" +
                "   nurseId      INT     PRIMARY KEY," +
                "   firstname   VARCHAR(255)     NOT NULL," +
                "   lastname     VARCHAR(255)     NOT NULL," +
                "   department         VARCHAR(255)      NOT NULL," +
                "   password         VARCHAR(255)     NOT NULL," +
                "   );";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PATIENT");
        this.onCreate(sqLiteDatabase);

    }
}
