package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.CommentDao;
import es.upm.miw.apaw_contest.documents.Comment;
import es.upm.miw.apaw_contest.dtos.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentBusinessController {

    private CommentDao commentDao;

    @Autowired
    public CommentBusinessController(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto create(CommentDto commentDto) {
        Comment comment = new Comment(commentDto.getPositive(), commentDto.getDescription());
        this.commentDao.save(comment);
        return new CommentDto(comment);
    }

    public List<CommentDto> readAll() {
        List<Comment> comments = this.commentDao.findAll();
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
