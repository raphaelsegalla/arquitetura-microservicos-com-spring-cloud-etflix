package com.raphaelsegalla.pagamento.message;

import com.raphaelsegalla.pagamento.dto.ProdutoVendaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutoEstoqueSendMessage {

    @Value("${crud.rabbitmq.exchange}")
    String exchange;

    @Value("${crud.rabbitmq.routingkey}")
    String routingkey;

    public final RabbitTemplate rabbitTemplate;

    public void sendMessage(List<ProdutoVendaDto> produtoVendaDtos) {
        rabbitTemplate.convertAndSend(exchange, routingkey, produtoVendaDtos);
    }
}
