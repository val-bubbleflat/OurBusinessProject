package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;


import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnterpriseProjectServiceIntegrationTest {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    @Autowired private Bootstrap bootstrap;

    private Project project;
    private Enterprise enterprise;

    @Before
    public void setUp() {

        // given a a valid Enterprise
        enterprise = new Enterprise();
        enterprise.setName("MyComp");
        enterprise.setDescription("My comp description");
        enterprise.setContactEmail("comp@com.com");
        enterprise.setContactName("comp contact name");

        // given a a valid project
        project = new Project();
        project.setTitle("A project");
        project.setDescription("Project description");
        project.setEnterprise(enterprise);

    }

    @Test
    public void testSaveValidProjectWithNewEnterprise() {

        // given a a valid project

        // when saving the project
        enterpriseProjectService.save(project);

        // expect the project is saved with a generated id
        assertThat(project.getId(), is(notNullValue()));

        // expect its enterprise is saved too
        assertThat(project.getEnterprise().getId(), is(notNullValue()));

        // expect the enterprise has the project referenced in its collection of projects
        assertThat(enterprise.getProjects(), hasItem(project));

    }

    @Test
    public void testSaveValidProjectWithAlreadySavedEnterprise() {

        // given a a valid project and an already saved enterprise
        enterpriseProjectService.save(enterprise);

        // when saving the project
        enterpriseProjectService.save(project);

        // expect the project is saved with a generated id
        assertThat(project.getId(), is(notNullValue()));

        // expect the enterprise has the project referenced in its collection of projects
        assertThat(enterprise.getProjects(), hasItem(project));

    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNonValidProject() {

        // given a non valid project
        project.setTitle("");

        // when saving the project
        enterpriseProjectService.save(project);

        // then an exception is thrown

    }

    @Test
    public void testSaveValidEnterprise() {

        // given a a valid Enterprise

        // when saving the enterprise
        enterpriseProjectService.save(enterprise);

        // expect the enterprise is saved with a generated id
        assertThat(enterprise.getId(), is(notNullValue()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNonValidEnterprise() {

        // given a non valid enterprise
        enterprise = new Enterprise();
        enterprise.setName(null);


        // when saving the enterprise
        enterpriseProjectService.save(enterprise);

        // then an exception is thrown

    }

    @Test
    public void testFindExistingProjectById() {

        // given a saved project
        enterpriseProjectService.save(project);


        // when an existing  project is searched by id
        Project fetchedProject = enterpriseProjectService.findProjectById(project.getId());

        // then the fetched project is correctly instanced
        assertThat(fetchedProject.getTitle(), is(project.getTitle()));
        assertThat(fetchedProject.getDescription(), is(project.getDescription()));

    }

    @Test
    public void testFindNonExistingProjectById() {

        // when a non existing  project is searched by id
        Project fetchedProject = enterpriseProjectService.findProjectById(10L);

        // then the fetched project is null
        assertThat(fetchedProject, is(nullValue()));

    }

    @Test
    public void testFindExistingEnterprise() {
        // given a saved Enterprise
        enterpriseProjectService.save(enterprise);

        // when an existing  enterprise is searched by id
        Enterprise fetchedEnterprise = enterpriseProjectService.findEnterpriseById(enterprise.getId());

        // the fetched enterprise is correctly instanced
        assertThat(fetchedEnterprise.getName(), is(enterprise.getName()));
        assertThat(fetchedEnterprise.getDescription(), is(enterprise.getDescription()));
        assertThat(fetchedEnterprise.getContactEmail(), is(enterprise.getContactEmail()));
        assertThat(fetchedEnterprise.getContactName(), is(enterprise.getContactName()));

    }

    @Test
    public void testFindNonExistingEnterpriseById() {

        // when a non existing  project is searched by id
        Enterprise fetchedEnterprise = enterpriseProjectService.findEnterpriseById(10L);

        // then the fetched project is null
        assertThat(fetchedEnterprise, is(nullValue()));

    }

    @Test
    public void testFindAllProjects() {
        // given an enterprise

        // and three persisted projects
        enterpriseProjectService.save(new Project("cp3", "p3 description", enterprise));
        enterpriseProjectService.save(new Project("bp2", "p2 description", enterprise));
        enterpriseProjectService.save(project);


        // when searching for all projects
        List<Project> projects = enterpriseProjectService.findAllProjects();

        // then the three projects are fetched
        assertThat(projects.size(), is(6)); // 3 + 3 from bootsrap

        // and projects are sorted by title
        assertThat(projects.get(0), is(project));
        assertThat(projects.get(1).getTitle(), is("bp2"));
        assertThat(projects.get(2).getTitle(), is("cp3"));

    }

    @Test
    public void testFindAllProjectsFromInitialization() {
        // given the initialization service of the bootstrap component
        InitializationService initializationService = bootstrap.getInitializationService();

        // when searching for all projects
        List<Project> projects = enterpriseProjectService.findAllProjects();

        // then three projects are fetched
        assertThat(projects.size(), is(3));

        // and projects are sorted by title
        assertThat(projects.get(0).getTitle(), is(initializationService.getProject1E1().getTitle()));
        assertThat(projects.get(1).getTitle(), is(initializationService.getProject1E2().getTitle()));
        assertThat(projects.get(2).getTitle(), is(initializationService.getProject2E1().getTitle()));

        // and projects have the good enterprise affected
        assertThat(projects.get(0).getEnterprise().getName(), is(initializationService.getEnterprise1().getName()));
        assertThat(projects.get(1).getEnterprise().getName(), is(initializationService.getEnterprise2().getName()));
        assertThat(projects.get(2).getEnterprise().getName(), is(initializationService.getEnterprise1().getName()));

    }


}