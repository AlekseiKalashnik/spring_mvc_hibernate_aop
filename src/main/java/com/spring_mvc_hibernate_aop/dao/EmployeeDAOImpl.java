package com.spring_mvc_hibernate_aop.dao;

import com.spring_mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.lang.annotation.Retention;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;
    /*для того чтобы наш DAO мог подключаться к БД через hibernate,
    DAO должен иметь доступ к SessionFactory, через бин,
    который мы создавали в applicationContext.
    SessionFactory бин зависит от бина dataSource,
    поэтому мы внедряем зависимость для EmployeeDAOImpl от SessionFactory бина*/

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees = session.createQuery("from Employee",
                        Employee.class).getResultList();
        /*получаем всех работников из базы. Employee - имя класса
        */
        /*второй вариант использования через Query:
        Query<Employee> query = session.createQuery("from Employee",
                Employee.class);
        List<Employee> allEmployees = query.getResultList();
        */
        return allEmployees;
    }
}
