package br.com.douglasog87.event.consumer;

import br.com.douglasog87.consumer.EventConsumerConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("productsApiConsumerConfig")
public class ConsumerConfig implements EventConsumerConfig {

    @Getter
    @Value("${products_api.event.config.topic-name:products-api.event}")
    private String eventTopicName;

}
