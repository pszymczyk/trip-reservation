package com.pszymczyk.adapter.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableJpaRepositories("com.pszymczyk.adapter.persistence")
@EntityScan("com.pszymczyk.adapter.persistence")
class PersistenceConfiguration {

}
