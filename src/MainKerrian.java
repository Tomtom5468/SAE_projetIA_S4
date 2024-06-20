import java.io.IOException;

public class MainKerrian {

    public static void main(String[] args) {
        FlouMoyenne flouMoyenne = new FlouMoyenne();

        try {
            flouMoyenne.appliquerFiltreConvolution("images/Planete 1.jpg");
            flouMoyenne.appliquerFiltreConvolution("images/Planete 2.jpg");
            flouMoyenne.appliquerFiltreConvolution("images/Planete 3.jpg");
            flouMoyenne.appliquerFiltreConvolution("images/Planete 4.jpg");
            flouMoyenne.appliquerFiltreConvolution("images/Planete 5.jpeg");

            FlouGaussien flouGaussien = new FlouGaussien(7);
            flouGaussien.appliquerFiltreConvolution("./images/Planete 1.jpg");
            KMeans kMeans = new KMeans(10, 10);
            ImageBiomes imageBiomes = new ImageBiomes(kMeans, Palette.DEFAULT);
            imageBiomes.afficherBiomes("images/Gaussien-new-Planete 1.jpg");
            /**
            DBSCAN dbscan = new DBSCAN(0.01, 1);
            ImageEcosystemes imageEcosystemes = new ImageEcosystemes(dbscan, Palette.DEFAULT);
            imageEcosystemes.afficherEcosystemes("images/Gaussien-new-city_logo.jpeg");
             **/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
