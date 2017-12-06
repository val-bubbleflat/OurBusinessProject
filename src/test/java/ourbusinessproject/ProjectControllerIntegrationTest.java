package ourbusinessproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitializationService initializationService;

    @Test
    public void testFindAllProjectsWithEnterprises() {

        // when requesting all projects
        String body = this.restTemplate.getForObject("/projectsWithEnterprises", String.class);

        // then the results provide three projects with their enterprise
        assertThat(body, containsString(initializationService.getProject1E1().getTitle()));
        assertThat(body, containsString(initializationService.getProject1E2().getTitle()));
        assertThat(body, containsString(initializationService.getProject2E1().getTitle()));
        assertThat(body, containsString(initializationService.getEnterprise1().getName()));
        assertThat(body, containsString(initializationService.getEnterprise2().getName()));


    }

}