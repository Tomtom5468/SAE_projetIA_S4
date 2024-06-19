import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Palette {
    List<Biome> biome;
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
    }
}
