package com.gonzaga.contatos.service.impl;
import com.gonzaga.contatos.model.Contato;
import com.gonzaga.contatos.repositories.ContatosRepository;
import com.gonzaga.contatos.service.ContatosServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Data
@Service
public class ContatosServiceImpl implements ContatosServices {

    private final List<Contato> contatos = new ArrayList<>();

    @Autowired
    private ContatosRepository contatosRepository;

    @Override
    public Contato cadastrar(Contato contato) {

        var contatoEncontrado = contatosRepository.findFirstByDocumento(contato.getDocumento());

        if (Objects.nonNull(contatoEncontrado))
            return null;

        contato.setId(UUID.randomUUID().toString());
        return contatosRepository.save(contato);
    }

    @Override
    public List<Contato> listar() {
        return contatosRepository.findAll();
    }

    @Override
    public Contato buscarPorId(String id) {
        return contatosRepository.findById(id).orElse(null);
    }

    @Override
    public boolean inativar(String id) {

        var contato = buscarPorId(id);

        if (Objects.isNull(contato)) return false;

        contato.setAtivo(false);
        contatosRepository.save(contato);
        return true;

    }

    @Override
    public boolean ativar(String id) {

        var contato = buscarPorId(id);

        if (Objects.nonNull(contato)){
            contato.setAtivo(true);
            contatosRepository.save(contato);
            return true;
        }
        return true;
    }

    @Override
    public Contato deletar(String id) {

        contatos.removeIf(c -> c.getId().equals(id) && c.isAtivo());
        return null;
    }

    @Override
    public Contato atualizar(String id, String nome, String documento) {

        return contatos
                .stream()
                .filter(c -> id.equals(c.getId()) && (c.isAtivo())
                        && !(c.equals(nome)) && (nonNull(nome))
                        && !(c.equals(documento)) && c.getDocumento() != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));
    }
}
