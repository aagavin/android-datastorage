package ca.aagavin.hospitalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.R;
import ca.aagavin.hospitalapp.beans.Nurse;
import ca.aagavin.hospitalapp.beans.Patient;
import ca.aagavin.hospitalapp.beans.Test;

public class ViewTestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CommonDAO _dao;
    private int _selectedIndex = 0;
    private int _patientID;
    private Patient selectedPatient;
    private ListView testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);

        this._dao = new CommonDAO(this);

        testList = (ListView) findViewById(R.id.listview1);

        List<Patient> a =  this._dao.getAllPatients();
        List<String> dropDown = new ArrayList<>();


        for (Patient p :a) {
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

    public void viewTestBtn(View view) {
        List<Test> patientTests =  this._dao.getPatientTest(_patientID);

        ArrayList<String> val = new ArrayList<>();

        for(Test t : patientTests){
            String viewSting = "";

            Nurse patientNurse = this._dao.getNurseById(t.getNurseId());
            viewSting += "NurseId: " + patientNurse.getFirstname() + " " + patientNurse.getLastname();
            viewSting += "\nPatient: "+ this.selectedPatient.getFirstname() + " " + this.selectedPatient.getLastname();
            viewSting += "\nBPL: " + t.getBpl();
            viewSting += "\nBPH: " + t.getBph();
            viewSting += "\nTemp: " + t.getTemp();

            val.add(viewSting);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, val);
        ListView list = (ListView) findViewById(R.id.listview1);
        list.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this._selectedIndex = i;
        selectedPatient = this._dao.getAllPatients().get(this._selectedIndex);
        _patientID = selectedPatient.getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this._dao.close();
    }

}
