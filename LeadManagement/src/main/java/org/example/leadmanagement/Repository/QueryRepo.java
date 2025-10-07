package org.example.leadmanagement.Repository;

import org.example.leadmanagement.Entities.Leads.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepo extends JpaRepository<Query, String> {



}
