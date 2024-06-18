public class OutilCouleur {

    public static int[] getTabColor(int rgb){
        int[] tabColor = new int[3];
        tabColor[0] = (rgb & 0xFF0000) >> 16;
        tabColor[1] = (rgb & 0xFF00) >> 8;
        tabColor[2] = rgb & 0xFF;
        return tabColor;
    }
}
