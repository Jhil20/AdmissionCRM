package org.example.leadmanagement.Repository;

import org.example.leadmanagement.Entities.Leads.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepo extends JpaRepository<Lead, String> {
}
