package ru.ponkratov.hibernate;

import org.hibernate.Session;
import ru.ponkratov.hibernate.entity.Author;
import ru.ponkratov.hibernate.entity.Book;

import java.util.logging.Logger;

public class Start {
    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        for(Author author:new AuthorHelper().getAuthorList()){
            System.out.println("author = " + author.getName());
        }
        for(Book book:new BookHelper().getBookList()){
            System.out.println(book.getName());
        }


    }
}
