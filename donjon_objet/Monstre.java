public class Monstre extends ElementDonjon {
    private String nom;
    private int force;
    private String skin = "( o.o )";


    public enum DeplacementType {
        HORIZONTAL,
        VERTICAL
    }

    private DeplacementType deplacementType;

    public Monstre(String nom, int force) {
        this.nom = nom;
        this.force = force;

    }

    public String getNom() 
    { 
        return nom; 
    }

    public int getForce() 
    { 
        return force; 
    }

    @Override
    public String toString() {
        return estActif()
            ? "Monstre " + nom + " (" + force + ") " + skin + " position: (" + x + ", " + y + ")"
            : "[Monstre éliminé]";
    }
}
