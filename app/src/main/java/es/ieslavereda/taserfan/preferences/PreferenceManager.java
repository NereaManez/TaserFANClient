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

    public String getIP(Context context){
        inicializa(context);
        return pref.getString("ipKey", "10.19.11.3");
    }

    public String getPort(Context context){
        inicializa(context);
        return pref.getString("puertoKey", "4567");
    }

    private void inicializa(Context context) {
        if (pref == null)
            pref = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getTheme(Context context){
        inicializa(context);
        return pref.getString(context.getString(R.string.settings_theme_key),ThemeSetup.Mode.DEFAULT.name());
    }
}
