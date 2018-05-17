package home.me.mescomptes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set current Year
        TextView currentYear =  findViewById(R.id.text_current_year);
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        currentYear.setText(String.valueOf(year));


        //Fill Vertical RecyclerView
        RecyclerView vertical_recycler_view= (RecyclerView) findViewById(R.id.vertical_recycler_view);

        ArrayList<String> verticalList=new ArrayList<>();
        fillListMonth(verticalList);

        VerticalAdapter  verticalAdapter=new VerticalAdapter(verticalList);

        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        vertical_recycler_view.setLayoutManager(verticalLayoutmanager);
        vertical_recycler_view.setAdapter(verticalAdapter);


    }

    private void fillListMonth(ArrayList<String> list) {

        list.add( getResources().getString((R.string.month1)));
        list.add( getResources().getString((R.string.month2)));
        list.add( getResources().getString((R.string.month3)));
        list.add( getResources().getString((R.string.month4)));

    }

    private class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

        private List<String> verticalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            public LinearLayout layoutItemMonth;

            public MyViewHolder(View view) {
                super(view);
                txtView = view.findViewById(R.id.txtMonth);
                layoutItemMonth = view.findViewById(R.id.itemMonth);

            }
        }


        public VerticalAdapter(List<String> verticalList) {
            this.verticalList = verticalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_item_month, parent, false);


            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            //odd even line color
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.evenLine));
            } else {
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.oddLine));
            }


            holder.txtView.setText(verticalList.get(position));

            holder.layoutItemMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("layoutItemMonth","Click layoutItem position : " + position);

                    Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                    intent.putExtra("positionSelect", position);
                    intent.putExtra("yearSelect", MainActivity.this.year);
                    MainActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return verticalList.size();
        }
    }
}
