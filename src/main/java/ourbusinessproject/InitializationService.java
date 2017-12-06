package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Val on 25/10/2017.
 */
@Service
@Transactional
public class InitializationService {

    private Project project1E1;
    private Project project1E2;
    private Project project2E1;

    private Enterprise enterprise1;
    private Enterprise enterprise2;

    private Partnership partnershipP1E1WithE2;
    private Partnership partnershipP1E2WithE1;
    private Partnership partnershipP2E1WithE2;

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    @Autowired
    private PartnershipService partnershipService;

    public void initProjects() {
        enterprise1 = new Enterprise();
        enterprise1.setName("e1");
        enterprise1.setDescription("Description 1");
        enterprise1.setContactName("contact1");
        enterprise1.setContactEmail("contact@entreprise1.fr");
        enterprise2 = new Enterprise();
        enterprise2.setName("e2");
        enterprise2.setDescription("Description 2");
        enterprise2.setContactName("contact2");
        enterprise2.setContactEmail("contact@entreprise2.fr");
        project1E1 = new Project("p1", "description 1", enterprise1);
        project1E2 = new Project("p2", "description 2", enterprise2);
        project2E1 = new Project("p3", "description 12", enterprise1);
        enterpriseProjectService.save(project1E1);
        enterpriseProjectService.save(project1E2);
        enterpriseProjectService.save(project2E1);
    }

    public Project getProject1E1() {
        return project1E1;
    }

    public Project getProject1E2() {
        return project1E2;
    }

    public Project getProject2E1() {
        return project2E1;
    }

    public Enterprise getEnterprise1() {
        return enterprise1;
    }

    public Enterprise getEnterprise2() {
        return enterprise2;
    }

    public void initPartnerships() {
        partnershipP1E1WithE2 = new Partnership();
        partnershipP1E1WithE2.setProject(project1E1);
        partnershipP1E1WithE2.setEnterprise(enterprise2);
        partnershipP1E2WithE1 = new Partnership();
        partnershipP1E2WithE1.setProject(project1E2);
        partnershipP1E2WithE1.setEnterprise(enterprise1);
        partnershipP2E1WithE2 = new Partnership();
        partnershipP2E1WithE2.setProject(project2E1);
        partnershipP2E1WithE2.setEnterprise(enterprise2);
        partnershipService.save(partnershipP1E1WithE2);
        partnershipService.save(partnershipP1E2WithE1);
        partnershipService.save(partnershipP2E1WithE2);
    }

    public Partnership getPartnershipP1E1WithE2() {
        return partnershipP1E1WithE2;
    }

    public Partnership getPartnershipP1E2WithE1() {
        return partnershipP1E2WithE1;
    }

    public Partnership getPartnershipP2E1WithE2() {
        return partnershipP2E1WithE2;
    }
}
