package home.me.mescomptes;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MonthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        int yearSelect = getIntent().getIntExtra("yearSelect", Calendar.getInstance().get(Calendar.YEAR));
        int positionSelect = getIntent().getIntExtra("positionSelect", 0);
        positionSelect = positionSelect +1;

        int idMonthRessource = getResources().getIdentifier("month" + positionSelect, "string", getPackageName());

        TextView title =  findViewById(R.id.activityMonthTitle);
        title.setText(getString(idMonthRessource) + " "+ yearSelect);

        Button addOperation = (Button) findViewById(R.id.btnAddOperation);
        addOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                AddOperationFragment.newInstance().show(fm,"frangment_add_operation");
            }
        });
    }
}
