package es.ieslavereda.taserfan.preferences;

import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import java.util.Arrays;
import java.util.List;

import es.ieslavereda.taserfan.R;

public class PreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preferences, rootKey);

        // EditTextPreference IP
        final EditTextPreference ip = findPreference("ipKey");
        ip.setSummary("Actualmente: " + PreferenceManager.getInstance().getIP(getContext()));
        ip.setOnPreferenceChangeListener((preference, newValue) -> {
            ip.setSummary("Actualmente: " + newValue);
            return true;
        });

        // EditTextPreference PORT
        final EditTextPreference port = findPreference("editTextPreferenceKey");
        port.setSummary("Actualmente: " + PreferenceManager.getInstance().getPort(getContext()));
        port.setOnPreferenceChangeListener((preference, newValue) -> {
            port.setSummary("Actualmente: " + newValue);
            return true;
        });

        // Theme preferences with ListPreference
        ListPreference themePreference = getPreferenceManager().findPreference(getString(R.string.settings_theme_key));
        if (themePreference.getValue() == null) {
            themePreference.setValue(ThemeSetup.Mode.DEFAULT.name());
        }
        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeSetup.applyTheme(ThemeSetup.Mode.valueOf((String) newValue));
            return true;
        });

        SwitchPreference switchPreference = findPreference("switch_preference_1");
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (switchPreference.isChecked())
                    NLMode.setMode(0);
                else
                    NLMode.setMode(1);

                return true;
            }
        });
    }
}
