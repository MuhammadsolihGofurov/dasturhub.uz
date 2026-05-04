package dasturhub.uz.services.course;

import dasturhub.uz.dtos.course.CreateAndEditCourseDto;
import dasturhub.uz.entity.Course;

import java.util.List;

public interface ICourseService {

    public List<Course> getAllCourse();

    public Course getCourseById(String id);

    public void updateCourse(String id, CreateAndEditCourseDto courseDto);

    public void  addCourse(CreateAndEditCourseDto courseDto);

    public void deleteCourse(String id);

}
