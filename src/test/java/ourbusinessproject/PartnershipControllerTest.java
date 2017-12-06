package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class PartnershipControllerTest {

    private PartnershipController partnershipController;

    @MockBean
    private EnterpriseProjectService enterpriseProjectService;

    @MockBean
    private PartnershipService partnershipService;

    @Before
    public void setUp() throws Exception {
        partnershipController = new PartnershipController(enterpriseProjectService, partnershipService);
    }

    @Test
    public void testAddPartnership() {
        // when add request is triggered
        partnershipController.addPartnership(1L,2L);

        // then several collaborations are triggered
        verify(enterpriseProjectService).findProjectById(1L);
        verify(enterpriseProjectService).findEnterpriseById(2L);
        verify(partnershipService).save(any(Partnership.class));
    }

    @Test
    public void testRemovePartnership() {
        // when add request is triggered
        partnershipController.removePartnership(1L);

        // then several collaborations are triggered
        verify(partnershipService).findPartnershipById(1L);
        verify(partnershipService).remove(any(Partnership.class));
    }



}