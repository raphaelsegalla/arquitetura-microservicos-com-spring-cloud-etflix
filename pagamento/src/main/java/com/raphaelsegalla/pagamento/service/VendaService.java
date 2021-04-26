package com.raphaelsegalla.pagamento.service;

import com.raphaelsegalla.pagamento.dto.ProdutoVendaDto;
import com.raphaelsegalla.pagamento.dto.VendaDto;
import com.raphaelsegalla.pagamento.entity.Produto;
import com.raphaelsegalla.pagamento.entity.ProdutoVenda;
import com.raphaelsegalla.pagamento.entity.Venda;
import com.raphaelsegalla.pagamento.exception.ResourceNotFoundException;
import com.raphaelsegalla.pagamento.message.ProdutoEstoqueSendMessage;
import com.raphaelsegalla.pagamento.repository.ProdutoRepository;
import com.raphaelsegalla.pagamento.repository.ProdutoVendaRepository;
import com.raphaelsegalla.pagamento.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoEstoqueSendMessage produtoEstoqueSendMessage;

    public VendaDto create(VendaDto vendaDto) {
        Venda venda = vendaRepository.save(Venda.create(vendaDto));

        List<ProdutoVenda> produtosSalvos = new ArrayList<>();
        List<ProdutoVendaDto> produtoVendaDtos = new ArrayList<>();
        vendaDto.getProdutos().forEach(p -> {
            ProdutoVenda produtoVenda = ProdutoVenda.create(p);
            produtoVenda.setVenda(venda);
            ProdutoVenda produtoVendaSalvo = produtoVendaRepository.save(produtoVenda);
            Optional<Produto> produtoAtual = produtoRepository.findById(p.getIdProduto());
            if(produtoAtual.isPresent() && produtoAtual.get().getEstoque() - p.getQuantidade() >= 0) {
                produtoAtual.get().setEstoque(produtoAtual.get().getEstoque() - p.getQuantidade());
            }
            produtoVendaDtos.add(ProdutoVendaDto.create(produtoVendaSalvo));
            produtosSalvos.add(produtoVendaSalvo);
        });
        venda.setProdutos(produtosSalvos);
        produtoEstoqueSendMessage.sendMessage(produtoVendaDtos);

        return VendaDto.create(venda);
    }

    public Page<VendaDto> findAll(Pageable pageable) {
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaDto);
    }


    public VendaDto findById(Long id) {
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id: " + id));
        return VendaDto.create(entity);
    }

    private VendaDto convertToVendaDto(Venda venda) {
        return VendaDto.create(venda);
    }
}
