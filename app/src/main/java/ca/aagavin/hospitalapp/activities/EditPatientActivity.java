package ca.aagavin.hospitalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.R;
import ca.aagavin.hospitalapp.beans.Patient;

public class EditPatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CommonDAO _dao;
    private List<Patient> _allPatients;
    private int _selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);


        this._dao = new CommonDAO(this);

        this._allPatients =  this._dao.getAllPatients();
        List<String> dropDown = new ArrayList<>();


        for (Patient p : this._allPatients) {
            System.out.println(p.getFirstname() +" " + p.getLastname());
            dropDown.add(p.getFirstname() +" " + p.getLastname());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, dropDown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        this._dao.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this._selectedIndex = i;

        Patient selectedPatient = this._allPatients.get(i);

        ((EditText) findViewById(R.id.editTextfirstname)).setText(selectedPatient.getFirstname());
        ((EditText) findViewById(R.id.editTextlastname)).setText(selectedPatient.getFirstname());
        ((EditText) findViewById(R.id.editTextdepartment)).setText(selectedPatient.getDepartment());
        ((EditText) findViewById(R.id.editTextroom)).setText(selectedPatient.getRoom());

    }

    @Override public void onNothingSelected(AdapterView<?> adapterView) {}
}
