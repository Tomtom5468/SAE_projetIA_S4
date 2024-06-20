import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
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

        //on récupère les clusters dans un set avec leur index
        HashMap<Integer, Integer> clustersMap = new HashMap<>();
        for (int i = 0; i < clusters.length; i++) {
            clustersMap.put(clusters[i], i);
        }

        //on crée un set de biomes et on l'initialise a vide
        List<Color> biomes = new ArrayList<>(clustersMap.size());
        for (int i = 0; i < clustersMap.size(); i++) {
            biomes.add(new Color(0, 0, 0));
        }

        //on récupère les couleurs des clusters
        for (int i = 0; i < clustersMap.size(); i++) {
            int[] rgbtab;
            int rgb = img.getRGB(clustersMap.get(i)/img.getWidth(), clustersMap.get(i)%img.getWidth());
            rgbtab = OutilCouleur.getTabColor(rgb);
            biomes.set(i,palette.getPlusProche(new Color(rgbtab[0], rgbtab[1], rgbtab[2])));
        }


        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        index = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color biome = biomes.get(clusters[index]);
                newImg.setRGB(i, j, biome.getRGB());
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
        }

        // Création des images pour chaque biome
        for (int b = 0; b < biomes.size(); b++) {
            BufferedImage biomeImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            index = 0;
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    if (clusters[index] == b) {
                        biomeImg.setRGB(i, j, biomes.get(b).getRGB());
                    } else {
                        Color color = new Color(img.getRGB(i, j));
                        int[] rgbtab = OutilCouleur.getTabColor(color.getRGB());
                        rgbtab[0] = Math.round(rgbtab[0] + (float) 75 /100 * (255-rgbtab[0]));
                        rgbtab[1] = Math.round(rgbtab[1] + (float) 75 /100 * (255-rgbtab[1]));
                        rgbtab[2] = Math.round(rgbtab[2] + (float) 75 /100 * (255-rgbtab[2]));
                        biomeImg.setRGB(i, j, new Color(rgbtab[0], rgbtab[1], rgbtab[2]).getRGB());
                    }
                    index++;
                }
            }
            if(path.endsWith(".jpg")){
                ImageIO.write(biomeImg, "jpg", new File(path.replace(".jpg", "-Biome" + b + ".jpg")));
            }else if(path.endsWith(".png")){
                ImageIO.write(biomeImg, "png", new File(path.replace(".png", "-Biome" + b + ".png")));
            }else if(path.endsWith(".jpeg")) {
                ImageIO.write(biomeImg, "jpeg", new File(path.replace(".jpeg", "-Biome" + b + ".jpeg")));
            }else {
                System.out.println("Format d'image non supporté");
            }
        }

    }


}