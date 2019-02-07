package org.andestech.learning.rfb19.g3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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


        try(FileWriter fw1 = new FileWriter("e:\\datas3\\file3.txt"))
        {
            fw1.write("Salute!!\r\nСалют!!");

        }
        catch (IOException ex){
            ex.printStackTrace();
        }


        try(FileReader fr1 = new FileReader("e:\\datas3\\file3.txt"))
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



    }
}
