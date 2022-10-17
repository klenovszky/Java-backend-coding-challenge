package com.klenovszky.challenge.mapper;

import com.klenovszky.challenge.entity.SurValuesEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

public class SurValuesFieldSetMapper implements FieldSetMapper<SurValuesEntity> {

    @Override
    public SurValuesEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        return new SurValuesEntity(
                fieldSet.readString("company"),
                fieldSet.readString("chdrNum"),
                fieldSet.readDouble("surValue"),
                fieldSet.readString("validDate").substring(0,10)
        );
    }
}
