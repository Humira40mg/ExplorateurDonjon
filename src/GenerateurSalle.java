public class GenerateurSalle extends Salle {
    private static Salle salle;

    // Define an explicit constructor that calls the Salle class's constructor
    public GenerateurSalle(GroupeDonjon elem, char[][] matrice) {
        super(elem, matrice); // Call the Salle class's constructor
    }

    public static Salle getSalle(GroupeDonjon elem, char[][] matrice) {
        if(salle ==null){
            //  creer une nouvelle salle avec les parametres par defaut ou chargés 
            salle = new Salle(elem, matrice);
        }
        return salle;
    }

    public static void destroySalle() {
        salle = null; // temporaire a changer si on pense que il y a a changer 
    }
}