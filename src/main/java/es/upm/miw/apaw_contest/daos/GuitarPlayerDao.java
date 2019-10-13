package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;

public  interface GuitarPlayerDao extends MongoRepository<GuitarPlayer, String> {
}
