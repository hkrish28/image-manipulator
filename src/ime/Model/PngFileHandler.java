//package ime.Model;
//
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//public class PngFileHandler implements FileHandler {
//
//  @Override
//  public void saveFile(Image image, String filename) {
//  }
//
//  @Override
//  public float[][][] loadFile(String filename) throws IOException {
//    BufferedImage bufferedImage = ImageIO.read(new File(filename));
//    int width = bufferedImage.getWidth();
//    System.out.println("Width of image: " + width);
//    int height = bufferedImage.getHeight();
//    System.out.println("Height of image: " + height);
//    float[][][] imagePixels = new float[height][width][];
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        int pixel = bufferedImage.getRGB(j, i);
//
//        float red = (float) ((pixel >> 16) & 0xFF);
//        float green = (float) ((pixel >> 8) & 0xFF);
//        float blue = (float) (pixel & 0xFF);
//
//        imagePixels[i][j] = new float[]{red, green, blue};
//      }
//    }
//    return imagePixels;
//  }
//
//  }
