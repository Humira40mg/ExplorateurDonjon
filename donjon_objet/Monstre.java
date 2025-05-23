public class Monstre extends ElementDonjon {
    private String nom;
    private int force;
    private String skin = "( o.o )";

    private int x; // colonne (horizontal)
    private int y; // ligne (vertical)

    private int min; // borne min (x ou y)
    private int max; // borne max (x ou y)
    private int direction = 1; // 1 = avancer, -1 = reculer

    public enum DeplacementType {
        HORIZONTAL,
        VERTICAL
    }

    private DeplacementType deplacementType;

    public Monstre(String nom, int force, int x, int y, int min, int max, DeplacementType deplacementType) {
        this.nom = nom;
        this.force = force;
        this.x = x;
        this.y = y;
        this.min = min;
        this.max = max;
        this.deplacementType = deplacementType;
    }

    public void deplacer() {
        if (!estActif()) return;

        if (deplacementType == DeplacementType.VERTICAL) {
            y += 2 * direction;
            if (y >= max || y <= min) {
                direction *= -1;
                y += 2 * direction;
            }
        } else if (deplacementType == DeplacementType.HORIZONTAL) {
            x += 2 * direction;
            if (x >= max || x <= min) {
                direction *= -1;
                x += 2 * direction;
            }
        }
    }

    public int getX() 
    { 
        return x; 
    }

    public int getY() 
    { 
        return y; 
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
