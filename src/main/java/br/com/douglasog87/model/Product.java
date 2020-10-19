package br.com.douglasog87.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -2475998499776498054L;

    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String kind;
    @NotEmpty
    private String manufacturer;

    private LocalDateTime createdAt, updatedAt;

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
