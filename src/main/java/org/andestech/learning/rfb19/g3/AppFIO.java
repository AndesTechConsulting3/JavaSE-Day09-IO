package org.andestech.learning.rfb19.g3;

import java.io.*;
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

        File f5 = new File("e:\\datas3\\file5.bin");
        try(DataOutputStream dos =
                new DataOutputStream(new FileOutputStream(f5)))
        {
            int i = 87687632; // 4 bytes
            long L = 1000_333_444_555_666_777L; // 8 bytes

            dos.writeInt(i);
            dos.writeLong(L);


        }
 catch (IOException ex){
        ex.printStackTrace();
    }

       // читатель

        try(DataInputStream dis =
                    new DataInputStream(new FileInputStream(f5)))
        {
            int i = dis.readInt(); // 4 bytes
            long L = dis.readLong(); // 8 bytes



            System.out.println("i=" + i + ", L=" + L);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

//----------------------------------------------
        // RAF
        System.out.println("-------------------- RAF ------------------");

        File f6 = new File("e:\\datas3\\file_raf.txt");

        try(RandomAccessFile raf = new RandomAccessFile(f6,"rw"))
        {
            raf.write("DATA TEST 1234\r\nHELLO!!".getBytes());
           int size = (int)raf.length(); // 1
           int size2 = (int)raf.getFilePointer();
            System.out.println("size2=" + size2 + ", size=" + size);


        }
        catch (IOException ex){
            ex.printStackTrace();
        }


    }
}
