package com.iara.bookservice.controller;

import com.iara.bookservice.model.Book;
import com.iara.bookservice.proxy.CambioProxy;
import com.iara.bookservice.repository.BookRepository;
import com.iara.bookservice.response.Cambio;
import jakarta.ws.rs.NotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private Environment environment;
    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @GetMapping("/{id}/{currency}")
    public Optional<Book> findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id " + id + " not found!"));

        if(book == null) {
            throw new RuntimeException("Book not found!");
        }

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + port + " Cambio Port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());
        return Optional.of(book);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setLaunchDate(new Date());
        return repository.save(book);
    }
}
