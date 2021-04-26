package com.raphaelsegalla.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.raphaelsegalla.pagamento.entity.Produto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id","estoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoDto extends RepresentationModel<ProdutoDto> implements Serializable {

    private static final long serialVersionUID = -3996000160816322020L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("estoque")
    private Integer estoque;

    public static ProdutoDto create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoDto.class);
    }

}
