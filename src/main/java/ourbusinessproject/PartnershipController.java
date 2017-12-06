package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Val on 03/11/2017.
 */
@RestController
public class PartnershipController {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    @Autowired
    private PartnershipService partnershipService;

    @Autowired
    private PartnershipRepository partnershipRepository;

    public PartnershipController(EnterpriseProjectService enterpriseProjectService, PartnershipService partnershipService) {
        this.enterpriseProjectService = enterpriseProjectService;
        this.partnershipService = partnershipService;
    }

    @PostMapping("/api/v1/partnerships")
    public Partnership addPartnership(@RequestParam Long project_id, @RequestParam Long enterprise_id) {
        Partnership partnership = new Partnership();
        partnership.setEnterprise(enterpriseProjectService.findEnterpriseById(enterprise_id));
        partnership.setProject(enterpriseProjectService.findProjectById(project_id));
        return partnershipService.save(partnership);
    }

    @DeleteMapping("/api/v1/partnerships/{id}")
    public void removePartnership(@PathVariable long id) {
        Partnership partnership = partnershipService.findPartnershipById(id);
        partnershipService.remove(partnership);
    }

    @PostMapping("/api/search/partnerships")
    public Page<Partnership> searchPartnerships(@RequestParam String project_title, @RequestParam String enterprise_name, Pageable page){
        if(!project_title.isEmpty() && !enterprise_name.isEmpty()){
            return partnershipRepository.findByEnterpriseNameAndProjectTitle(enterprise_name, project_title, page);
        }else if(!project_title.isEmpty()){
            return partnershipRepository.findByProjectTitle(project_title, page);
        }else if(!enterprise_name.isEmpty()){
            return partnershipRepository.findByEnterpriseName(enterprise_name, page);
        }
        return partnershipRepository.findAll(page);
    }
}
