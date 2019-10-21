package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.documents.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfig
public class CommentDaoIT {

    @Autowired
    private CommentDao commentDao;

    @Test
    void testCreate() {
        Comment comment = new Comment(true, "Buena interpretación Juan!");
        this.commentDao.save(comment);
        Comment databaseComment = this.commentDao.findById(comment.getId()).orElseGet(Assertions::fail);
        assertEquals("Buena interpretación Juan!", databaseComment.getDescription());
    }

}
