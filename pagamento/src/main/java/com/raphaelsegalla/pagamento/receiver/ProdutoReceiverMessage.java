package com.raphaelsegalla.pagamento.receiver;

import com.raphaelsegalla.pagamento.dto.ProdutoDto;
import com.raphaelsegalla.pagamento.entity.Produto;
import com.raphaelsegalla.pagamento.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoReceiverMessage {

    private final ProdutoRepository produtoRepository;

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receiver(@Payload ProdutoDto produtoDto) {
        produtoRepository.save(Produto.create(produtoDto));
    }
}
