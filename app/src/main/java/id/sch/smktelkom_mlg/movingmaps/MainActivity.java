package id.sch.smktelkom_mlg.movingmaps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import id.sch.smktelkom_mlg.startmaps.R;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final CameraPosition Bandung = CameraPosition.builder()
            .target(new LatLng(-6.917464, 107.619123))
            .zoom(21)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition Jkt = CameraPosition.builder()
            .target(new LatLng(-6.175110, 106.865039))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition Semarang = CameraPosition.builder()
            .target(new LatLng(-7.005145, 110.438125))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();
    static final CameraPosition Solo = CameraPosition.builder()
            .target(new LatLng(-7.575489, 110.824327))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();
    GoogleMap m_map;
    boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSeattle = (Button) findViewById(R.id.btnSolo);
        btnSeattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(Solo);
            }
        });

        Button btnDublin = (Button) findViewById(R.id.btnSemarang);
        btnDublin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(Semarang);
            }
        });

        Button btnTokyo = (Button) findViewById(R.id.btnBandung);
        btnTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(Bandung);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;
        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        flyTo(Jkt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }
}