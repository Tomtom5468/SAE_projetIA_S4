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

    public Palette() {
        this.biome = new ArrayList<>();
    }
    public Palette(List<Biome> biomes) {
        this.biome = biomes;
    }

    void addColor(Biome b) {
        biome.add(b);
    }

    void removeColor(Biome b) {
        biome.remove(b);
    }

    void removeIndex(int i) {
        biome.remove(i);
    }
}
