import java.awt.*;

public class Norme94 implements NormeCouleurs{
    @Override
    public double distanceCouleur(Color color1, Color color2) {
        int[] lab = ConversionLAB.rgb2lab(color1.getRed(), color1.getGreen(), color1.getBlue());
        int[] lab2 = ConversionLAB.rgb2lab(color2.getRed(), color2.getGreen(), color2.getBlue());

        double l = lab[0] - lab2[0];

        double c1 = Math.sqrt(Math.pow(lab[1], 2) + Math.pow(lab[2], 2));
        double c2 = Math.sqrt(Math.pow(lab2[1], 2) + Math.pow(lab2[2], 2));

        double sc = 1 + 0.045 * c1;
        double sh = 1 + 0.015 * c1;

        double c = c1 - c2;

        double a = Math.pow(lab[1] - lab2[1], 2);
        double b = Math.pow(lab[2] - lab2[2], 2);
        double cCarré = Math.pow(c, 2);
        double h = Math.sqrt(a + b + cCarré);

        double p1 = Math.pow(l /1,2);
        double p2 = Math.pow(c /sc,2);
        double p3 = Math.pow(h /sh,2);

        return Math.sqrt(p1 + p2 + p3);
    }
}
