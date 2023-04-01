package org.serf.ApplicantPortal.domain.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.serf.ApplicantPortal.domain.enums.ESubject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class SubjectsConverter implements AttributeConverter<List<ESubject>, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<ESubject> eSubjects) {
        String mappedEsubjects = null;
        try {
            mappedEsubjects = objectMapper.writeValueAsString(eSubjects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mappedEsubjects;
    }

    @Override
    public List<ESubject> convertToEntityAttribute(String s) {
        List<ESubject> subjects = null;
        try {
            subjects = objectMapper.readValue(s, new TypeReference<List<ESubject>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return subjects;
    }

}
