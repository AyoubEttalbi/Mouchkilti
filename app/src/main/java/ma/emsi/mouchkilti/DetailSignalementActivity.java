/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : DetailSignalementActivity.java 
 * Description  : Affichage des détails d'un problème avec Intent, vote citoyen et PopupMenu
 * Responsable(s) : 
 *   - Yassine Lamghari El Kossori
 */
package ma.emsi.mouchkilti;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class DetailSignalementActivity extends AppCompatActivity {

    private TextView tvTitre, tvCategorie, tvLocalisation, tvDate, tvStatut, tvDesc;
    private Button btnSoutenir, btnActionsPopup, btnRetourDetail;
    private Signalement currentSignalement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_signalement);

        tvTitre = findViewById(R.id.tvDetailTitre);
        tvCategorie = findViewById(R.id.tvDetailCategorie);
        tvLocalisation = findViewById(R.id.tvDetailLocalisation);
        tvDate = findViewById(R.id.tvDetailDate);
        tvStatut = findViewById(R.id.tvDetailStatut);
        tvDesc = findViewById(R.id.tvDetailDesc);
        btnSoutenir = findViewById(R.id.btnSoutenir);
        btnActionsPopup = findViewById(R.id.btnActionsPopup);
        btnRetourDetail = findViewById(R.id.btnRetourDetail);

        // Reception de l'objet transmis via Intent
        if (getIntent() != null && getIntent().hasExtra("SIGNALEMENT_OBJ")) {
            currentSignalement = (Signalement) getIntent().getSerializableExtra("SIGNALEMENT_OBJ");
            if (currentSignalement != null) {
                tvTitre.setText(currentSignalement.getTitre());
                tvCategorie.setText("Catégorie : " + currentSignalement.getCategorie());
                tvLocalisation.setText("Lieu : " + currentSignalement.getLocalisation());
                tvDate.setText("Date : " + currentSignalement.getDate());
                tvStatut.setText("Statut : " + currentSignalement.getStatut());
                tvDesc.setText(currentSignalement.getDescription());
                btnSoutenir.setText("Je confirme ce problème (" + currentSignalement.getConfirmations() + " votes)");
            }
        }

        // Action de soutien (+1 vote)
        btnSoutenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSignalement != null) {
                    currentSignalement.incrementConfirmations();
                    btnSoutenir.setText("Je confirme ce problème (" + currentSignalement.getConfirmations() + " votes)");
                    Toast.makeText(DetailSignalementActivity.this, "Merci pour votre confirmation citoyenne !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // PopupMenu
        btnActionsPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(DetailSignalementActivity.this, btnActionsPopup);
                popup.getMenu().add("Partager le signalement");
                popup.getMenu().add("Signaler comme faux incident");
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(DetailSignalementActivity.this, item.getTitle() + " effectué", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

        btnRetourDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
