package dasturhub.uz.controller;

import dasturhub.uz.dtos.course.CreateAndEditCourseDto;
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

    @GetMapping("/{id}")
    public String getCourseDetails(@PathVariable String id, Model model) {


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
