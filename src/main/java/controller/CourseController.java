package controller;

import model.learning.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(path = "courses", method = RequestMethod.GET)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping(path = "courses/{id}", method = RequestMethod.GET)
    public Course getCourseByID(@PathVariable int id) {
        return courseService.getCourseByID(id);
    }

    @RequestMapping(path = "courses", method = RequestMethod.POST)
    public void createNewCourse(@RequestBody Course course) {
        courseService.createNewCourse(course);
    }

    @RequestMapping(path = "course", method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
    }

    @RequestMapping(path = "course/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
