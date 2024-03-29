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
package uk.ac.ebi.ega.accession.file.persistence;

import uk.ac.ebi.ega.accession.file.FileModel;
import uk.ac.ebi.ampt2d.commons.accession.core.AccessioningRepository;
import uk.ac.ebi.ampt2d.commons.accession.persistence.BasicSpringDataRepositoryDatabaseService;

public class FileAccessioningDatabaseService extends BasicSpringDataRepositoryDatabaseService<FileModel, FileEntity, String, String> {

    public FileAccessioningDatabaseService(AccessioningRepository<FileEntity, String, String> repository) {
        super(repository,
                mha -> new FileEntity(mha.hash(), mha.accession()),
                FileEntity::getAccession,
                FileEntity::getHashedMessage);
    }

}
