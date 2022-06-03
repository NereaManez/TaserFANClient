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

        // Modificacion de la vista de preferencias por codigo
        // ListPreference
        final ListPreference unidades = findPreference("unidades");
        final List<String> unidades_entries = Arrays.asList(getResources().getStringArray(R.array.unidades_entries));
        final List<String> unidades_values = Arrays.asList(getResources().getStringArray(R.array.unidades_values));

        int pos  = unidades_values.indexOf(PreferenceManager.getInstance().getUnidades(getContext()));

        unidades.setSummary("Unidades en " + unidades_entries.get(pos));
        unidades.setOnPreferenceChangeListener((preference, newValue) -> {

            int pos1 = unidades_values.indexOf(newValue);
            unidades.setSummary("Unidades en " + unidades_entries.get(pos1));

            return true;
        });

        // EditTextPreference
        final EditTextPreference editTextPreference = findPreference("editTextPreferenceKey");
        editTextPreference.setSummary("Actualmente: " + PreferenceManager.getInstance().getEditTextPreference(getContext()));
        editTextPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            editTextPreference.setSummary("Actualmente: " + newValue);
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
