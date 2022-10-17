package com.klenovszky.challenge.mapper;

import com.klenovszky.challenge.entity.OutPayHeaderEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

public class OutPayHeaderFieldSetMapper implements FieldSetMapper<OutPayHeaderEntity> {

    @Override
    public OutPayHeaderEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        return new OutPayHeaderEntity(
                fieldSet.readString("clntnum"),
                fieldSet.readString("chdrnum"),
                fieldSet.readString("letterType"),
                fieldSet.readString("printDate"),
                fieldSet.readString("dataID"),
                fieldSet.readString("clntName"),
                fieldSet.readString("clntAddress"),
                fieldSet.readDouble("benPercent"),
                fieldSet.readString("role1"),
                fieldSet.readString("role2"),
                fieldSet.readString("cownNum"),
                fieldSet.readString("cownName")
        );
    }
}
