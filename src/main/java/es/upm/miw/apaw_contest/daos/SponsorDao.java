package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.documents.Sponsor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SponsorDao extends MongoRepository<Sponsor, String> {
}
