package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.api_controllers.GuitarPlayerResource;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class GuitarPlayerControllerPublisherIT {

    @Autowired
    GuitarPlayerResource guitarPlayerResource;

    @Test
    void guitarPlayerInsert(){
        GuitarPlayerCreationDto guitarPlayerCreationDto = new GuitarPlayerCreationDto("diego","riofrio",true,677528554);
        StepVerifier
                .create(guitarPlayerResource.publisher())
                .then(() -> guitarPlayerResource.create(guitarPlayerCreationDto))
                .expectNext(guitarPlayerCreationDto.getName())
                .thenCancel().verify();
    }

}
