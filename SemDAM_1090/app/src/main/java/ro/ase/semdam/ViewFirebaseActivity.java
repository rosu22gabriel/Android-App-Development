package ro.ase.semdam;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewFirebaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(this);
        List<BiletAvion> biletAvionList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("semdam-1090-default-rtdb");
        myRef.keepSynced(true);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot dn: snapshot.getChildren())
                    {
                        BiletAvion biletAvion = dn.getValue(BiletAvion.class);
                        biletAvionList.add(biletAvion);
                    }
                }
                ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        biletAvionList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("semdam-1090-default-rtdb").addValueEventListener(listener);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                BiletAvion biletAvion = biletAvionList.get(position);
                biletAvionList.remove(biletAvion);

                myRef.child("semdam-1090-default-rtdb").child(biletAvion.getUid()).removeValue();

                ArrayAdapter<BiletAvion> adapter = (ArrayAdapter<BiletAvion>)listView.getAdapter();
                adapter.notifyDataSetChanged();

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                BiletAvion biletAvion = biletAvionList.get(position);

                HashMap map = new HashMap();
                map.put("destinatie", "Paris");
                map.put("pret", 500);

                myRef.child("semdam-1090-default-rtdb").child(biletAvion.getUid()).
                        updateChildren(map);
            }
        });

        HorizontalScrollView sv = new HorizontalScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText("Lista bilete avion din Firebase\n");

        ll.addView(textView);
        ll.addView(listView);
        sv.addView(ll);

        setContentView(sv);
    }
}
