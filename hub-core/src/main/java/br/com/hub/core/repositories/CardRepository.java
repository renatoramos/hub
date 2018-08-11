package br.com.hub.core.repositories;

import br.com.hub.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
@Repository
public interface CardRepository extends CrudRepository<Card, String> {}

