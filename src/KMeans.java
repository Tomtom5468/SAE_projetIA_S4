import java.awt.*;
import java.util.Random;
public class KMeans implements AlgoClustering{

    private int nbClusters;
    private int nbIterations;

    public KMeans(int nbClusters, int nbIterations){
        this.nbClusters = nbClusters;
        this.nbIterations = nbIterations;
    }

    @Override
    public int[] cluster(double[][] descriptions) {
        int[] clusters = new int[descriptions.length];
        double[][] centroids = new double[nbClusters][descriptions[0].length];

        // Initialisation des centroïdes
        Random random = new Random();
        for(int i = 0; i < nbClusters; i++){
            int index = random.nextInt(descriptions.length);
            centroids[i] = descriptions[index];
        }

        // Itérations
        for(int i=0; i<nbIterations; i++){
            // Assignation des points aux clusters
            for(int j=0; j<descriptions.length; j++) {
                clusters[j] = plusProcheCentroide(descriptions[j], centroids);
            }
            centroids = recalculCentroides(clusters, descriptions, nbClusters);
        }

        return clusters;
    }

    private double[][] recalculCentroides(int[] clusters, double[][] descriptions, int nbClusters) {
        double[][] nouveauxCentroides = new double[nbClusters][descriptions[0].length];
        int[] nbPoints = new int[nbClusters];

        for(int i=0; i<descriptions.length; i++){
            int cluster = clusters[i];
            for(int j=0; j<descriptions[i].length; j++){
                nouveauxCentroides[cluster][j] += descriptions[i][j];
            }
            nbPoints[cluster]++;
        }

        for(int i=0; i<nbClusters; i++){
            for(int j=0; j<nouveauxCentroides[i].length; j++){
                nouveauxCentroides[i][j] /= nbPoints[i];
            }
        }
        return nouveauxCentroides;
    }

    private int plusProcheCentroide(double[] description, double[][] centroids) {
        int i = 0;
        double minDistance = Double.MAX_VALUE;
        for(int j=0; j<centroids.length; j++){
            NormeCIELAB NormeCIELAB = new NormeCIELAB();
            Color  c1 = new Color((int)description[0], (int)description[1], (int)description[2]);
            Color  c2 = new Color((int)centroids[j][0], (int)centroids[j][1], (int)centroids[j][2]);

            double distance = NormeCIELAB.distanceCouleur(c1, c2);
            if(distance < minDistance){
                minDistance = distance;
                i = j;
            }
        }
        return i;
    }
}
