import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlouMoyenne implements PretraitementImage {

    private static final double[][] FILTRE = {
            {1.0, 1.0, 1.0},
            {1.0, 1.0, 1.0},
            {1.0, 1.0, 1.0}
    };
    @Override
    public void appliquerFiltreConvolution(String path) throws IOException {
        // Récupération de l'image
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        //  Pour chaque groupe de 3x3 pixels
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int[] rgb = new int[3];
                // récupération des 9 pixels autour du pixel courant
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        // Vérifie si le pixel est dans les limites de l'image
                        if (i + k >= 0 && i + k < img.getWidth() && j + l >= 0 && j + l < img.getHeight()) {
                            int[] rgb2 = OutilCouleur.getTabColor(img.getRGB(i + k, j + l));
                            rgb[0] += rgb2[0] * FILTRE[k + 1][l + 1] / 9;
                            rgb[1] += rgb2[1] * FILTRE[k + 1][l + 1] / 9;
                            rgb[2] += rgb2[2] * FILTRE[k + 1][l + 1] / 9;
                        }
                    }
                }
                rgb[0] = Math.min(255, Math.max(0, rgb[0]));
                rgb[1] = Math.min(255, Math.max(0, rgb[1]));
                rgb[2] = Math.min(255, Math.max(0, rgb[2]));
                int nRGB = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
                newImg.setRGB(i, j, nRGB);
            }
        }

        ImageIO.write(newImg, "png", new File(path.replace(".png", "-flouMoyenne.png")));
    }
}