public class Tresor extends ElementDonjon {
    private int valeur_score;
    private String skin = "[-o-]";

    public Tresor(int valeur_score) {
        this.valeur_score = valeur_score;
    }

    public int getValeur_score() {
        return valeur_score;
    }

    @Override
    public String toString() {
        return estActif()
            ? "( Trésor avec Valeur: " + valeur_score + ") " + skin
            : "[Trésor ramassé]";
    }
}
