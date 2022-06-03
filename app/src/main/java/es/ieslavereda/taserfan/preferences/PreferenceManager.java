package es.ieslavereda.taserfan.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import es.ieslavereda.taserfan.R;

public class PreferenceManager {
    private SharedPreferences pref;
    private static PreferenceManager preferenceManager;

    private PreferenceManager(){

    }

    public static PreferenceManager getInstance(){
        if(preferenceManager ==null)
            preferenceManager = new PreferenceManager();
        return preferenceManager;
    }

    private void inicializa(Context context) {
        if (pref == null)
            pref = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUnidades(Context context){
        inicializa(context);
        return pref.getString("unidades","standard");
    }

    public String getEditTextPreference(Context context){
        inicializa(context);
        return pref.getString("editTextPreferenceKey","389");
    }

    public boolean getCheckBoxPreference(Context context){
        inicializa(context);
        return pref.getBoolean("checkBoxPreferenceKey",false);
    }
    public String getTheme(Context context){
        inicializa(context);
        return pref.getString(context.getString(R.string.settings_theme_key),ThemeSetup.Mode.DEFAULT.name());
    }
}
