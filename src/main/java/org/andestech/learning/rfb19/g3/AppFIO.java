package org.andestech.learning.rfb19.g3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Date;


public class AppFIO {

    public static void main(String[] args)
    {

        File file = new File("e:\\datas3\\file2");


       // if(file.exists())
        try {
            boolean res = file.createNewFile();
            System.out.println("success? " + res);
        }
        catch (IOException ex){
            ex.printStackTrace();

        }

        file = new File("e:\\datas3\\file2");
        boolean res = file.delete();
        System.out.println("delete success? " + res);

        file = new File("e:\\datas3\\inner1\\inner2");
       // if(file.mkdirs()) {}
        file.mkdirs();


        // Simple File IO

        File file2 = new File("e:\\datas3\\file2.txt");


        FileWriter fw = null;
        try
        {
            fw = new FileWriter(file2);
            fw.write("Test string\r\nТестовая строка!!!");

            fw.close();
        }
        catch (IOException ex){
            ex.printStackTrace();}
        finally {
            if(fw != null)
                try{fw.close();}
                catch (IOException ex){ex.printStackTrace();}
        }

        String file3 = "e:\\datas3\\file3.txt";

        //try(FileWriter fw1 = new FileWriter(file3, true))
        try(FileWriter fw1 = new FileWriter(file3))
        {
            //fw1.write("\r\nSalute!!\r\nСалют!!");
            fw1.write("Salute!!\r\nСалют!!");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }


        try(FileReader fr1 = new FileReader(file3))
        {
            int ch;
            while( (ch = fr1.read()) != -1   )
            {
                System.out.print((char)ch);
            }
         }
        catch (IOException ex){
            ex.printStackTrace();
        }

    //------------------ FIS FOS
// писатель
        String file4 = "e:\\datas3\\file4.txt";
        try(FileOutputStream fos = new FileOutputStream(file4)){

            String data = "Salute!!\r\nСалют!!\r\n" + new Date();
            byte[] arr = data.getBytes("UTF-8");
            fos.write(arr);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

// читатель
        File f4 = new File(file4);
        String data = "";
        try(FileInputStream fos = new FileInputStream(file4)){

            // byte[] arr = new byte[fos.available()]; // works
            byte[] arr = new byte[(int)f4.length()];
            fos.read(arr);
            data = new String(arr,"utf8");

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        System.out.println(data + "\n\n" + Integer.MAX_VALUE);


     //-----------------------------------------------------------





    }
}
