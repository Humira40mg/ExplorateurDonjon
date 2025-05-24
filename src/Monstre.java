public class Monstre extends ElementDonjon {
    private String nom;
    private int force;
    private String skin;


    private int min;
    private int max;
    private int[] direction = {0, 0}; // déplacement par défaut

    public Monstre(String nom, int force, int x, int y, int min, int max, int[] direction) {
        this.nom = nom;
        this.force = force;
        setX(x);
        setY(y);
        this.min = min;
        this.max = max;
        this.direction = direction;
        this.skin = "( o.o )";
    }



    public void inverserDirection() {
        direction[0] *= -1;
        direction[1] *= -1;
    }

    @Override
    public String toString() {
        return estActif()
            ? "Monstre " + nom + " (" + force + ") " + skin +
              " position: (" + getX() + ", " + getY() + "), direction: (" + direction[0] + ", " + direction[1] + ")"
            : "[Monstre éliminé]";
    }
}
