/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : MapSignalementsActivity.java 
 * Description  : Affichage interactif des points de signalements avec navigation vers les détails
 * Responsable(s) : 
 *   - Souhail BECHCHARI
 */
package ma.emsi.mouchkilti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MapSignalementsActivity extends AppCompatActivity {

    private Button btnMarker1, btnMarker2, btnMarker3, btnRetourListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_signalements);

        btnMarker1 = findViewById(R.id.btnMarker1);
        btnMarker2 = findViewById(R.id.btnMarker2);
        btnMarker3 = findViewById(R.id.btnMarker3);
        btnRetourListe = findViewById(R.id.btnRetourListe);

        btnMarker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail("Gros nid-de-poule dangereux", "Voirie", "Bd Zerktouni, Casablanca", "En attente");
            }
        });

        btnMarker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail("Lampadaire éteint depuis 1 semaine", "Éclairage", "Hay Riad, Rabat", "En cours");
            }
        });

        btnMarker3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail("Accumulation d'ordures près de l'école", "Propreté", "Guéliz, Marrakech", "Résolu");
            }
        });

        btnRetourListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openDetail(String titre, String cat, String loc, String statut) {
        Signalement sig = new Signalement("M1", titre, "Signalement sélectionné depuis la vue cartographique.", cat, loc, "Moyenne", "22/11/2026", statut, 2);
        Intent intent = new Intent(MapSignalementsActivity.this, DetailSignalementActivity.class);
        intent.putExtra("SIGNALEMENT_OBJ", sig);
        startActivity(intent);
    }
}
