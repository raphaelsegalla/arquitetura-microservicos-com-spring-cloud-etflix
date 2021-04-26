package com.raphaelsegalla.pagamento.entity;

import com.raphaelsegalla.pagamento.dto.ProdutoDto;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto {

    @Id
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto create(ProdutoDto produtoDto) {
        return new ModelMapper().map(produtoDto, Produto.class);
    }
}
