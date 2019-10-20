package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.CommentBusinessController;
import es.upm.miw.apaw_contest.dtos.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommentResource.COMMENTS)
public class CommentResource {

    public static final String COMMENTS = "/comments";
    static final String ID_ID = "/{id}";

    private CommentBusinessController commentBusinessController;

    @Autowired
    public CommentResource(CommentBusinessController commentBusinessController) {
        this.commentBusinessController = commentBusinessController;
    }

    @PostMapping
    public CommentDto create(@RequestBody CommentDto commentDto) {
        commentDto.validate();
        return this.commentBusinessController.create(commentDto);
    }

    @GetMapping
    public List<CommentDto> readAll() {
        return this.commentBusinessController.readAll();
    }

    @GetMapping(value = ID_ID)
    public CommentDto get(@PathVariable String id) {
        return this.commentBusinessController.getCommentById(id);
    }

    @DeleteMapping(value = ID_ID)
    public void delete(@PathVariable String id) {
        this.commentBusinessController.delete(id);
    }
}
