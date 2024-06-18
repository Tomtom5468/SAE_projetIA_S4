import java.io.IOException;

public class MainThomas {
    public static void main(String[] args) {
        try{
            FlouGaussien flouGaussien = new FlouGaussien(3);
            flouGaussien.appliquerFiltreConvolution("./images/Planete 1.jpg");
            flouGaussien.appliquerFiltreConvolution("./images/Planete 2.jpg");
            flouGaussien.appliquerFiltreConvolution("./images/Planete 3.jpg");
            flouGaussien.appliquerFiltreConvolution("./images/Planete 4.jpg");
            flouGaussien.appliquerFiltreConvolution("./images/Planete 5.jpeg");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
