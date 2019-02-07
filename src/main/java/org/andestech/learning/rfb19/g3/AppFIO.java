package org.andestech.learning.rfb19.g3;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.nio.file.*;
import java.nio.channels.Channels;


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
            raf.write("Дата2 TEST 1234\r\nHELLO!!".getBytes());
           int size = (int)raf.length(); // 1
           int size2 = (int)raf.getFilePointer();
           System.out.println("size2=" + size2 + ", size=" + size);

            byte[] arr = new byte[size];

            raf.seek(0);
            raf.write("data".getBytes());

            raf.seek(0);
            raf.read(arr);

            System.out.println( new String(arr,"utf8"));


        }
        catch (IOException ex){
            ex.printStackTrace();
        }


        Book book3 =
                new Book(1869,"GG-864876","Лев Толстой","Война и Мир");
        book3.printBookInfo();

        Book book4 =
                new Book(1889,"GT-823464876","Лев Толстой","Крейцерова соната");

        ArrayList<Book> books =
                new ArrayList<>();

        books.add(book3);
        books.add(book4);

        // --- Object stream

        File f7 = new File("e:\\datas3\\books.library");

        try(ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(f7))){

         oos.writeObject(books);

        }
        catch (IOException ex){
        ex.printStackTrace();}

        //--------------- Читатель -------------------------

        ArrayList<Book> books_from_disk =
                new ArrayList<>();

            try(ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(f7))){

                books_from_disk =   ( ArrayList<Book>)ois.readObject();
                System.out.println(books_from_disk);
            }
        catch (IOException | ClassNotFoundException ex){
                ex.printStackTrace();


         //-----------NIO-----------------
    }

        System.out.println("---------------- NIO paths ----------------");
    Path path = Paths.get("e:\\datas3\\file_raf.txt");
        try {
            System.out.println(new String(Files.readAllBytes(path)));

            Files.copy(path, Paths.get("e:\\datas3\\file_raf2.txt"),StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){ex.printStackTrace();}

        //----------------------------------- NIO write simple data -----------


        try(
        WritableByteChannel rbc =
                Channels.newChannel(new DataOutputStream(
                        new FileOutputStream("e:\\datas3\\test_channel.txt")));
        ){

            byte[] arr = "aSJDLKASDHLAKSDHLK\r\nYIYIUY".getBytes();

            ByteBuffer bb = ByteBuffer.allocate(arr.length);
            bb.put(arr);
            bb.flip();

            rbc.write(bb);
        }
        catch (IOException ex){ex.printStackTrace();}

    }
}
