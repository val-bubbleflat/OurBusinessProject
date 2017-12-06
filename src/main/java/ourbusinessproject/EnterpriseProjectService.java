package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by Val on 20/10/2017.
 */
@Service
@Transactional
public class EnterpriseProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Project project) {
        if(project.getEnterprise() != null){
            entityManager.persist(project.getEnterprise());
        }
        entityManager.persist(project);
    }

    public void save(Enterprise enterprise) {
        entityManager.persist(enterprise);
    }

    public Project findProjectById(Long id) {
        return entityManager.find(Project.class, id);
    }

    public Enterprise findEnterpriseById(Long id) {
        return entityManager.find(Enterprise.class, id);
    }

    public List<Project> findAllProjects() {
        return entityManager.createQuery("Select p from Project p join fetch p.enterprise order by p.title", Project.class).getResultList();
    }
}
