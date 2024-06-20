import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ImageEcosystemes {

    private DBSCAN dbscan;
    private Palette palette;

    public ImageEcosystemes(DBSCAN dbscan, Palette palette) {
        this.dbscan = dbscan;
        this.palette = palette;
    }

    public void afficherEcosystemes(String path) throws IOException {
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

        KMeans kmeans = new KMeans(10,10);
        int[] clusters = kmeans.cluster(descriptions);

        //on fait un set pour avoir le nombre de clusters différents
        Set<Integer> clustersSet = new HashSet<>();
        for (int cluster : clusters) {
            clustersSet.add(cluster);
        }

        //pour tous les clusters trouvés
        for (int cluster : clustersSet) {
            BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            //on récupère les données de chaque pixel du cluster courant
            List<Integer> clusterCur = new ArrayList<>();
            for (int i = 0; i < clusters.length; i++) {
                if(clusters[i] == cluster){
                    clusterCur.add(i);
                }
            }
            //on refait un tableau de description pour le cluster courant
            double[][] clusterData = new double[clusterCur.size()][3];
            for (int i = 0; i < clusterCur.size(); i++) {
                clusterData[i] = descriptions[clusterCur.get(i)];
            }

            //on récupère les sous clusters du cluster courant
            int[] clusterColors = dbscan.cluster(clusterData);
            HashMap<Integer, Integer> clustersMap = new HashMap<>();
            for (int i = 0; i < clusterColors.length; i++) {
                clustersMap.put(clusterColors[i], clusterCur.get(i));
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

            int index2 = 0;
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    if(index2 < clusterCur.size()) {
                        if ((clusterCur.get(index2) / img.getWidth() == i) && (clusterCur.get(index2) % img.getWidth() == j)) {
                            Color biome = biomes.get(clusterColors[index2]);
                            newImg.setRGB(i, j, biome.getRGB());
                            index2++;
                        } else {
                            Color color = new Color(img.getRGB(i, j));
                            int[] rgbtab = OutilCouleur.getTabColor(color.getRGB());
                            rgbtab[0] = Math.round(rgbtab[0] + (float) 75 /100 * (255-rgbtab[0]));
                            rgbtab[1] = Math.round(rgbtab[1] + (float) 75 /100 * (255-rgbtab[1]));
                            rgbtab[2] = Math.round(rgbtab[2] + (float) 75 /100 * (255-rgbtab[2]));
                            newImg.setRGB(i, j, new Color(rgbtab[0], rgbtab[1], rgbtab[2]).getRGB());
                        }
                    }else {
                        Color color = new Color(img.getRGB(i, j));
                        int[] rgbtab = OutilCouleur.getTabColor(color.getRGB());
                        rgbtab[0] = Math.round(rgbtab[0] + (float) 75 /100 * (255-rgbtab[0]));
                        rgbtab[1] = Math.round(rgbtab[1] + (float) 75 /100 * (255-rgbtab[1]));
                        rgbtab[2] = Math.round(rgbtab[2] + (float) 75 /100 * (255-rgbtab[2]));
                        newImg.setRGB(i, j, new Color(rgbtab[0], rgbtab[1], rgbtab[2]).getRGB());
                    }
                }
            }

            //on enregistre l'image
            if (path.endsWith(".jpg")) {
                ImageIO.write(newImg, "jpg", new File(path.replace(".jpg", cluster + "-DBSCAN.jpg")));
            } else if (path.endsWith(".png")) {
                ImageIO.write(newImg, "png", new File(path.replace(".png", cluster + "-DBSCAN.png")));
            } else if (path.endsWith(".jpeg")) {
                ImageIO.write(newImg, "jpeg", new File(path.replace(".jpeg", cluster + "-DBSCAN.jpeg")));
            } else {
                System.out.println("Format d'image non supporté");
            }
        }

    }
}