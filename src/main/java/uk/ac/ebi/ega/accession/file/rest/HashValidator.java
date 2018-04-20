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

import uk.ac.ebi.ega.accession.file.FileModel;
import uk.ac.ebi.ega.accession.file.HashType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HashValidator implements ConstraintValidator<ValidHash, FileModel> {

    @Override
    public void initialize(ValidHash constraintAnnotation) {
    }

    @Override
    public boolean isValid(FileModel fileModel, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        if (fileModel.getHashType() == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Please provide a hashType(Supported Types:MD5,SHA1) and a valid hash").addConstraintViolation();
            return false;
        }

        if (fileModel.getHash() == null || (fileModel.getHashType() == HashType.MD5 && fileModel.getHash().length() != 32) ||
                (fileModel.getHashType() == HashType.SHA1 && fileModel.getHash().length() != 40)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Please provide a valid hash")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
