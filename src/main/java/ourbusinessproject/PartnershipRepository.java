package ourbusinessproject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Val on 03/11/2017.
 */
@Repository
public interface PartnershipRepository extends PagingAndSortingRepository<Partnership, Long>{

    public Page<Partnership> findByProjectTitle(String title, Pageable page);

    public Page<Partnership> findByEnterpriseName(String name, Pageable page);

    public Page<Partnership> findByEnterpriseNameAndProjectTitle(String name, String title, Pageable page);

}
