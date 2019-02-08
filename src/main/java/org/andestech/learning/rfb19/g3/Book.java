package org.andestech.learning.rfb19.g3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Random;

@XmlRootElement(name = "Book")
public class Book implements Serializable {

    private static final int minYear=1400, maxYear;

    static {

        System.out.println("+++ static");
        ///

        maxYear= 2019;
    }


    private static int counter = 0;
    public static int getCounter(){return counter;}

    private int year;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlAttribute(name = "Название")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String isbn, author, title;

    private int invNumber;

    public int getYear(){return year;}
    public void setYear(int year){
        if(year >= minYear && year <= maxYear ) this.year = year;

    }
    // public void setYear(int year2){year = year2;}


    public Book(){
        // minYear = -100;
        counter++;
        System.out.println("++ ctor, o: " + this);
        //
        invNumber = new Random().nextInt(2500000);
    }

    public Book(int year, String isbn, String author, String title) {
        this();
        this.year = year;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    public int getInvNumber(){return invNumber;}

    private String getReportStr(){
        return
                "author: " + author +
                        ", title: " + title +
                        ", year:" + year +
                        ", isbn: " + isbn + ", invN: "+ invNumber +".";
    }

    public String toString(){return getReportStr();}



    public void printBookInfo(){
        System.out.println(getReportStr());
    }

}
