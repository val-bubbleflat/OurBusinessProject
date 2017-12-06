package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PartnershipTest {

    private Validator validator;

    @MockBean
    private Project project;

    @MockBean
    private Enterprise enterprise;

    private Partnership partnership;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        partnership = new Partnership(1L);
        partnership.setCreationDate(new Date());
        partnership.setEnterprise(enterprise);
        partnership.setProject(project);
    }

    @Test
    public void testValidPartnershipValidation() {

        // given a valid partnership
        // expect validating the partnership instance returns no constraints violation
        assertThat(validator.validate(partnership).isEmpty(), is(true));

    }

    @Test
    public void testPartnerShipWithNoDateSetIsInvalid() {
        // given a partnership without creation date set
        partnership.setCreationDate(null);

        // expect validating the partnership instance returns  constraint violation
        assertThat(validator.validate(partnership).isEmpty(), is(false));
    }

    @Test
    public void testPartnerShipWithNoEnterpriseIsInvalid() {
        // given a partnership without enterprise
        partnership.setEnterprise(null);

        // expect validating the partnership instance returns  constraint violation
        assertThat(validator.validate(partnership).isEmpty(), is(false));
    }

    @Test
    public void testPartnerShipWithNoProjectIsInvalid() {
        // given a partnership without project
        partnership.setProject(null);

        // expect validating the partnership instance returns  constraint violation
        assertThat(validator.validate(partnership).isEmpty(), is(false));
    }

}