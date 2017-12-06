package ourbusinessproject;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

/**
 * Created by Val on 20/10/2017.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    @ManyToOne
    @NotNull
    private Enterprise enterprise;

    public Project() {}

    public Project(String title, String description, Enterprise enterprise) {
        this.title = title;
        this.description = description;
        this.enterprise = enterprise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
        if(enterprise != null){
            if(enterprise.getProjects() == null){
                enterprise.setProjects(new HashSet<>());
            }
            this.enterprise.getProjects().add(this);
        }
    }

}
