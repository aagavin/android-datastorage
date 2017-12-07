package ca.aagavin.hospitalapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.R;
import ca.aagavin.hospitalapp.beans.Doctor;
import ca.aagavin.hospitalapp.beans.Nurse;
import ca.aagavin.hospitalapp.beans.Patient;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private CommonDAO _dao;
    private int _isDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.userSelection);

        radioGroup.setOnCheckedChangeListener(this);

        this._dao = new CommonDAO(this);
        this._isDoctor = -1;

        Doctor d = new Doctor();
        d.setFirstname("aaron");
        d.setLastname("Smith");
        d.setDepartment("radiology");
        d.setPassword("pass");

        Nurse n = new Nurse();
        n.setFirstname("n");
        n.setLastname("Smith");
        n.setDepartment("radiology");
        n.setPassword("n");

        Patient p = new Patient();
        p.setFirstname("testPatient");
        p.setLastname("testLastName");
        p.setDepartment("radiology");
        p.setDoctorId(1);
        p.setRoom(123);

        Patient p2 = new Patient();
        p2.setFirstname("testPatient 2");
        p2.setLastname("testLastName 2");
        p2.setDepartment("radiology");
        p2.setDoctorId(1);
        p2.setRoom(321);

        this._dao.createEntity(d);
        this._dao.createEntity(p);
        this._dao.createEntity(n);
        this._dao.createEntity(p2);

    }

    public void loginBtnClick(View view) {
        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        if(this._verifyNotEmpty(username, password)) {

            switch (this._isDoctor){
                case -1:
                    Toast.makeText(this, "Please select user type (Doctor, Nurse)", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Doctor doctor = this._dao.loginDoctor(username.getText().toString(), password.getText().toString());
                    if (doctor !=null) {
                        SharedPreferences.Editor editor = getSharedPreferences("loginid", MODE_PRIVATE).edit();
                        editor.putInt("id", doctor.getDoctorId());
                        editor.putBoolean("isDoctor", true);
                        editor.apply();

                        startActivity(new Intent(this, MenuActivity.class));
                    }
                    else{
                        Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    Nurse nurse = this._dao.loginNurse(username.getText().toString(), password.getText().toString());
                    if (nurse != null){
                        SharedPreferences.Editor editor = getSharedPreferences("loginid", MODE_PRIVATE).edit();
                        editor.putInt("id", nurse.getNurseId());
                        editor.putBoolean("isDoctor", false);
                        editor.apply();

                        startActivity(new Intent(this, MenuActivity.class));
                    }
                    else{
                        Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }


    private boolean _verifyNotEmpty(EditText username, EditText password) {

        if (username.getText().toString().isEmpty()){
            username.setError("Username can't be empty");
            return false;
        }
        else if (password.getText().toString().isEmpty()) {
            password.setError("Password can't be empty");
            return false;
        }
        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this._dao.close();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        String selection = ((RadioButton) findViewById(i)).getText().toString();

        switch (selection){
            case "Nurse":
                this._isDoctor = 0;
                break;
            case "Doctor":
                this._isDoctor = 1;
                break;
            default:
                break;
        }
    }
}
