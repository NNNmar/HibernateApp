package ru.ponkratov.hibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ponkratov.hibernate.entity.Book;

import java.util.List;

public class BookHelper {
    private SessionFactory sessionFactory;

    public BookHelper(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList(){
        //открыть сессию для манипуляции с персист. объектами
        Session session =sessionFactory.openSession();
        //этап подготовки запроса

        //объект-конструктор запроса для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();//не использовать sesion.createCriteria, т.к. deprecared
        CriteriaQuery cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);//первостепенный, корневой entity (в sql запросе from)
        cq.select(root);//необязательный оператор, если просто нужно получить все значения

        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Book> bookList = query.getResultList();
        session.close();
        return bookList;
    }


}
