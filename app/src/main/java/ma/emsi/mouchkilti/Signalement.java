/* 
 * Projet       : Mouchkilti - Signalement de problèmes urbains
 * Fichier      : Signalement.java 
 * Description  : Modèle de données pour un signalement urbain
 * Responsable(s) : 
 *   - Ayoub ETTALBI
 */
package ma.emsi.mouchkilti;

import java.io.Serializable;

public class Signalement implements Serializable {
    private String id;
    private String titre;
    private String description;
    private String categorie;
    private String localisation;
    private String urgence;
    private String date;
    private String statut;
    private int confirmations;

    public Signalement(String id, String titre, String description, String categorie, 
                       String localisation, String urgence, String date, String statut, int confirmations) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.localisation = localisation;
        this.urgence = urgence;
        this.date = date;
        this.statut = statut;
        this.confirmations = confirmations;
    }

    public String getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public String getCategorie() { return categorie; }
    public String getLocalisation() { return localisation; }
    public String getUrgence() { return urgence; }
    public String getDate() { return date; }
    public String getStatut() { return statut; }
    public int getConfirmations() { return confirmations; }

    public void incrementConfirmations() {
        this.confirmations++;
    }

    @Override
    public String toString() {
        return titre + " [" + categorie + "] - Statut: " + statut;
    }
}
