package com.raphaelsegalla.crud.receiver;

import com.raphaelsegalla.crud.dto.ProdutoVendaDto;
import com.raphaelsegalla.crud.entity.Produto;
import com.raphaelsegalla.crud.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProdutoEstoqueReceiverMessage {

    private final ProdutoRepository produtoRepository;

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receiver(@Payload List<ProdutoVendaDto> produtoVendaDtos) {
        produtoVendaDtos.forEach(pv -> {
            Optional<Produto> produto = produtoRepository.findById(pv.getIdProduto());
            if (produto.isPresent()) {
                var quantidadeAtual = produto.get().getEstoque();
                if(quantidadeAtual - pv.getQuantidade() >= 0) {
                    produto.get().setEstoque(quantidadeAtual - pv.getQuantidade());
                }
                produtoRepository.save(produto.get());
            }
        });
    }
}
