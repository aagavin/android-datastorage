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

        this._dao.createEntity(d);

    }

    public void loginBtnClick(View view) {
        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        if(this._verifyNotEmpty(username, password)) {

            if (this._isDoctor == -1){
                Toast.makeText(this, "Please select user type (Doctor, Nurse)", Toast.LENGTH_SHORT).show();
            }
            else if(this._isDoctor == 1){
                Doctor doctor = this._dao.loginDoctor(username.getText().toString(), password.getText().toString());
                if (doctor !=null) {
                    SharedPreferences.Editor editor = getSharedPreferences("loginid", MODE_PRIVATE).edit();
                    editor.putInt("docId", doctor.getDoctorId());
                    editor.apply();

                    startActivity(new Intent(this, TestDataActivity.class));
                }
                else{
                    Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }


            }
            else {
                Nurse nurse = this._dao.loginNurse(username.getText().toString(), password.getText().toString());
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
