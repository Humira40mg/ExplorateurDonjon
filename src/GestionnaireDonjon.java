import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionnaireDonjon {
    private List<ElementDonjon> elements = new ArrayList<>();

    public void ajouterElement(ElementDonjon element) {
        elements.add(element);
    }

    // Simulation d'une interaction avec les éléments (ex: joueur passe dessus)
    public void interaction() {
        System.out.println("Avant interaction :");
        afficherElements();

        // Désactive les pièges (simulation de contact)
        for (ElementDonjon e : elements) {
            if (e instanceof Piege || e instanceof Tresor) {
                e.despawn();
            }
        }

        // Nettoyage des éléments désactivés
        elements.removeIf(e -> !e.estActif());

        System.out.println("\nAprès interaction et nettoyage :");
        afficherElements();
    }

    // Simulation de mouvements des monstres
    public void deplacerMonstres() {
        for (ElementDonjon e : elements) {
            if (e instanceof Monstre monstre) {
                monstre.deplacer();
            }
        }
    }

    public void afficherElements() {
        if (elements.isEmpty()) {
            System.out.println("Aucun élément actif.");
            return;
        }

        for (ElementDonjon e : elements) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        GestionnaireDonjon donjon = new GestionnaireDonjon();

        donjon.ajouterElement(new Monstre("Gobelin", 10, 0, 5, 0, 10, Monstre.DeplacementType.HORIZONTAL));
        donjon.ajouterElement(new Monstre("Squelette", 12, 3, 0, 0, 6, Monstre.DeplacementType.VERTICAL));
        donjon.ajouterElement(new Tresor(100));
        donjon.ajouterElement(new Piege("Flammes", 15));
        donjon.ajouterElement(new Porte(Porte.TypePorte.ENTREE));
        donjon.ajouterElement(new Porte(Porte.TypePorte.SORTIE));

        System.out.println("=== Donjon initial ===");
        donjon.afficherElements();

        System.out.println("\n=== Déplacement des monstres ===");
        for (int i = 0; i < 4; i++) {
            donjon.deplacerMonstres();
            donjon.afficherElements();
            System.out.println("---");
        }

        System.out.println("\n=== Interaction avec éléments ===");
        donjon.interaction();
    }
}
