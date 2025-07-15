package org.example.communicationservice.Repository;

import org.example.communicationservice.Models.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageTemplateRepo extends JpaRepository<MessageTemplate, UUID> {
    Optional<MessageTemplate> findByName(String name);
}

