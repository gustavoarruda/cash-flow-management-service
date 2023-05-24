package com.br.app.cashflowmanagementservice.domain.repository

import com.br.app.cashflowmanagementservice.domain.entities.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<PersonEntity, String>