package br.com.infnet.persistencia.service;

import br.com.infnet.persistencia.exceptions.BancoDeDadosIndisponivelException;
import br.com.infnet.persistencia.exceptions.ClienteInexistenteException;
import br.com.infnet.persistencia.exceptions.IntegridadeDeDadosVioladaException;
import br.com.infnet.persistencia.model.Cliente;
import br.com.infnet.persistencia.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Classe implementada para fins didáticos, atendendo as rubricas 6, 7 e 8 do spring data
@Service @Slf4j
public class ClienteService {
    private final ClienteRepository clienteRepository;

    //Injeção de dependência via construtor
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente){
        log.info("Tentando salvar o cliente");
        try {
            Cliente save = clienteRepository.save(cliente);
            log.info("Cliente com UUID {} salvo com sucesso", save.getUuid());
            return save;
        } catch (DataIntegrityViolationException e) {
            log.error("Algum campo obrigatório do cliente não foi preenchido. Corrigir e tentar de novo.");
            throw new IntegridadeDeDadosVioladaException("Algum campo obrigatório do cliente não foi preenchido. Corrigir e tentar de novo.", e);
        } catch (CannotCreateTransactionException e) {
            log.error("Problemas ao conectar com o banco de dados, tente novamente mais tarde.");
            throw new BancoDeDadosIndisponivelException("Problemas ao conectar com o banco de dados, tente novamente mais tarde.", e);
        }
    }

    public Cliente findById(UUID id){
        log.info("Buscando cliente com UUID = {}", id);
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()){
            log.error("Não existe cliente com o id informado: {}", id);
            throw new ClienteInexistenteException("Não existe cliente com o id informado: " + id);
        }
         return optionalCliente.get();
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public void deleteById(UUID id){
        log.info("Deletando cliente com UUID {}.", id);
        try {
            clienteRepository.deleteById(id);
            log.info("Cliente com UUID {} deletado com sucesso.", id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Não existe cliente com o id informado: {}", id);
            throw new ClienteInexistenteException("Não existe cliente com o id informado: " + id);
        }

    }
}
