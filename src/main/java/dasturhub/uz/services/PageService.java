package dasturhub.uz.services;

import dasturhub.uz.entity.Page;
import dasturhub.uz.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public Page getPage(long id) {
        return pageRepository.findById(id).orElse(new Page());
    }

}
