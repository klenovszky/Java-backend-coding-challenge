package com.klenovszky.challenge.configuration;

import com.klenovszky.challenge.entity.SurValuesEntity;
import com.klenovszky.challenge.mapper.SurValuesFieldSetMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;


@Configuration
public class SurValuesConfiguration {
    private final static String INPUT_FILE_NAME = "/Input_TXT_Files/ZTPSPF.TXT";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<SurValuesEntity> surValuesItemReader() {
        FlatFileItemReader<SurValuesEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(INPUT_FILE_NAME));

        DefaultLineMapper<SurValuesEntity> customerLineMapper = new DefaultLineMapper<>();

//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
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
    public Step stepSurValues() {
        return stepBuilderFactory.get("stepSurValues")
                .<SurValuesEntity, SurValuesEntity>chunk(10)
                .reader(surValuesItemReader())
                .writer(surValuesItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(stepSurValues())
                .build();
    }
}