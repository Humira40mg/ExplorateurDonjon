/**
 * Classe qui génère une salle avec des éléments aléatoires
 */
public class GenerateurSalle extends Salle {
    private static Salle salle; // instance de la salle courante

    /**
     * Constructeur qui appelle le constructeur de la classe Salle
     * @param elem groupe d'éléments
     * @param matrice matrice de la salle
     */
    public GenerateurSalle(GroupeDonjon elem, char[][] matrice) {
        super(elem, matrice); // Appelle le constructeur de la classe Salle
    }

    /**
     * Retourne la salle courante, si elle n'existe pas, en crée une nouvelle
     * @param elem groupe d'éléments
     * @param matrice matrice de la salle
     * @return la salle courante
     */
    public static Salle getSalle(GroupeDonjon elem, char[][] matrice) {
        if(salle == null){
            // Crée une nouvelle salle avec les paramètres par défaut ou chargés 
            salle = new Salle(elem, matrice);
        }
        return salle;
    }

    /**
     * Détruit la salle courante
     */
    public static void destroySalle() {
        salle = null; // temporaire à changer si on pense que il y a à changer 
    }
}
