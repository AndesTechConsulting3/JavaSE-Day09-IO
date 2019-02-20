package org.andestech.learning.rfb19.g3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
public class Library {



    public Library(){
        bookList = new ArrayList<>();
            }

    @XmlElement(name = "Book")
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    private List<Book> bookList;

    public void addBook(Book book){
        bookList.add(book);

    }

    public void rmBook(Book book)
    {
        bookList.remove(book);
    }



}
