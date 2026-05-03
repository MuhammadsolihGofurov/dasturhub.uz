package dasturhub.uz.controller;

import dasturhub.uz.entity.Page;
import dasturhub.uz.repository.PageRepository;
import dasturhub.uz.services.PageService;
import org.springframework.ui.Model;
import dasturhub.uz.entity.Banner;
import dasturhub.uz.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class MainController {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private PageService pageService;


    @GetMapping
    public String getMainIndexPage(Model model) {
        List<Banner> banners = bannerRepository.findAll();
        Page homePage = pageService.getPage(1L);

        model.addAttribute("banners", banners);
        model.addAttribute("seo_title", homePage.getSeoTitle());
        model.addAttribute("seo_description", homePage.getSeoDescription());
        model.addAttribute("seo_keywords", homePage.getSeoKeywords());

        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        Page homePage = pageService.getPage(2L);

        model.addAttribute("seo_title", homePage.getSeoTitle());
        model.addAttribute("seo_description", homePage.getSeoDescription());
        model.addAttribute("seo_keywords", homePage.getSeoKeywords());

        return "about";
    }


}
