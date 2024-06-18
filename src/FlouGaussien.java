import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FlouGaussien implements PretraitementImage{
    final static int[][] TROIS = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
    final static int[][] CINQ = {{1, 4, 7, 4, 1},{4, 16, 26, 16, 4}, {7, 26, 41, 26, 7}, {4, 16, 26, 16, 4}, {1, 4, 7, 4, 1}};
    final static int[][] SEPT = {{0, 0, 1, 2, 1, 0, 0}, {0, 3, 13, 22, 13, 3, 0}, {1, 13, 59, 97, 59, 13, 1}, {2, 22, 97, 159, 97, 22, 2}, {1, 13, 59, 97, 59, 13, 1}, {0, 3, 13, 22, 13, 3, 0}, {0, 0, 1, 2, 1, 0, 0}};
    int[][] filtre;
    int coeff;

    public FlouGaussien(int taille) {
        if(taille == 3) {
            filtre = TROIS;
            coeff = 16;
        } else if(taille == 5) {
            filtre = CINQ;
            coeff = 273;
        } else if(taille == 7) {
            filtre = SEPT;
            coeff = 1003;
        }else{
            filtre = TROIS;
            coeff = 16;
        }
    }

    @Override
    public void appliquerFiltreConvolution(String path) throws IOException {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);


        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int[] rgb = new int[3];
                for (int filtreX = 0; filtreX < filtre.length; filtreX++) {
                    for (int filtreY = 0; filtreY < filtre[filtreX].length; filtreY++) {
                        if(isPixelInImage(i, j, filtreX - filtre.length / 2, filtreY - filtre[filtreX].length / 2, image)){
                            int[] rgb2 = OutilCouleur.getTabColor(image.getRGB(i + filtreX - filtre.length / 2, j + filtreY - filtre[filtreX].length / 2));
                            rgb[0] += rgb2[0] * filtre[filtreX][filtreY] / coeff;
                            rgb[1] += rgb2[1] * filtre[filtreX][filtreY] / coeff;
                            rgb[2] += rgb2[2] * filtre[filtreX][filtreY] / coeff;
                        }
                    }
                }
                rgb[0] = Math.min(255, Math.max(0, rgb[0]));
                rgb[1] = Math.min(255, Math.max(0, rgb[1]));
                rgb[2] = Math.min(255, Math.max(0, rgb[2]));
                int nRGB = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
                newImage.setRGB(i, j, nRGB);
            }
        }


        Path path1 = Paths.get(path);
        String fileName = path1.getFileName().toString();
        String newPath = path1.getParent().toString() + "/Gaussien-new-" + fileName;
        ImageIO.write(newImage, "jpg", new File(newPath));
    }

    private boolean isPixelInImage(int x, int y, int filtreX, int filtreY, BufferedImage image){
        return x + filtreX >= 0 && x + filtreX < image.getWidth() && y + filtreY >= 0 && y + filtreY < image.getHeight();
    }
}
