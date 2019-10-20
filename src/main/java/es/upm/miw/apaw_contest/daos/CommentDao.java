package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.documents.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment, String> {
}
