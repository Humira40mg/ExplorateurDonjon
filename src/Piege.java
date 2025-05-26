/**
 * Classe représentant un piège dans le donjon.
 * 
 * @author NGUYEN Alexandre et Nathan Milllot
 *
 */
public class Piege extends ElementDonjon {
    /**
     * Nombre de dégâts infligés par le piège.
     */
    private int degats;

    /**
     * Constructeur.
     */
    public Piege() {
        this.degats = 25;
        skin = "X"; //Apparence visuelle
    }

    /**
     * Méthode qui inflige les dégâts du piège au joueur.
     * 
     * @param joueur Joueur qui entre en contact avec le piège.
     */
    public boolean touch(Joueur joueur) {
        if (!joueur.isAlive()) return false;
        joueur.takeDamage(degats);
        return true;
    }

    /**
     * Retourne une chaîne de caractère représentant le piège.
     * 
     * @return Chaîne de caractère représentant le piège.
     */
    @Override
    public String toString() {
        return estActif()
            ? "Piège: (Dégâts: " + degats + ") " + skin
            : "[Piège désactivé]";
    }
}

