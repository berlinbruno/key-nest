package dev.berlinbruno.key_nest.service.impl;

import dev.berlinbruno.key_nest.model.Secret;
import dev.berlinbruno.key_nest.repository.SecretRepository;
import dev.berlinbruno.key_nest.service.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SecretServiceImpl implements SecretService {
    private final SecretRepository secretRepository;

    @Autowired
    public SecretServiceImpl(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    @Override
    public List<Secret> findAllSecretsByUserId(String userId) {
        return secretRepository.findAllByUserId(userId, Sort.by(Sort.Order.desc("lastModifiedAt")));
    }

    @Override
    public void saveSecret(Secret secret) {
        secretRepository.save(secret);
        log.info("Saved - {}", secret.getId());
    }

    @Override
    public void updateSecret(Secret secret, String id) {
        secretRepository.findById(id).ifPresent(existingSecret -> {
            existingSecret.setSecretName(secret.getSecretName());
            existingSecret.setName(secret.getName());
            existingSecret.setValue(secret.getValue());
            existingSecret.setCategory(secret.getCategory());
            existingSecret.setNotes(secret.getNotes());

            secretRepository.save(existingSecret);
            log.info("Updated - {}", id);
        });
    }

    @Override
    public void deleteSecret(String id) {
        secretRepository.deleteById(id);
        log.info("Deleted - {}", id);
    }
}
