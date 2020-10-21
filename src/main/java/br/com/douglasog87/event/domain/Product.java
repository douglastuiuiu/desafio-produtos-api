package br.com.douglasog87.event.domain;

import br.com.douglasog87.domain.Payload;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Builder(builderClassName = "Builder")
@With
@Value
@JsonDeserialize(builder = Product.Builder.class)
public class Product implements Payload {

    String id;
    String name;
    String kind;
    String manufacturer;
    LocalDateTime createdAt, updatedAt;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }

    public static Product parseProduct(br.com.douglasog87.api.model.Product product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .kind(product.getKind())
                .manufacturer(product.getManufacturer())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", createAt='" + createdAt + '\'' +
                ", updateAt='" + updatedAt + '\'' +
                '}';
    }

}
