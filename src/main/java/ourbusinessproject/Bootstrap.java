package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Val on 25/10/2017.
 */
@Component
public class Bootstrap {

    @Autowired
    private InitializationService initializationService;

    public Bootstrap(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    @PostConstruct
    public void init() {
        try{
            initializationService.initProjects();
            initializationService.initPartnerships();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public InitializationService getInitializationService() {
        return initializationService;
    }
}
