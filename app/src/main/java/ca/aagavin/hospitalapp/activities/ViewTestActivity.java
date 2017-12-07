package ca.aagavin.hospitalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ca.aagavin.hospitalapp.R;

public class ViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
    }

    public void viewTestBtn(View view) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

}
