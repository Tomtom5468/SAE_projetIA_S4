import java.io.IOException;

public class MainThomas {
    public static void main(String[] args) {
        try{
            FlouGaussien flouGaussien = new FlouGaussien(3);
            flouGaussien.appliquerFiltreConvolution("./images/Planete 4.jpg");
            KMeans kMeans = new KMeans(10,5);
            kMeans.convertirImage("./images/Gaussien-new-Planete 4.jpg");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
