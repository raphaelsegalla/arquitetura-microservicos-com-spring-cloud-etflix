package com.raphaelsegalla.crud.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoVendaDto extends RepresentationModel<ProdutoVendaDto> implements Serializable {

    private static final long serialVersionUID = -3513838691072788171L;

    private Long idProduto;

    private Integer quantidade;

}
