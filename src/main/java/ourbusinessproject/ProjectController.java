package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Val on 25/10/2017.
 */
@RestController
public class ProjectController {

    private EnterpriseProjectService enterpriseProjectService;

    public ProjectController(EnterpriseProjectService enterpriseProjectService) {
        this.enterpriseProjectService = enterpriseProjectService;
    }

    @GetMapping("/projectsWithEnterprises")
    public List<Project> findAllProjectsWithEnterprises() {
        return enterpriseProjectService.findAllProjects();
    }
}
