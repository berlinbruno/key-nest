package dev.berlinbruno.key_nest.repository;

import dev.berlinbruno.key_nest.model.Secret;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretRepository extends MongoRepository<Secret, String> {

    List<Secret> findAllByUserId(String userId, Sort sort);
}
