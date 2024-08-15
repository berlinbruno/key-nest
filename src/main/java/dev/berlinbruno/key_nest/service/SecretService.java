package dev.berlinbruno.key_nest.service;

import dev.berlinbruno.key_nest.model.Secret;

import java.util.List;

public interface SecretService {
    List<Secret> findAllSecretsByUserId(String id);

    void saveSecret(Secret secret);

    void updateSecret(Secret secret, String id);

    void deleteSecret(String id);
}
