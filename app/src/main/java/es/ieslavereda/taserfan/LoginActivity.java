package es.ieslavereda.taserfan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import es.ieslavereda.taserfan.api.API;
import es.ieslavereda.taserfan.api.Connector;
import es.ieslavereda.taserfan.api.Result;
import es.ieslavereda.taserfan.entity.AuthenticateData;
import es.ieslavereda.taserfan.base.BaseActivity;
import es.ieslavereda.taserfan.base.CallInterface;
import es.ieslavereda.taserfan.entity.Employee;
import es.ieslavereda.taserfan.base.LoggedInUserRepository;
import es.ieslavereda.taserfan.preferences.PreferenceManager;
import es.ieslavereda.taserfan.preferences.ThemeSetup;

public class LoginActivity extends BaseActivity implements CallInterface {

    Result<Employee> result;
    private Button b;
    private EditText email, pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ThemeSetup.applyPreferenceTheme(getApplicationContext());

        b = findViewById(R.id.login_button);
        email = findViewById(R.id.TextEmailAddress);
        pswd = findViewById(R.id.TextPassword);

        ThemeSetup.applyPreferenceTheme(this);
        API.Routes.changeURL(PreferenceManager.getInstance().getIP(this), PreferenceManager.getInstance().getPort(this));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCall(LoginActivity.this);
            }
        });
    }

    public void executeCall(CallInterface callInterface) {
        super.executeCall(callInterface);
    }

    @Override
    public void doInBackground() {
        AuthenticateData a = new AuthenticateData(email.getText().toString(), pswd.getText().toString());
        result = Connector.getConector().post(Employee.class, a, "/employee");
    }

    @Override
    public void doInUI() {
        if (result instanceof Result.Success) {
            LoggedInUserRepository.getInstance().login((((Result.Success<Employee>) result).getData()));
            Toast.makeText(this,"Acceso", Toast.LENGTH_LONG).show();
        } else {
            Result.Error r = (Result.Error) result;
            Toast.makeText(this, r.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.configuracion):
                Intent intentPreferenciasActivity = new Intent(this, PreferencesActivity.class);
                startActivity(intentPreferenciasActivity);
                return true;
            case (R.id.exit):
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", email.getText().toString());
        outState.putString("password", pswd.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        email.setText(savedInstanceState.getString("email"));
        pswd.setText(savedInstanceState.getString("password"));
    }
}