public class Monstre {
    private String nom;
    private int force;
    private String skin = "( o.o )";

    public Monstre(String nom, int force){
        this.nom = nom;
        this.force = force;
    }

    public String getNOm(){
        return nom;
    }

    public int getForce(){
        return force;
    }

    public String toString(){
        return "Monstre " + nom + " (" + force + ") " + skin;
    }
}


/* programmer aussi le deplacement des monstres  avant arriere , en haut et en bas */ 
