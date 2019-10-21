package es.upm.miw.apaw_contest.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
    @Id
    private String id;

    private Boolean positive;

    private String description;

    public Comment(Boolean positive, String description) {
        this.positive = positive;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Boolean getPositive() {
        return positive;
    }

    public String getDescription() {
        return description;
    }
}
