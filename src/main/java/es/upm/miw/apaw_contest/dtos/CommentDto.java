package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.Comment;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;

import javax.validation.constraints.NotNull;

public class CommentDto {

    private String id;

    private Boolean positive;

    private String description;

    public CommentDto(Boolean positive, String description) {
        this.positive = positive;
        this.description = description;
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.positive = comment.getPositive();
        this.description = comment.getDescription();
    }

    public CommentDto() {
        //For Framework
    }

    public String getId() {
        return id;
    }

    @NotNull
    public Boolean getPositive() {
        return positive;
    }

    public String getDescription() {
        return description;
    }

    public void validate() {
        if (this.positive == null || this.description.isEmpty() || this.description == null) {
            throw new BadRequestException("Incomplete CommentDto");
        }
    }

    @Override
    public String toString() {
        return "CommentBasicDto{" +
                "id='" + id + '\'' +
                ", positive='" + positive + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
