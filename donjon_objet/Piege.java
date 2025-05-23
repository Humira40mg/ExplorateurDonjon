public class Piege extends ElementDonjon {
    private String type;
    private int degats;
    private String skin = "(X)";

    public Piege(String type, int degats) {
        this.type = type;
        this.degats = degats;
    }

    public String getType() {
        return type;
    }

    public int getDegats() {
        return degats;
    }

    @Override
    public String toString() {
        return estActif()
            ? "Piège: " + type + " (Dégâts: " + degats + ") " + skin
            : "[Piège désactivé]";
    }
}
