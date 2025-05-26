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
    @Override
    public ElementDonjon getElementFromPosition(int x, int y) {
        ElementDonjon elem;
        for (ElementDonjon e : enfants) {
            elem = e.getElementFromPosition(x, y);
            if (elem != null) {
                return elem;
            }
        }
        return null;
    }

    // recupere le composant parent d'un type d'instance.
    @Override
    public ElementDonjon getGroupeFromInstance(Class<?> type)
    {
        ElementDonjon elem;
        for (ElementDonjon e : enfants)
        {
            elem = e.getGroupeFromInstance(type);
            if (elem != null)
            {
                if (elem instanceof GroupeDonjon)
                    return elem;
                return this;
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

    //getter
    public List<ElementDonjon> getChildren()
    {
        return enfants;
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
