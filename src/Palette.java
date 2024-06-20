import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Palette {
    List<Biome> biome;
    NormeCouleurs distanceCouleur;
    final static Palette DEFAULT = new Palette(List.of(
            Biome.TUNDRA,
            Biome.TAIGA,
            Biome.FORET_TEMPEREE,
            Biome.FORET_TROPICALE,
            Biome.SAVANE,
            Biome.PRAIRIE,
            Biome.DESERT,
            Biome.GLACIER,
            Biome.EAU_PEU_PROFONDE,
            Biome.EAU_PROFONDE
    ));

    public Palette(List<Biome> biomes) {
        this.biome = biomes;
        this.distanceCouleur = new NormeCIELAB();
    }

    public Color getPlusProche(Color c){
        double distance = Double.MAX_VALUE;
        Color plusProche = null;
        for (int i = 0; i < biome.size(); i++) {
            double d = distanceCouleur.distanceCouleur(c, biome.get(i).couleur);
            if (d < distance){
                distance = d;
                plusProche = biome.get(i).couleur;
            }
        }
        return plusProche;
    }
}
