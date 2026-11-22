/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : AddSignalementActivity.java 
 * Description  : Formulaire de création d'un signalement avec Spinner, RadioGroup, DatePickerDialog et AlertDialog
 * Responsable(s) : 
 *   - Ayoub ETTALBI
 */
package ma.emsi.mouchkilti;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddSignalementActivity extends AppCompatActivity {

    private EditText etTitre, etDescription, etLocalisation;
    private Spinner spCategorie;
    private RadioGroup rgUrgence;
    private Button btnDate, btnSubmit;
    private TextView tvDateAffichee;
    private String selectedDate = "22/11/2026";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_signalement);

        etTitre = findViewById(R.id.etTitre);
        etDescription = findViewById(R.id.etDescription);
        etLocalisation = findViewById(R.id.etLocalisation);
        spCategorie = findViewById(R.id.spCategorie);
        rgUrgence = findViewById(R.id.rgUrgence);
        btnDate = findViewById(R.id.btnDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvDateAffichee = findViewById(R.id.tvDateAffichee);

        // Alimentation du Spinner avec ArrayAdapter
        String[] categories = {"Voirie & Chaussée", "Éclairage Public", "Propreté & Déchets", "Eau & Assainissement", "Espaces Verts"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spCategorie.setAdapter(catAdapter);

        // DatePickerDialog
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddSignalementActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        selectedDate = d + "/" + (m + 1) + "/" + y;
                        tvDateAffichee.setText("Date : " + selectedDate);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        // Validation & AlertDialog pour confirmation
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String titre = etTitre.getText().toString().trim();
                final String desc = etDescription.getText().toString().trim();
                final String loc = etLocalisation.getText().toString().trim();

                if (TextUtils.isEmpty(titre)) {
                    etTitre.setError("Le titre est obligatoire");
                    return;
                }
                if (TextUtils.isEmpty(loc)) {
                    etLocalisation.setError("La localisation est requise");
                    return;
                }

                // Confirmation avec AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AddSignalementActivity.this);
                builder.setTitle("Confirmation du signalement");
                builder.setMessage("Voulez-vous vraiment publier ce problème urbain aux services communaux ?");
                builder.setPositiveButton("Oui, publier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddSignalementActivity.this, "Signalement enregistré avec succès !", Toast.LENGTH_LONG).show();
                        finish(); // Retourner à la liste
                    }
                });
                builder.setNegativeButton("Annuler", null);
                builder.show();
            }
        });
    }
}
