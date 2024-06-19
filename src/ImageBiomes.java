import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageBiomes {

    private KMeans kMeans;
    private Palette palette;

    public ImageBiomes(KMeans kMeans, Palette palette) {
        this.kMeans = kMeans;
        this.palette = palette;
    }

    public void afficherBiomes(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        double[][] descriptions = new double[img.getWidth() * img.getHeight()][3];
        int index = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color color = new Color(img.getRGB(i, j));
                descriptions[index][0] = color.getRed();
                descriptions[index][1] = color.getGreen();
                descriptions[index][2] = color.getBlue();
                index++;
            }
        }

        int[] clusters = kMeans.cluster(descriptions);
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        index = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Biome biome = palette.biome.get(clusters[index]);
                newImg.setRGB(i, j, biome.couleur.getRGB());
                index++;
            }
        }

        if(path.endsWith(".jpg")){
            ImageIO.write(newImg, "jpg", new File(path.replace(".jpg", "-KMeans.jpg")));
        }else if(path.endsWith(".png")){
            ImageIO.write(newImg, "png", new File(path.replace(".png", "-KMeans.png")));
        }else if(path.endsWith(".jpeg")) {
            ImageIO.write(newImg, "jpeg", new File(path.replace(".jpeg", "-KMeans.jpeg")));
        }else {
            System.out.println("Format d'image non supporté");
        }    }
}