package ru.ponkratov.hibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ponkratov.hibernate.entity.Author;

import java.util.List;

public class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        //открыть сессию для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        session.get(Author.class, 1L);//получение объекта по id


        //этап подготовки запроса
        //объект-конструктор запроса для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();//не использовать sesion.createCriteria, т.к. deprecared
        CriteriaQuery cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);//первостепенный, корневой entity (в sql запросе from)
        cq.select(root);//необязательный оператор, если просто нужно получить все значения

        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Author> authorList = query.getResultList();
        session.close();
        return authorList;
            }
    public Author getAuthor(String name){return null;}
}
