package service;


import model.learning.Course;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CourseService {

    @Autowired
    SessionFactory sessionFactory;

    public List<Course> getAllCourses() {
        Query query =  sessionFactory.getCurrentSession().createQuery("from Course");
        return query.list();
    }

    public Course getCourseByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Course as c where c.id = :id");
        query.setInteger("id", id);
        return (Course) query.uniqueResult();
    }

    public void createNewCourse(Course courseReq) {
        sessionFactory.getCurrentSession().save(courseReq);
    }

    public void updateCourse(Course courseReq) {
        sessionFactory.getCurrentSession().update(courseReq);
    }

    public void deleteCourse(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Course as c where c.id = :id");
        query.setInteger("id", id);
        Course course = (Course) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(course);
    }

}
