/**
 * Classe static qui s'occupera d'afficher le necessaire dans le terminal
 */
public class View
{
    /**
     * Efface ce qui est affiché sur le terminal
     */
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Affiche le menu principale
     */
    public static void menuPrincipal()
    {
        String[] option = {"> *Facile*", "Difficile", "Commencer"};
        int selectedIndex = 0;

        while (true)
        {
            System.out.println(">---------------<| ExploDon |>---------------<");
            System.out.println();
            System.out.println("                Difficultée :");
            System.out.println("                   " + option[0]);
            System.out.println("                   " + option[1]);
            System.out.println();
            System.out.println("                <| " + option[2] + " |>");
            System.out.println();
            System.out.println(">--------------------------------------------<");
        }


    }
}