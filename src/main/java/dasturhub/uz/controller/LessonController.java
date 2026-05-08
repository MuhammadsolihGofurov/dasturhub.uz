package dasturhub.uz.controller;

import dasturhub.uz.dtos.lesson.CreateAndEditLessonDto;
import dasturhub.uz.entity.Section;
import dasturhub.uz.repository.LessonRepository;
import dasturhub.uz.services.lesson.ILessonService;
import dasturhub.uz.services.lesson.LessonService;
import dasturhub.uz.services.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import dasturhub.uz.entity.Lesson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private ILessonService lessonService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private LessonRepository lessonRepository;


    @GetMapping("/{id}")
    public String getLesson(@PathVariable String id, Model model) {
        // Dars mazmuni va uning bloklarini ko'rsatish
        return "lessons/view";
    }



    // === for admin ===
    @GetMapping("/manage/{sectionId}")
    public String manageLesson(@PathVariable String sectionId, Model model) {
        List<Lesson> lessons = lessonService.findAllBySectionId(sectionId);
        Section section = sectionService.getSectionById(sectionId);

        model.addAttribute("courseId", section.getCourse().getId());
        model.addAttribute("sectionId", section.getId());
        model.addAttribute("lessons", lessons);

        model.addAttribute("seo_title", "Darslarni boshqarish");

        return "/lessons/manage";
    }

    @GetMapping("/edit/{lessonId}")
    public String editLesson(@PathVariable String lessonId, Model model) {
        Lesson lesson = lessonService.getLessonById(lessonId);

        model.addAttribute("lesson", lesson);
        model.addAttribute("sectionId", lesson.getSection().getId());

        return "/lessons/edit";
    }

    @PostMapping("/update/{lessonId}")
    public String updateLesson(@PathVariable String lessonId, CreateAndEditLessonDto lessonDto) {
        String sectionId = lessonDto.getSectionId();

        lessonService.updateLesson(lessonId, lessonDto);

        return "redirect:/lessons/manage/" + sectionId;
    }

    @GetMapping("/create/{sectionId}")
    public String createLesson(@PathVariable String sectionId, Model model) {

        model.addAttribute("sectionId", sectionId);
        model.addAttribute("seo_title", "Darslarni boshqarish");

        return "lessons/create";
    }

    @PostMapping("/save")
    public String saveLesson(@ModelAttribute CreateAndEditLessonDto lessonDto) {
        lessonService.saveLessson(lessonDto.getSectionId(), lessonDto);

        return "redirect:/lessons/manage/" + lessonDto.getSectionId();
    }

    @DeleteMapping("/api/manage/{lessonId}")
    @ResponseBody
    public void deleteLesson(@PathVariable String lessonId) {
        lessonService.deleteLesson(lessonId);
    }
}
