/**
 * Classe représentant un Monstre dans le Donjon.
 * Hérite d'ElementDonjon.
 */
public class Monstre extends ElementDonjon {
    private String nom; // Nom du monstre
    private int force; // Force du monstre
    private String skin; // Apparence visuelle du monstre

    private int min; // Limite inférieure de déplacement
    private int max; // Limite supérieure de déplacement
    private int[] direction = {0, 0}; // Direction de déplacement par défaut

    /**
     * Constructeur pour initialiser un monstre.
     *
     * @param nom Le nom du monstre
     * @param force La force du monstre
     * @param x La coordonnée X initiale
     * @param y La coordonnée Y initiale
     * @param min La limite inférieure de déplacement
     * @param max La limite supérieure de déplacement
     * @param direction La direction initiale de déplacement
     */
    public Monstre(String nom, int force, int x, int y, int min, int max, int[] direction) {
        this.nom = nom;
        this.force = force;
        setX(x);
        setY(y);
        this.min = min;
        this.max = max;
        this.direction = direction;
        skin = "( o.o )";
    }

    /**
     * Inverse la direction de déplacement du monstre.
     */
    public void inverserDirection() {
        direction[0] *= -1;
        direction[1] *= -1;
    }

    /**
     * Inflige des dégâts au joueur en cas de contact.
     *
     * @param joueur Le joueur touché par le monstre
     */
    public boolean touch(Joueur joueur) {
        if (!joueur.isAlive()) return false;
        joueur.takeDamage(force);
        return true;
    }
    /**
     * Représentation textuelle du monstre.
     *
     * @return Une chaîne de caractères décrivant le monstre
     */
    @Override
    public String toString() {
        return estActif()
            ? "Monstre " + nom + " (" + force + ") " + skin +
              " position: (" + getX() + ", " + getY() + "), direction: (" + direction[0] + ", " + direction[1] + ")"
            : "[Monstre éliminé]";
    }
}

