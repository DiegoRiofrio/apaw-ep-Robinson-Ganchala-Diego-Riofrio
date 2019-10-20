package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.documents.GuitarContest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuitarContestDao extends MongoRepository <GuitarContest, String> {
}
