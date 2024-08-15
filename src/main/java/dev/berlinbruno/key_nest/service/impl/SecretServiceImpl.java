package dev.berlinbruno.key_nest.service.impl;

import dev.berlinbruno.key_nest.model.Secret;
import dev.berlinbruno.key_nest.repository.SecretRepository;
import dev.berlinbruno.key_nest.service.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SecretServiceImpl implements SecretService {
    private final SecretRepository secretRepository;

    @Autowired
    public SecretServiceImpl(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    public List<Secret> findAllSecretsByUserId(String userId) {
        return secretRepository.findAllByUserId(userId, Sort.by(Sort.Order.desc("lastModifiedAt")));
    }

    public void saveSecret(Secret secret) {
        secretRepository.save(secret);
        log.info("Created - {}", secret.getId());
    }

    @Override
    public void updateSecret(Secret secret, String id) {
        Optional<Secret> optionalSecret = secretRepository.findById(id);
        if (optionalSecret.isPresent()) {
            Secret updatedSecret = optionalSecret.get();
            updatedSecret.setName(secret.getName());
            updatedSecret.setValue(secret.getValue());
            updatedSecret.setCategory(secret.getCategory());
            updatedSecret.setNotes(secret.getNotes());

            secretRepository.save(updatedSecret);
            log.info("Updated - {}", id);
        }
    }


    public void deleteSecret(String id) {
        secretRepository.deleteById(id);
        log.info("Deleted - {}", id);
    }
}
