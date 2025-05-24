import java.util.ArrayList;
import java.util.List;

/**
 * Représente un groupe d'éléments du donjon.
 * 
 * Un groupe peut contenir d'autres groupes ou des éléments du donjon (monstres,
 * pièges, trésors, portes, ...).
 * 
 * @author NGUYEN Alexandre et Nathan Milliot
 */

public class GroupeDonjon extends ElementDonjon {
    /**
     * Liste des sous-groupes du groupe.
     */
    private List<GroupeDonjon> sousGroupes = new ArrayList<>();
    /**
     * Liste des éléments du groupe.
     */
    private List<ElementDonjon> enfants = new ArrayList<>();
    /**
     * Type du groupe (null pour le groupe racine).
     */
    private Class<? extends ElementDonjon> typeGroupe;

    /**
     * Constructeur par défaut pour le groupe racine.
     */
    public GroupeDonjon() {
        this.typeGroupe = null; // Groupe racine
    }

    /**
     * Constructeur avec le type du groupe.
     * 
     * @param type
     *            Le type du groupe.
     */
    public GroupeDonjon(Class<? extends ElementDonjon> type) {
        this.typeGroupe = type;
    }

    /**
     * Ajoute un élément dans le bon sous-groupe.
     * 
     * @param e
     *            L'élément à ajouter.
     */
    public void ajouter(ElementDonjon e) {
        Class<? extends ElementDonjon> type = e.getClass();
        for (GroupeDonjon g : sousGroupes) {
            if (g.typeGroupe.equals(type)) {
                g.enfants.add(e);
                return;
            }
        }

        GroupeDonjon nouveauGroupe = new GroupeDonjon(type);
        nouveauGroupe.enfants.add(e);
        sousGroupes.add(nouveauGroupe);
    }

    /**
     * Supprime un élément.
     * 
     * @param e
     *            L'élément à supprimer.
     */
    public void retirer(ElementDonjon e) {
        for (GroupeDonjon g : sousGroupes) {
            if (g.typeGroupe.equals(e.getClass())) {
                g.enfants.remove(e);
                return;
            }
        }
    }

    /**
     * Retourne un élément à une position donnée.
     * 
     * @param x
     *            La coordonnée X.
     * @param y
     *            La coordonnée Y.
     * @return L'élément à la position donnée si il existe, null sinon.
     */
    public ElementDonjon getElementFromPosition(int x, int y) {
        for (GroupeDonjon g : sousGroupes) {
            for (ElementDonjon e : g.enfants) {
                if (e.estActif() && e.isAt(x, y)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Nettoyage des éléments inactifs.
     */
    public void nettoyer() {
        for (GroupeDonjon g : sousGroupes) {
            g.enfants.removeIf(e -> !e.estActif());
        }
    }

    @Override
    /**
     * Interaction avec le joueur.
     * 
     * @param joueur
     *            Le joueur qui interagit.
     */
    public void touch(Joueur joueur) {
        for (GroupeDonjon g : sousGroupes) {
            for (ElementDonjon e : g.enfants) {
                e.touch(joueur);
            }
        }
    }

    @Override
    /**
     * Désactive le groupe et ses éléments.
     */
    public void despawn() {
        for (GroupeDonjon g : sousGroupes) {
            for (ElementDonjon e : g.enfants) {
                e.despawn();
            }
        }
        super.despawn();
    }

    @Override
    /**
     * Retourne une chaîne de caractères décrivant le groupe.
     * 
     * @return Une chaîne de caractères décrivant le groupe.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (typeGroupe == null) {
            sb.append("Contenu du Donjon :\n");
        } else {
            sb.append("Groupe de ").append(typeGroupe.getSimpleName()).append(" :\n");
            for (ElementDonjon e : enfants) {
                sb.append("   ").append(e.toString()).append("\n");
            }
        }

        for (GroupeDonjon g : sousGroupes) {
            sb.append(g.toString());
        }

        return sb.toString();
    }
}

