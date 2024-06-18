import java.awt.*;

public class Biome {
    Color couleur;
    String nom;
    public Biome(Color couleur, String nom) {
        this.couleur = couleur;
        this.nom = nom;
    }

    static final Biome TUNDRA = new Biome(new Color(71,70,61), "Tundra");
    static final Biome TAIGA = new Biome(new Color(43,50,35), "Taiga");
    static final Biome FORET_TEMPEREE = new Biome(new Color(59,66,43), "Forêt tempérée");
    static final Biome FORET_TROPICALE = new Biome(new Color(46,64,34), "Forêt tropicale");
    static final Biome SAVANE = new Biome(new Color(84,106,70), "Savane");
    static final Biome PRAIRIE = new Biome(new Color(104,95,82),"Prairie");
    static final Biome DESERT = new Biome(new Color(152,140,120), "Désert");
    static final Biome GLACIER = new Biome(new Color(200,200,200), "Glacier");
    static final Biome EAU_PEU_PROFONDE = new Biome(new Color(49,83,100), "Eau peu profonde");
    static final Biome EAU_PROFONDE = new Biome(new Color(12,31,47), "Eau profonde");
}
