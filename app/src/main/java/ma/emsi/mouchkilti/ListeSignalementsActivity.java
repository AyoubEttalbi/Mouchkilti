/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : ListeSignalementsActivity.java 
 * Description  : Interface d'accueil listant les signalements avec adaptateur et menu d'options
 * Responsable(s) : 
 *   - Souhail BECHCHARI
 */
package ma.emsi.mouchkilti;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListeSignalementsActivity extends AppCompatActivity {

    private TextView tvWelcomeUser;
    private ListView lvSignalements;
    private Button btnAjouter, btnGoCarte;
    private ArrayList<Signalement> signalementsList;
    private SignalementAdapter adapter;
    private String userEmail = "Citoyen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_signalements);

        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
        lvSignalements = findViewById(R.id.lvSignalements);
        btnAjouter = findViewById(R.id.btnAjouter);
        btnGoCarte = findViewById(R.id.btnGoCarte);

        // Reception de l'intent
        Intent received = getIntent();
        if (received != null && received.hasExtra("USER_EMAIL")) {
            userEmail = received.getStringExtra("USER_EMAIL");
        }
        tvWelcomeUser.setText("Bienvenue : " + userEmail);

        // Initialisation des données fictives
        signalementsList = new ArrayList<>();
        signalementsList.add(new Signalement("1", "Gros nid-de-poule dangereux", "Un grand trou sur la chaussée causant des dégâts aux voitures.", "Voirie", "Bd Zerktouni, Casablanca", "Urgente", "20/11/2026", "En attente", 5));
        signalementsList.add(new Signalement("2", "Lampadaire éteint depuis 1 semaine", "Éclairage public totalement en panne dans la ruelle.", "Éclairage", "Hay Riad, Rabat", "Moyenne", "18/11/2026", "En cours", 3));
        signalementsList.add(new Signalement("3", "Accumulation d'ordures près de l'école", "Poubelles débordantes non ramassées.", "Propreté", "Guéliz, Marrakech", "Faible", "15/11/2026", "Résolu", 12));
        signalementsList.add(new Signalement("4", "Fuite d'eau importante sur trottoir", "L'eau potable coule abondamment sur le trottoir.", "Eau & Assainissement", "Agdal, Rabat", "Urgente", "22/11/2026", "En attente", 8));

        // Adaptateur
        adapter = new SignalementAdapter(this, signalementsList);
        lvSignalements.setAdapter(adapter);

        // Selection d'un élément -> DetailSignalementActivity
        lvSignalements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Signalement selected = signalementsList.get(position);
                Intent detailIntent = new Intent(ListeSignalementsActivity.this, DetailSignalementActivity.class);
                detailIntent.putExtra("SIGNALEMENT_OBJ", selected);
                startActivity(detailIntent);
            }
        });

        // Navigation vers ajout de signalement
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(ListeSignalementsActivity.this, AddSignalementActivity.class);
                startActivity(addIntent);
            }
        });

        // Navigation vers la carte
        btnGoCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(ListeSignalementsActivity.this, MapSignalementsActivity.class);
                startActivity(mapIntent);
            }
        });
    }

    // Gestion du menu d'options (Options Menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_map) {
            startActivity(new Intent(this, MapSignalementsActivity.class));
            return true;
        } else if (id == R.id.action_my_reports) {
            Intent intent = new Intent(this, MesSignalementsActivity.class);
            intent.putExtra("USER_EMAIL", userEmail);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_refresh) {
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Liste des signalements actualisée", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_logout) {
            Intent logoutIntent = new Intent(this, LoginActivity.class);
            startActivity(logoutIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
