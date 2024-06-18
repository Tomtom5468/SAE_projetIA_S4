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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
