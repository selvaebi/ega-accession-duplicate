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
package uk.ac.ebi.ega.accession.file.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.ega.accession.file.FileAccessioningService;
import uk.ac.ebi.ega.accession.file.FileModel;
import uk.ac.ebi.ampt2d.commons.accession.rest.BasicRestController;

@RestController
@RequestMapping(value = "/v1/file")
public class FileRestController extends BasicRestController<FileModel, FileDTO, String> {

    public FileRestController(FileAccessioningService fileAccessioningService) {
        super(fileAccessioningService, fileModel -> new FileDTO(fileModel.getHash()));
    }

}
