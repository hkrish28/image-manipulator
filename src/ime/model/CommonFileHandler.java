//package ime.model;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
///**
// * This file handler supports loading and saving of RGB files in JPG and PNG formats.
// */
//public class CommonFileHandler implements FileHandler {
//
//  @Override
//  public void saveImage(Image image, String filename) throws IOException {
//    int width = image.getWidth();
//    int height = image.getHeight();
//    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//    for (int x = 0; x < width; x++) {
//      for (int y = 0; y < height; y++) {
//        float[] pixelValues = image.getPixelValues(y, x);
//        float red = pixelValues[0];
//        float green = pixelValues[1];
//        float blue = pixelValues[2];
//
//        // Convert the floating-point values back to RGB integers
//        int rgb = (int) red << 16 | (int) green << 8 | (int) blue;
//
//        // Set the pixel in the BufferedImage
//        bufferedImage.setRGB(x, y, rgb);
//      }
//    }
//    ImageIO.write(bufferedImage, filename.split("\\.")[1], new File(filename));
//  }
//
//  @Override
//  public float[][][] loadImage(String filename) throws IOException {
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
//}
