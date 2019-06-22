package com.asyncstream.cloudmessage.stream.config;

import com.datastax.driver.core.*;
import com.datastax.driver.extras.codecs.jdk8.ZonedDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
import org.springframework.data.convert.CustomConversions;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

@Configuration
@PropertySource("classpath:config/db.properties")
public class DbConfiguration extends AbstractCassandraConfiguration {

    private static final String CLUSTER_NAME = "cloudmessagecluster";

    @Autowired
    private Environment env;

    @Override
    protected String getKeyspaceName() {
        return "cloudmessagestream";
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        TupleType tupleType = TupleType.of(ProtocolVersion.NEWEST_SUPPORTED, CodecRegistry.DEFAULT_INSTANCE,DataType.timestamp(), DataType.text());
        CodecRegistry.DEFAULT_INSTANCE.register(new ZonedDateTimeCodec(tupleType));

        CassandraClusterFactoryBean cluster = super.cluster();
        cluster.setJmxReportingEnabled(false);
        cluster.setClusterName(CLUSTER_NAME);
        cluster.setContactPoints(env.getProperty("cassandra.dev.host"));
        cluster.setPort(Integer.parseInt(env.getProperty("cassandra.dev.cql.port")));
        return cluster;
    }

    @Bean
    public CustomConversions customConversions() {
        return new CassandraCustomConversions(Arrays.asList(CassandraZoneDateTupleToZonedDateTimeConverter.INSTANCE,
                ZonedDateTimeToCassandraLocalDateConverter.INSTANCE));
    }

    @Bean
    public CassandraBatchOperations cassandraBatchOperations(CassandraTemplate cassandraTemplate) {
        return cassandraTemplate.batchOps();
    }

    public enum CassandraZoneDateTupleToZonedDateTimeConverter implements Converter<TupleValue, ZonedDateTime> {

        INSTANCE;

        @Override
        public ZonedDateTime convert(TupleValue source) {
            java.util.Date timestamp = source.getTimestamp(0);
            ZoneId zoneId = ZoneId.of(source.getString(1));

            return timestamp.toInstant().atZone(zoneId);
        }
    }

    public enum ZonedDateTimeToCassandraLocalDateConverter implements Converter<ZonedDateTime, TupleValue> {

        INSTANCE;

        final static TupleType type = TupleType.of(ProtocolVersion.NEWEST_SUPPORTED, CodecRegistry.DEFAULT_INSTANCE,
                DataType.timestamp(), DataType.text());

        @Override
        public TupleValue convert(ZonedDateTime source) {
            TupleValue tupleValue = type.newValue();

            tupleValue.setTimestamp(0, Date.from(source.toLocalDateTime().toInstant(ZoneOffset.UTC)));
            tupleValue.setString(1, source.getZone().toString());

            return tupleValue;
        }
    }

}
