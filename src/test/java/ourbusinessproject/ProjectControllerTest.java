package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ProjectControllerTest {

    @MockBean
    private EnterpriseProjectService enterpriseProjectService;

    private ProjectController projectController;

    @Before
    public void setUp() {
        projectController = new ProjectController(enterpriseProjectService);
    }

    @Test
    public void testControllerDelegationToService() {
        // when requesting for all projects
        projectController.findAllProjectsWithEnterprises();

        // then the request is performed by the enterprise project service
        verify(enterpriseProjectService).findAllProjects();
    }

}