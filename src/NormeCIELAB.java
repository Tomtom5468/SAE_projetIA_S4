import java.awt.*;

public class NormeCIELAB implements NormeCouleurs{
    @Override
    public double distanceCouleur(Color color1, Color color2) {
        int[] lab = ConversionLAB.rgb2lab(color1.getRed(), color1.getGreen(), color1.getBlue());
        int[] lab2 = ConversionLAB.rgb2lab(color2.getRed(), color2.getGreen(), color2.getBlue());

        double l = Math.pow(lab[0] - lab2[0], 2);
        double a = Math.pow(lab[1] - lab2[1], 2);
        double b = Math.pow(lab[2] - lab2[2], 2);
        return Math.sqrt(l + a + b);
    }
}
