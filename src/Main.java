import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FlouMoyenne flouMoyenne = new FlouMoyenne();

        try {
            flouMoyenne.appliquerFiltreConvolution("images/img.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
