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

package uk.ac.ebi.ega.accession.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.ac.ebi.ampt2d.commons.accession.autoconfigure.EnableBasicRestControllerAdvice;
import uk.ac.ebi.ega.accession.file.persistence.FileAccessioningDatabaseService;
import uk.ac.ebi.ega.accession.file.persistence.FileAccessioningRepository;

@Configuration
@EntityScan({"uk.ac.ebi.ega.accession.file.persistence"})
@EnableJpaRepositories(basePackages = {"uk.ac.ebi.ega.accession.file.persistence"})
@EnableBasicRestControllerAdvice
public class FileServiceConfiguration {

    @Autowired
    private FileAccessioningRepository repository;

    @Bean
    public FileAccessioningService fileAccessionService() {
        return new FileAccessioningService(fileAccessioningDatabaseService());
    }

    @Bean
    public FileAccessioningDatabaseService fileAccessioningDatabaseService() {
        return new FileAccessioningDatabaseService(repository);
    }

}
