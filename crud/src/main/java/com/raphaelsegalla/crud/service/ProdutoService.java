package com.raphaelsegalla.crud.service;

import com.raphaelsegalla.crud.dto.ProdutoDto;
import com.raphaelsegalla.crud.entity.Produto;
import com.raphaelsegalla.crud.exception.ResourceNotFoundException;
import com.raphaelsegalla.crud.message.ProdutoSendMessage;
import com.raphaelsegalla.crud.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoSendMessage produtoSendMessage;

    public ProdutoDto create(ProdutoDto produtoDto) {
        Produto produtoDtoSave = produtoRepository.save(Produto.create(produtoDto));
        ProdutoDto produtoDtoRetorno = ProdutoDto.create(produtoDtoSave);
        produtoSendMessage.sendMessage(produtoDtoRetorno);
        return produtoDtoRetorno;
    }

    public Page<ProdutoDto> findAll(Pageable pageable) {
        var page = produtoRepository.findAll(pageable);
        return page.map(this::convertToProdutoDto);
    }


    public ProdutoDto findById(Long id) {
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id: " + id));
        return ProdutoDto.create(entity);
    }

    public ProdutoDto update(ProdutoDto produtoDto) {
        final Optional<Produto> optionalProduto = produtoRepository.findById(produtoDto.getId());
        if(!optionalProduto.isPresent()) {
            new ResourceNotFoundException("No records found for id: " + produtoDto.getId());
        }
        return ProdutoDto.create(produtoRepository.save(Produto.create(produtoDto)));
    }

    public void delete(Long id) {
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id: " + id));
        produtoRepository.delete(entity);
    }

    private ProdutoDto convertToProdutoDto(Produto produto) {
        return ProdutoDto.create(produto);
    }
}
