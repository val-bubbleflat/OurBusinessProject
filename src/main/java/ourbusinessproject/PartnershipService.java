package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by Val on 03/11/2017.
 */
@Service
@Transactional
public class PartnershipService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    public Partnership save(Partnership partnership) {
        if(partnership.getEnterprise() != null){
            entityManager.merge(partnership.getEnterprise());
        }
        if(partnership.getProject() != null){
            entityManager.merge(partnership.getProject());
        }
        entityManager.persist(partnership);
        return partnership;
    }


    public Partnership findPartnershipById(Long id) {
        return entityManager.find(Partnership.class, id);
    }

    public void remove(Partnership partnership) {
        entityManager.remove(entityManager.merge(partnership));
    }
}
