package ca.aagavin.hospitalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.R;
import ca.aagavin.hospitalapp.beans.Patient;

public class EditPatientActivity extends AppCompatActivity {

    private CommonDAO _dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);


        this._dao = new CommonDAO(this);

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



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        this._dao.close();
    }
}
