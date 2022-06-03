package es.ieslavereda.taserfan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.ieslavereda.taserfan.preferences.PreferenceFragment;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenceFragment())
                .commit();
    }
}