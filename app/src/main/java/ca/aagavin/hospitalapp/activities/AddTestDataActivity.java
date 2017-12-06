package ca.aagavin.hospitalapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.R;
import ca.aagavin.hospitalapp.beans.Patient;

public class AddTestDataActivity extends AppCompatActivity {

    private CommonDAO _dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

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

    public void saveTestBtn(View view) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }
}
