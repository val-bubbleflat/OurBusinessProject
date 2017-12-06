package ourbusinessproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;


@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PartnershipControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitializationService initializationService;

    @Autowired
    private PartnershipService partnershipService;

    @Test public void testAddPartnership() {

        // when a request to add a partnership is triggered
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<String, Long>();
        map.add("project_id", initializationService.getProject1E1().getId());
        map.add("enterprise_id", initializationService.getEnterprise2().getId());
        Partnership partnership = restTemplate.postForObject("/api/v1/partnerships",map, Partnership.class);

        // then the response provide data on the added partnership
        assertThat(partnership.getId(), notNullValue());
        assertThat(partnership.getProject().getId(), is(initializationService.getProject1E1().getId()));
        assertThat(partnership.getEnterprise().getId(), is(initializationService.getEnterprise2().getId()));

    }

    @Test public void testRemovePartnership() {
        // when a partnership is requested to be removed
        restTemplate.delete("/api/v1/partnerships/"+initializationService.getPartnershipP1E2WithE1().getId());
        // then it is deleted from the database
        assertThat(partnershipService.findPartnershipById(initializationService.getPartnershipP1E2WithE1().getId()),nullValue());
    }

}