package ca.aagavin.hospitalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ca.aagavin.hospitalapp.DAO.CommonDAO;
import ca.aagavin.hospitalapp.beans.Doctor;

public class MainActivity extends AppCompatActivity {

    private CommonDAO _dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this._dao = new CommonDAO(this);
        Doctor d = new Doctor();
        d.setFirstname("Aaron");
        d.setLastname("Smith");
        d.setDepartment("radiology");
        d.setPassword("pass");

        this._dao.createEntity(d);

    }

    public void loginBtnClick(View view) {
        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        if(this._verifyNotEmpty(username, password)) {


            Toast.makeText(this, "button ", Toast.LENGTH_SHORT).show();
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
}
