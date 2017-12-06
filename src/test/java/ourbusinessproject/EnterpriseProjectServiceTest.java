package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ourbusinessproject.Enterprise;
import ourbusinessproject.Project;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class EnterpriseProjectServiceTest {

    private EnterpriseProjectService enterpriseProjectService;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Project project;

    @MockBean
    private Enterprise enterprise;

    private Long anId = 1L;

    @Before
    public void setUp() throws Exception {
        enterpriseProjectService = new EnterpriseProjectService();
        enterpriseProjectService.setEntityManager(entityManager);
    }

    @Test
    public void testEntityManagerPersistAProjectWhenProjectIsSaved() {
        // when: trying to save a project
        enterpriseProjectService.save(project);

        // then: the persist method is invoke on the entity manager
        verify(enterpriseProjectService.getEntityManager()).persist(project);
    }

    @Test
    public void testEntityManagerPersistAnEnterpriseWhenEnterpriseIsSaved() {
        // when: trying to save an enterprise
        enterpriseProjectService.save(enterprise);

        // then: the persist method is invoke on the entity manager
        verify(entityManager).persist(enterprise);
    }

    @Test
    public void testEntityManagerFindAProjectWhenProjectIsSearchedById() {

        // when: trying to save the project
        enterpriseProjectService.findProjectById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Project.class, anId);
    }

    @Test
    public void testEntityManagerFindAnEnterpriseWhenEnterpriseIsSearchedById() {

        // when: trying to save the project
        enterpriseProjectService.findEnterpriseById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Enterprise.class, anId);
    }



}