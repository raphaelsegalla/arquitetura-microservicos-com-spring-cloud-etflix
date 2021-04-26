package com.raphaelsegalla.pagamento.entity;

import com.raphaelsegalla.pagamento.dto.VendaDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda implements Serializable {

    private static final long serialVersionUID = -7342690561583734695L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyy")
    @Column(name = "data", nullable = false)
    private Date data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = {CascadeType.REFRESH})
    private List<ProdutoVenda> produtos;

    @Column(name = "valor_total", nullable = false, length = 10)
    private Double valorTotal;

    public static Venda create(VendaDto vendaDto) {
        return new ModelMapper().map(vendaDto, Venda.class);
    }
}
