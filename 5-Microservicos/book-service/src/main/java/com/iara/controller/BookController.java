package com.iara.controller;

import com.iara.model.Book;
import com.iara.proxy.CambioProxy;
import com.iara.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.NotFoundException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private Environment environment;
    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @Operation(summary = "Find a book by id")
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

    @Operation(summary = "Create a new book")
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setLaunchDate(new Date());
        return repository.save(book);
    }
}
