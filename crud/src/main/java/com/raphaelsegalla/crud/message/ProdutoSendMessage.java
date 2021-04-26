package com.raphaelsegalla.crud.message;

import com.raphaelsegalla.crud.dto.ProdutoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoSendMessage {

    @Value("${crud.rabbitmq.exchange}")
    String exchange;

    @Value("${crud.rabbitmq.routingkey}")
    String routingkey;

    public final RabbitTemplate rabbitTemplate;

    public void sendMessage(ProdutoDto produtoDto) {
        rabbitTemplate.convertAndSend(exchange, routingkey, produtoDto);
    }
}
