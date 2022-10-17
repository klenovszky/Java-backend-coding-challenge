package com.klenovszky.challenge.configuration;

import com.klenovszky.challenge.entity.OutPayHeaderEntity;
import com.klenovszky.challenge.entity.PolicyEntity;
import com.klenovszky.challenge.entity.SurValuesEntity;
import com.klenovszky.challenge.mapper.OutPayHeaderFieldSetMapper;
import com.klenovszky.challenge.mapper.PolicyFieldSetMapper;
import com.klenovszky.challenge.mapper.SurValuesFieldSetMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;


@Configuration
public class JobConfiguration {
    private static final String SURVALUES_FILE_NAME = "/Input_TXT_Files/ZTPSPF.TXT";
    private static final String OUTPAYHEADER_FILE_NAME = "/Input_TXT_Files/OUTPH_CUP_20200204_1829.TXT";
    private static final String POLICY_FILE_NAME = "/Input_TXT_Files/CUSTCOMP01.txt";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<SurValuesEntity> surValuesItemReader() {
        FlatFileItemReader<SurValuesEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(SURVALUES_FILE_NAME));

        DefaultLineMapper<SurValuesEntity> customerLineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setNames("company", "chdrNum", "surValue", "jobUser", "jobName", "validDate");
        tokenizer.setColumns(new Range(1, 1),
                new Range(2, 9),
                new Range(10, 24),
                new Range(25, 31),
                new Range(35, 42),
                new Range(45)
        );
        customerLineMapper.setLineTokenizer(tokenizer);
        customerLineMapper.setFieldSetMapper(new SurValuesFieldSetMapper());
        customerLineMapper.afterPropertiesSet();
        reader.setLineMapper(customerLineMapper);
        return reader;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public JdbcBatchItemWriter<SurValuesEntity> surValuesItemWriter() {
        JdbcBatchItemWriter<SurValuesEntity> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("INSERT INTO surValues (chdrNum, surValue, company, validDate) VALUES (:chdrNum, :surValue, :company, :validDate)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }
    @Bean
    public FlatFileItemReader<OutPayHeaderEntity> outPayHeaderItemReader() {
        FlatFileItemReader<OutPayHeaderEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(OUTPAYHEADER_FILE_NAME));

        DefaultLineMapper<OutPayHeaderEntity> customerLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(";");
        tokenizer.setNames("clntnum", "chdrnum", "letterType", "printDate", "dataID", "clntName", "clntAddress", "benPercent", "role1", "role2", "cownNum", "cownName", "dummy");
        customerLineMapper.setLineTokenizer(tokenizer);
        customerLineMapper.setFieldSetMapper(new OutPayHeaderFieldSetMapper());
        customerLineMapper.afterPropertiesSet();
        reader.setLineMapper(customerLineMapper);
        return reader;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public JdbcBatchItemWriter<OutPayHeaderEntity> outPayHeaderItemWriter() {
        JdbcBatchItemWriter<OutPayHeaderEntity> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("INSERT INTO OutPayHeader (clntnum, chdrnum, letterType, printDate, dataID, clntName, clntAddress, benPercent, role1, role2, cownNum, cownName) VALUES (:clntnum, :chdrnum, :letterType, :printDate, :dataID, :clntName, :clntAddress, :benPercent, :role1, :role2, :cownNum, :cownName)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public FlatFileItemReader<PolicyEntity> policyItemReader() {
        FlatFileItemReader<PolicyEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(POLICY_FILE_NAME));

        DefaultLineMapper<PolicyEntity> customerLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter("|");
        tokenizer.setNames("chdrNum", "cownNum", "ownerName", "lifcNum", "lifcName", "aracde", "agntNum", "mailAddress", "dummy");
        customerLineMapper.setLineTokenizer(tokenizer);
        customerLineMapper.setFieldSetMapper(new PolicyFieldSetMapper());
        customerLineMapper.afterPropertiesSet();
        reader.setLineMapper(customerLineMapper);
        return reader;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public JdbcBatchItemWriter<PolicyEntity> policyItemWriter() {
        JdbcBatchItemWriter<PolicyEntity> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("INSERT INTO policy (chdrNum, cownNum, ownerName, lifcNum, lifcName, aracde, agntNum, mailAddress) VALUES (:chdrNum, :cownNum, :ownerName, :lifcNum, :lifcName, :aracde, :agntNum, :mailAddress)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public Step stepSurValues() {
        return stepBuilderFactory.get("stepSurValues")
                .<SurValuesEntity, SurValuesEntity>chunk(10)
                .reader(surValuesItemReader())
                .writer(surValuesItemWriter())
                .build();
    }
    @Bean
    public Step stepOutPayHeader() {
        return stepBuilderFactory.get("stepOutPayHeader")
                .<OutPayHeaderEntity, OutPayHeaderEntity>chunk(10)
                .reader(outPayHeaderItemReader())
                .writer(outPayHeaderItemWriter())
                .build();
    }
    @Bean
    public Step stepPolicy() {
        return stepBuilderFactory.get("stepPolicy")
                .<PolicyEntity, PolicyEntity>chunk(10)
                .reader(policyItemReader())
                .writer(policyItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(stepSurValues())
                .next(stepOutPayHeader())
                .next(stepPolicy())
                .build();
    }
}