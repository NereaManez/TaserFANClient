package es.ieslavereda.taserfan.preferences;

import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeSetup {
    private ThemeSetup() {
    }

    public enum Mode {
        DEFAULT, DARK, LIGHT
    }

    public static void applyTheme(Mode mode) {
        switch (mode) {
            case DARK:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case LIGHT:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
        }
    }

    public static void applyPreferenceTheme(Context context) {

        applyTheme(Mode.valueOf(PreferenceManager.getInstance().getTheme(context)));
    }
}
