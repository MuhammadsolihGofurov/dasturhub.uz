package dasturhub.uz.controller;

import dasturhub.uz.dtos.course.CreateAndEditCourseDto;
import dasturhub.uz.entity.Lesson;
import dasturhub.uz.services.course.CourseService;
import org.springframework.ui.Model;
import dasturhub.uz.entity.Course;
import dasturhub.uz.entity.Page;
import dasturhub.uz.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private PageService pageService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllCourses(Model model) {
        Page homePage = pageService.getPage(3L);
        List<Course> courses = courseService.getAllCourse();

        model.addAttribute("courses", courses);
        model.addAttribute("seo_title", homePage.getSeoTitle());
        model.addAttribute("seo_description", homePage.getSeoDescription());
        model.addAttribute("seo_keywords", homePage.getSeoKeywords());



        return "courses";
    }

    @GetMapping("/details/{courseId}")
    public String getCourseDetails(@PathVariable String courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);

        // Agar dars tanlanmagan bo'lsa, xato bermasligi uchun null dars obyektlarini yuboramiz
        model.addAttribute("currentLesson", null);
        model.addAttribute("prevLesson", null);
        model.addAttribute("nextLesson", null);

        return "courses/details";
    }

    // DARSLAR UCHUN YANGI METOD
    @GetMapping("/{courseId}/lessons/{lessonId}")
    public String getLessonDetails(@PathVariable String courseId,
                                   @PathVariable String lessonId,
                                   Model model) {
        Course course = courseService.getCourseById(courseId);

        // Barcha darslarni tartiblangan holda olamiz
        List<Lesson> allLessons = courseService.getAllLessonsSorted(course);

        // Hozirgi darsni topamiz
        Lesson currentLesson = allLessons.stream()
                .filter(l -> l.getId().equals(lessonId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dars topilmadi"));

        int currentIndex = allLessons.indexOf(currentLesson);

        // Oldingi va keyingi darslarni aniqlaymiz
        Lesson prevLesson = (currentIndex > 0) ? allLessons.get(currentIndex - 1) : null;
        Lesson nextLesson = (currentIndex < allLessons.size() - 1) ? allLessons.get(currentIndex + 1) : null;

        model.addAttribute("course", course);
        model.addAttribute("currentLesson", currentLesson);
        model.addAttribute("prevLesson", prevLesson);
        model.addAttribute("nextLesson", nextLesson);

        // SEO
        model.addAttribute("seo_title", currentLesson.getTitle());

        return "courses/details";
    }


    // === for admin ===
    @GetMapping("/manage")
    public String manageCourses(Model model) {
        List<Course> courses = courseService.getAllCourse();

        model.addAttribute("courses", courses);
        model.addAttribute("seo_title", "Manage Courses");
        model.addAttribute("seo_description", "Manage Courses");
        model.addAttribute("seo_keywords", "Manage Courses");

        return "/courses/manage";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("seo_title", "Create course");
        model.addAttribute("seo_description", "Create course");
        model.addAttribute("seo_keywords", "Create course");

        return "courses/create";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute CreateAndEditCourseDto courseDto) {
        courseService.addCourse(courseDto);

        return "redirect:/courses/manage";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);

        model.addAttribute("seo_title", "Edit Course");
        return "courses/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable String id, @ModelAttribute CreateAndEditCourseDto courseDto) {
        courseService.updateCourse(id, courseDto);
        return "redirect:/courses/manage";
    }

    @DeleteMapping("/api/manage/{id}")
    @ResponseBody
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }

}
