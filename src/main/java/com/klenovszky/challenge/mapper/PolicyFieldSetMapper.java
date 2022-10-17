package com.klenovszky.challenge.mapper;

import com.klenovszky.challenge.entity.PolicyEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

public class PolicyFieldSetMapper implements FieldSetMapper<PolicyEntity> {

    @Override
    public PolicyEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        return new PolicyEntity(
                fieldSet.readString("chdrNum"),
                fieldSet.readString("cownNum"),
                fieldSet.readString("ownerName"),
                fieldSet.readString("lifcNum"),
                fieldSet.readString("lifcName"),
                fieldSet.readString("aracde"),
                fieldSet.readString("agntNum"),
                fieldSet.readString("mailAddress")
        );
    }
}
