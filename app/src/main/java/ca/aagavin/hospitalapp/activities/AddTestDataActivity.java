package ca.aagavin.hospitalapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.R;
import ca.aagavin.hospitalapp.beans.Patient;
import ca.aagavin.hospitalapp.beans.Test;

public class AddTestDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CommonDAO _dao;
    private TextView bph;
    private TextView bpl;
    private TextView temp;
    private int PatientID;
    private int _selectedIndex = 0;

    private SharedPreferences _prefs;

    private int _nurseID;
    private boolean _isDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

        this._prefs = getSharedPreferences("loginid", MODE_PRIVATE);
        this._nurseID = this._prefs.getInt("id", 0);
        this._isDoctor = _prefs.getBoolean("isDoctor", false);

        bph = (TextView) findViewById(R.id.editText2);
        bpl = (TextView) findViewById(R.id.editText1);
        temp = (TextView) findViewById(R.id.editText4);

        if (this._isDoctor){
            bph.setEnabled(false);
            bpl.setEnabled(false);
            temp.setEnabled(false);
        }

        this._dao = new CommonDAO(this);

        List<Patient> a =  this._dao.getAllPatients();
        List<String> dropDown = new ArrayList<>();


        for (Patient p :a) {
            System.out.println(p.getFirstname() +" " + p.getLastname());
            dropDown.add(p.getFirstname() +" " + p.getLastname());
        }
        this._doctorCheck();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, dropDown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void saveTestBtn(View view) {


        if (_doctorCheck()) return;

        Test test = new Test();
        test.setPatientId(PatientID+1);
        test.setNurseId(this._nurseID);
        test.setBpl(Integer.parseInt(bpl.getText().toString()));
        test.setBph(Integer.parseInt(bph.getText().toString()));
        test.setTemp(Integer.parseInt(temp.getText().toString()));

        this._dao.createEntity(test);

        Toast.makeText(this,"New test added",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this._selectedIndex = i;

        Patient selectedPatient = this._dao.getAllPatients().get(this._selectedIndex);

        PatientID = selectedPatient.getId();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    private boolean _doctorCheck() {
        if (this._isDoctor){
            Toast.makeText(this,
                    "WARNING! Only nurses can update patient test information",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
