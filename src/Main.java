//  Программа получает на вход путь к изобрадению .png с горизонтальными линиями
//  и выводит кол-во линий, их длину и общую длину всех линий

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static boolean isPixelsEqual(int[] a, int[] b){
    return (a[0]==b[0]&&a[1]==b[1]&&a[2]==b[2]&&a[3]==b[3]);
}
    public  static  void  main(String args[]) throws IOException {


        String path;

        System.out.println(" Введите путь к файлу с картинкой:");
        Scanner in = new Scanner(System.in);
        path = in.nextLine();

        File file = new File(path);
        if(!file.exists()){
            System.out.println("файла не существует");
            System.exit(0);
        }

        BufferedImage image = ImageIO.read(file);

//        ImageReader reader = null;
//        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("png");
//        if(iterator.hasNext()){reader = iterator.next();}
//
//        InputStream inputStream = new FileInputStream(new File(path));
//        ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
//        ImageInputStream imageInputStream1 = ImageIO.createImageInputStream(new File(path));
//
//        reader.setInput(imageInputStream,true);
//        BufferedImage image1 = reader.read(reader.getNumImages(true));

        WritableRaster writableRaster = image.getRaster();

        int n = 0;
        int l = 0;
        int L = 0;
        boolean inLine = false;

        int[] color = new int[4];
        color[0]= 255;
        color[1]= 255;
        color[2]= 255;
        color[3]= 0;

        int[] white = new int[4];
        white[0] = color[0];
        white[1] = color[1];
        white[2] = color[2];
        white[3] = color[3];


        for(int i=0; i<writableRaster.getHeight(); i++){
            for(int j=0; j<writableRaster.getWidth(); j++){
                int[] pixel = writableRaster.getPixel(j, i, new int[4]);
                if(inLine){
                    if(isPixelsEqual(pixel,color)){
                        l+=1;
                    }
                    else{
                        for(int k=0;k<4;k++){
                            color[k]=pixel[k];
                        }
                        n+=1;
                        L+=l;
                        System.out.println("длина "+n+"-ой линии = "+l);
                        l=0;
                        inLine=false;
                        if(!isPixelsEqual(pixel,white)){
                            for(int k=0;k<4;k++){
                                color[k]=pixel[k];
                            }
                            inLine=true;
                            l+=1;
                        }
                    }

                }
                else{
                    if(!isPixelsEqual(pixel,white)){
                        for(int k=0;k<4;k++){
                            color[k]=pixel[k];
                        }
                        inLine=true;
                        l+=1;
                    }
                }
            }
        }
        if (inLine){
            n+=1;
            L+=l;
            System.out.println("длина "+n+"-ой линии = "+l);
        }
        System.out.println("число линий = "+n);
        System.out.println("общая длина всех линий = "+L);
    }

}
