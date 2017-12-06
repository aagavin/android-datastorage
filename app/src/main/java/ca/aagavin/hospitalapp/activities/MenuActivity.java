package ca.aagavin.hospitalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.aagavin.hospitalapp.R;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ((ListView) findViewById(R.id.options)).setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long index) {

        switch ((int) index){
            case 0:
                startActivity(new Intent(this, ViewTestActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, AddTestDataActivity.class));
                break;
            default:
                break;
        }
    }
}
