package dasturhub.uz.controller;

import dasturhub.uz.dtos.course.CreateAndEditCourseDto;
import dasturhub.uz.dtos.section.SectionCreateAndEditDto;
import dasturhub.uz.services.section.ISectionService;
import dasturhub.uz.services.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import dasturhub.uz.entity.Section;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private ISectionService sectionService;

    // === for admin ===
    @GetMapping("/manage/{courseId}")
    public String manage(@PathVariable String courseId, Model model){
        List<Section> sections = sectionService.getSectionByCourseIdAndOrderIncrease(courseId);

        model.addAttribute("courseId", courseId);
        model.addAttribute("sections", sections);
        model.addAttribute("seo_title", "Bo'limlarni boshqarish");

        return "sections/manage";
    }


    @GetMapping("/create/{courseId}")
    public String showCreateForm(@PathVariable String courseId, Model model) {
        model.addAttribute("seo_title", "Create section");
        model.addAttribute("seo_description", "Create section");
        model.addAttribute("seo_keywords", "Create section");
        model.addAttribute("courseId", courseId);

        return "/sections/create";
    }

    @PostMapping("/save")
    public String saveSection(@ModelAttribute SectionCreateAndEditDto sectionDto) throws Exception {
        //  keyinchalik kursni databasedan qidirib qo'yishim kerak
        sectionService.addSection(sectionDto);


        return "redirect:/sections/manage/" + sectionDto.getCourseId();
    }

    @GetMapping("/edit/{id}")
    public String editSection(@PathVariable String id, Model model) {
        Section section = sectionService.getSectionById(id);

        model.addAttribute("section", section);
        model.addAttribute("courseId", section.getCourse().getId());
        model.addAttribute("seo_title", "Edit Section");

        return "sections/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable String id, @ModelAttribute SectionCreateAndEditDto sectionDto) {
        sectionService.updateSection(id, sectionDto);
        return "redirect:/sections/manage/" + sectionDto.getCourseId();
    }

    @DeleteMapping("/api/manage/{id}")
    @ResponseBody
    public void deleteSection(@PathVariable String id) {
        sectionService.deleteSection(id);
    }
}
