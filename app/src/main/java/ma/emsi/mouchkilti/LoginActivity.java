/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : LoginActivity.java 
 * Description  : Interface de connexion citoyenne
 * Responsable(s) : 
 *   - Oubaydoullah ASLI
 */
package ma.emsi.mouchkilti;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Veuillez saisir votre email");
                    etEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Veuillez saisir votre mot de passe");
                    etPassword.requestFocus();
                    return;
                }

                // Simple validation for academic demo
                Toast.makeText(LoginActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, ListeSignalementsActivity.class);
                intent.putExtra("USER_EMAIL", email);
                startActivity(intent);
                finish();
            }
        });
    }
}
