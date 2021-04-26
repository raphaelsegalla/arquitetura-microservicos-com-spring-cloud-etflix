package com.raphaelsegalla.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.raphaelsegalla.crud.entity.Produto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id","nome","estoque","preco"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoDto extends RepresentationModel<ProdutoDto> implements Serializable {

    private static final long serialVersionUID = 4727086662881315881L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private Double preco;

    public static ProdutoDto create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoDto.class);
    }

}
