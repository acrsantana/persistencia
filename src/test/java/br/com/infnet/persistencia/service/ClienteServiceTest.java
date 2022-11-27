package br.com.infnet.persistencia.service;

import br.com.infnet.persistencia.exceptions.ClienteInexistenteException;
import br.com.infnet.persistencia.exceptions.IntegridadeDeDadosVioladaException;
import br.com.infnet.persistencia.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    void testSalvaClienteComSucesso(){
        Cliente cliente = new Cliente();
        cliente.setNome("Cezão da Bahia");
        cliente.setEmail("cezaodabahia@gmail.com");
        Cliente clienteValidacao = clienteService.save(cliente);
        assertNotNull(clienteValidacao);
    }

    @Test
    void testErroAoSalvarClienteSemNome(){
        Cliente cliente = new Cliente();
        cliente.setEmail("cezaodabahia@gmail.com");
        assertThrowsExactly(
            IntegridadeDeDadosVioladaException.class,
            () -> clienteService.save(cliente));
    }

    @Test
    void testErroAoSalvarClienteSemEmail(){
        Cliente cliente = new Cliente();
        cliente.setNome("Cezão da Bahia");
        assertThrowsExactly(
            IntegridadeDeDadosVioladaException.class,
            () -> clienteService.save(cliente));
    }

    @Test
    void testErroAoSalvarClienteComEmailRepetido(){
        Cliente cliente = new Cliente();
        cliente.setNome("Luna Luneta");
        cliente.setEmail("luna@gmail.com");
        clienteService.save(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Luna Carvalho");
        cliente2.setEmail("luna@gmail.com");

        assertThrowsExactly(
            IntegridadeDeDadosVioladaException.class,
            () -> clienteService.save(cliente2));
    }

    @Test
    void testBuscaClientePorUUID(){
        Cliente cliente = new Cliente();
        cliente.setNome("Jojo Colonia");
        cliente.setEmail("jojo@gmail.com");
        clienteService.save(cliente);

        Cliente clienteValidacao = clienteService.findById(cliente.getUuid());

        assertNotNull(clienteValidacao);
        assertEquals(cliente.getUuid(), clienteValidacao.getUuid());
        assertEquals(cliente.getNome(), clienteValidacao.getNome());
        assertEquals(cliente.getEmail(), clienteValidacao.getEmail());
    }

    @Test
    void testBuscaClienteInexistentePorUUID(){

        assertThrowsExactly(
            ClienteInexistenteException.class,
            () -> clienteService.findById(UUID.fromString("f7150a4b-9999-9999-9999-97d0a9e659ea")));

    }

    @Test
    void testBuscarTodosOsClienteComSucesso(){
        Cliente cliente = new Cliente();
        cliente.setNome("Jorge Cabanas");
        cliente.setEmail("cabanas@gmail.com");
        clienteService.save(cliente);
        List<Cliente> clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() >= 1);
    }

    @Test
    void testDeletarPorUUID(){
        Cliente cliente = new Cliente();
        cliente.setNome("Hermanos Dalton");
        cliente.setEmail("hermanos@gmail.com");

        clienteService.save(cliente);
        UUID uuid = cliente.getUuid();
        clienteService.deleteById(cliente.getUuid());

        assertThrowsExactly(
            ClienteInexistenteException.class,
            () -> clienteService.findById(uuid));

    }

    @Test
    void testDeletarPorUUIDInexistente(){

        assertThrowsExactly(
            ClienteInexistenteException.class,
            () -> clienteService.deleteById(UUID.fromString("f7150a4b-9999-9999-9999-97d0a9e659ea")));

    }

}
