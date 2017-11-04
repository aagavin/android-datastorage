package ca.aagavin.hospitalapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ca.aagavin.hospitalapp.R;

public class AddTestDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);
    }

    public void saveTestBtn(View view) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }
}
