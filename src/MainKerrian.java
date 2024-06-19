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

            /**
            KMeans kMeans = new KMeans(5, 100);
            ImageBiomes imageBiomes = new ImageBiomes(kMeans, Palette.DEFAULT);
            imageBiomes.afficherBiomes("images/Planete 1.jpg");
            **/
            DBSCAN dbscan = new DBSCAN(5, 10);
            ImageEcosystemes imageEcosystemes = new ImageEcosystemes(dbscan, Palette.DEFAULT);
            imageEcosystemes.afficherEcosystemes("images/Planete 1.jpg");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
