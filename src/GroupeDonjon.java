import java.util.ArrayList;
import java.util.List;

public class GroupeDonjon extends ElementDonjon {
    private List<ElementDonjon> enfants = new ArrayList<>();

    // Ajouter un élément au groupe
    public void ajouter(ElementDonjon e) {
        enfants.add(e);
    }

    // Retirer un élément du groupe
    public void retirer(ElementDonjon e) {
        enfants.remove(e);
    }

    // Récupérer l'élément à une position donnée (ou null si absent)
    public ElementDonjon getElementFromPosition(int x, int y) {
        for (ElementDonjon e : enfants) {
            if (e.isAt(x, y)) {
                return e;
            }
        }
        return null;
    }


    // Désactivation des enfants
    @Override
    public void despawn() {
        super.despawn();
        for (ElementDonjon e : enfants) {
            e.despawn();
        }
    }

    // Affichage
    @Override
    public String toString() {
        if (!estActif()) return "[Groupe inactif]";
        StringBuilder sb = new StringBuilder("GroupeDonjon :\n");
        for (ElementDonjon e : enfants) {
            sb.append("  ").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
