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
}
