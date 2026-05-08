package dasturhub.uz.services.course;

import dasturhub.uz.dtos.course.CreateAndEditCourseDto;
import dasturhub.uz.entity.Course;
import dasturhub.uz.entity.Lesson;
import dasturhub.uz.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kurs topilmadi"));
    }

    @Override
    public void updateCourse(String id, CreateAndEditCourseDto courseDto) {
        Course course = getCourseById(id);

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setImageUrl(courseDto.getImageUrl());
        course.setPrice(courseDto.getPrice());

        courseRepository.save(course);
    }

    @Override
    public void addCourse(CreateAndEditCourseDto courseDto) {
        Course course = new Course();

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setImageUrl(courseDto.getImageUrl());
        course.setPrice(courseDto.getPrice());

        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getAllLessonsSorted(Course course) {
        return course.getSections().stream()
                .flatMap(section -> section.getLessons().stream())
                .toList();
    }

}
