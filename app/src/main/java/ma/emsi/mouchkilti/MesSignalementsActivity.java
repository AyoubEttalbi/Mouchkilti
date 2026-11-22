/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : MesSignalementsActivity.java 
 * Description  : Interface de suivi des signalements de l'utilisateur avec démonstration du cycle de vie
 * Responsable(s) : 
 *   - Ayoub ETTALBI
 */
package ma.emsi.mouchkilti;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MesSignalementsActivity extends AppCompatActivity {

    private static final String TAG = "Mouchkilti_Lifecycle";
    private ListView lvMesSignalements;
    private Button btnFermer;
    private TextView tvSub;
    private ArrayList<Signalement> mesList;
    private SignalementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_signalements);
        Log.d(TAG, "MesSignalementsActivity: onCreate() appelé");

        lvMesSignalements = findViewById(R.id.lvMesSignalements);
        btnFermer = findViewById(R.id.btnFermerMesSig);
        tvSub = findViewById(R.id.tvMesSignalementsSub);

        String email = getIntent().getStringExtra("USER_EMAIL");
        if (email != null) {
            tvSub.setText("Signalements déclarés par : " + email);
        }

        // Données fictives personnelles
        mesList = new ArrayList<>();
        mesList.add(new Signalement("101", "Mon signalement : Poubelles débordantes", "Déchets accumulés devant mon immeuble.", "Propreté", "Rabat, Hassan", "Faible", "21/11/2026", "En attente", 2));
        mesList.add(new Signalement("102", "Mon signalement : Fuite canalisation", "Fuite d'eau sur le trottoir.", "Eau & Assainissement", "Rabat, Agdal", "Urgente", "19/11/2026", "En cours", 7));

        adapter = new SignalementAdapter(this, mesList);
        lvMesSignalements.setAdapter(adapter);

        btnFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Gestion du cycle de vie requise par le TP1
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MesSignalementsActivity: onStart() appelé");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MesSignalementsActivity: onResume() appelé");
        Toast.makeText(this, "Statut de vos signalements actualisé", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MesSignalementsActivity: onPause() appelé");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MesSignalementsActivity: onStop() appelé");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MesSignalementsActivity: onDestroy() appelé");
    }
}
