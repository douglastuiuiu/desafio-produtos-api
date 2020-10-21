package br.com.douglasog87.event.consumer;

import br.com.douglasog87.consumer.EventConsumerConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("productsApiConsumerConfig")
public class ProductsApiConsumerConfig implements EventConsumerConfig {

    @Getter
    @Value("${products-api.event.config.topic-name:products-api.event}")
    private String eventTopicName;

}
