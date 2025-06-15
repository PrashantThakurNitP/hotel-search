package com.hotelbooking.search_service.config;

import com.hotelbooking.common.event.BookingEvent;
import com.hotelbooking.common.event.HotelEvent;
import com.hotelbooking.common.event.RoomEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "search-service-group";




    private Map<String, Object> baseConsumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.hotelbooking.common.event");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        return props;
    }

    @Bean
    public ConsumerFactory<String, HotelEvent> hotelEventConsumerFactory() {
        Map<String, Object> props = baseConsumerProps();
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, HotelEvent.class.getName());
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, RoomEvent> roomEventConsumerFactory() {
        Map<String, Object> props = baseConsumerProps();
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, RoomEvent.class.getName());
        return new DefaultKafkaConsumerFactory<>(props);
    }




    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, HotelEvent> hotelKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, HotelEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(hotelEventConsumerFactory());
        factory.setCommonErrorHandler(globalKafkaErrorHandler());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RoomEvent> roomKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RoomEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(roomEventConsumerFactory());
        factory.setCommonErrorHandler(globalKafkaErrorHandler());
        return factory;
    }

    @Bean
    public DefaultErrorHandler globalKafkaErrorHandler() {
        // Retry 3 times with 1 second delay
        return new DefaultErrorHandler(new FixedBackOff(1000L, 3));
    }



}
