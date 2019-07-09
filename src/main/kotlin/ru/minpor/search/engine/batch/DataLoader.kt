package ru.minpor.search.engine.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.xml.StaxEventItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import ru.minpor.search.engine.batch.listener.ItemCountListener
import ru.minpor.search.engine.batch.model.License
import ru.minpor.search.engine.batch.model.Person
import ru.minpor.search.engine.batch.processor.ItemLicenseProcessor
import ru.minpor.search.engine.batch.processor.ItemPersonProcessor
import ru.minpor.search.engine.batch.provider.LicenseSqlParameterSourceProvider
import ru.minpor.search.engine.batch.provider.PersonSqlParameterSourceProvider
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
class DataLoader(val jobBuilderFactory: JobBuilderFactory, val stepBuilderFactory: StepBuilderFactory) {

    @Bean
    fun writerLicense(dataSource: DataSource): JdbcBatchItemWriter<License> {
        return JdbcBatchItemWriterBuilder<License>()
                .itemSqlParameterSourceProvider(LicenseSqlParameterSourceProvider())
                .sql("INSERT INTO license(data) VALUES (:json)")
                .dataSource(dataSource)
                .build()
    }

    @Bean
    fun writerPerson(dataSource: DataSource): JdbcBatchItemWriter<Person> {
        return JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(PersonSqlParameterSourceProvider())
                .sql("INSERT INTO person(last_name) VALUES (:json)")
                .dataSource(dataSource)
                .build()
    }

    @Bean
    fun step1(writerLicense: JdbcBatchItemWriter<License>): Step {
        return stepBuilderFactory.get("step1")
                .chunk<License, License>(1000)
                .reader(readerLicense())
                .processor(licenseProcessor())
                .writer(writerLicense)
                .listener(listener())
                .build()
    }

    @Bean
    fun step2(writerPerson: JdbcBatchItemWriter<Person>): Step {
        return stepBuilderFactory.get("step2")
                .chunk<Person, Person>(100)
                .reader(readerPerson())
                .processor(personProcessor())
                .writer(writerPerson)
                .build()
    }

    private fun licenseProcessor(): ItemProcessor<License, License> {
        return ItemLicenseProcessor()
    }

    private fun personProcessor(): ItemProcessor<Person, Person> {
        return ItemPersonProcessor()
    }

    @Bean
    fun readerLicense(): StaxEventItemReader<License> {
        val reader: StaxEventItemReader<License> = StaxEventItemReader()
        reader.setResource(FileSystemResource("/storage-mf/samples/data-20190526-structure-20170926.xml"))
        reader.setFragmentRootElementName("licenses")

        val unMarshaller = Jaxb2Marshaller()
        unMarshaller.setClassesToBeBound(License::class.java)
        reader.setUnmarshaller(unMarshaller)

        return reader
    }

    @Bean
    fun readerPerson(): StaxEventItemReader<Person> {
        val reader: StaxEventItemReader<Person> = StaxEventItemReader()
        reader.setResource(ClassPathResource("people.xml"))
        reader.setFragmentRootElementName("Person")

        val unMarshaller = Jaxb2Marshaller()
        unMarshaller.setClassesToBeBound(Person::class.java)
        reader.setUnmarshaller(unMarshaller)

        return reader
    }

    @Bean
    fun listener(): ItemCountListener {
        return ItemCountListener()
    }

    @Bean
    fun importUserJob(step1: Step, step2: Step): Job {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build()
    }


}

