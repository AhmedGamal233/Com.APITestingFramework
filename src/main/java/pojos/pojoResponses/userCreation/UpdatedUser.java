package pojos.pojoResponses.userCreation;

import java.util.Date;

public class UpdatedUser {
    public String name;
    public String job;
    public String id;
    public Date updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date createdAt) {
        this.updatedAt = updatedAt;
    }


}
