package model.learning;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String courseCode;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseDetail> courseDetails = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LectureDetail> lectureDetails = new ArrayList<>();


    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseDetail> getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(List<CourseDetail> courseDetails) {
        this.courseDetails = courseDetails;
    }

    public List<LectureDetail> getLectureDetails() {
        return lectureDetails;
    }

    public void setLectureDetails(List<LectureDetail> lectureDetails) {
        this.lectureDetails = lectureDetails;
    }
}
