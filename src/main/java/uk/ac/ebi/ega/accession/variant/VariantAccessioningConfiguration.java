/*
 *
 * Copyright 2018 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package uk.ac.ebi.ega.accession.variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.ac.ebi.ampt2d.commons.accession.autoconfigure.EnableBasicRestControllerAdvice;
import uk.ac.ebi.ega.accession.variant.persistence.VariantAccessioningDatabaseService;
import uk.ac.ebi.ega.accession.variant.persistence.VariantAccessioningRepository;
import uk.ac.ebi.ampt2d.commons.accession.autoconfigure.EnableSpringDataContiguousIdService;
import uk.ac.ebi.ampt2d.commons.accession.generators.monotonic.MonotonicAccessionGenerator;
import uk.ac.ebi.ampt2d.commons.accession.persistence.monotonic.service.ContiguousIdBlockService;

@Configuration
@EnableSpringDataContiguousIdService
@EntityScan({"uk.ac.ebi.ega.accession.variant.persistence"})
@EnableJpaRepositories(basePackages = {"uk.ac.ebi.ega.accession.variant.persistence"})
@EnableBasicRestControllerAdvice
public class VariantAccessioningConfiguration {

    @Autowired
    private VariantAccessioningRepository repository;
    @Autowired
    private ContiguousIdBlockService service;

    @Bean
    @ConfigurationProperties(prefix = "accessioning.variant")
    public VariantApplicationProperties getVariantApplicationProperties() {
        return new VariantApplicationProperties();
    }

    @Bean
    public VariantAccessioningService variantAccessionService() {
        return new VariantAccessioningService(variantAccessionGenerator(), variantAccessioningDatabaseService());
    }

    @Bean
    public VariantAccessioningDatabaseService variantAccessioningDatabaseService() {
        return new VariantAccessioningDatabaseService(repository);
    }

    @Bean
    public MonotonicAccessionGenerator<VariantModel> variantAccessionGenerator() {
        VariantApplicationProperties variantApplicationProperties = getVariantApplicationProperties();
        return new MonotonicAccessionGenerator<>(variantApplicationProperties.getBlockSize(),
                variantApplicationProperties.getCategoryId(), variantApplicationProperties.getInstanceId(),
                service);
    }
}