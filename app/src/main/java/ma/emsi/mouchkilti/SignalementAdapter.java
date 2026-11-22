/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : SignalementAdapter.java 
 * Description  : Adaptateur personnalisé ArrayAdapter pour afficher la collection de signalements
 * Responsable(s) : 
 *   - Souhail BECHCHARI
 */
package ma.emsi.mouchkilti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class SignalementAdapter extends ArrayAdapter<Signalement> {

    public SignalementAdapter(Context context, List<Signalement> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_signalement, parent, false);
        }

        Signalement item = getItem(position);
        if (item != null) {
            TextView tvTitre = convertView.findViewById(R.id.tvItemTitre);
            TextView tvStatut = convertView.findViewById(R.id.tvItemStatut);
            TextView tvCat = convertView.findViewById(R.id.tvItemCategorie);
            TextView tvLoc = convertView.findViewById(R.id.tvItemLocalisation);

            tvTitre.setText(item.getTitre());
            tvStatut.setText(item.getStatut());
            tvCat.setText("Catégorie : " + item.getCategorie());
            tvLoc.setText("Lieu : " + item.getLocalisation());
        }

        return convertView;
    }
}
