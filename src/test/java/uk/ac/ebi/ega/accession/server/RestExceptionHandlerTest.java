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
package uk.ac.ebi.ega.accession.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.ega.accession.file.rest.FileDTO;
import uk.ac.ebi.ega.accession.study.rest.StudyDTO;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestExceptionHandlerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void fileDtoValidation() throws Exception {
        FileDTO fileA = new FileDTO("checksum");

        String url = "/v1/file";
        HttpEntity<Object> requestEntity = new HttpEntity<>(Arrays.asList(fileA));

        ResponseEntity<Map> response = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("hash length should be 32 chars", response.getBody().get("debugMessage"));

        fileA = new FileDTO(null);
        requestEntity = new HttpEntity<>(Arrays.asList(fileA));
        response = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("hash value should not be null", response.getBody().get("debugMessage"));

    }

    @Test
    public void studyDtoValidation() throws Exception {
        String url = "/v1/study";
        HttpEntity<Object> requestEntity = new HttpEntity<>(Arrays.asList(new StudyDTO(null)));
        ResponseEntity<Map> response = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Study properties should not be null", response.getBody().get("debugMessage"));

    }

}