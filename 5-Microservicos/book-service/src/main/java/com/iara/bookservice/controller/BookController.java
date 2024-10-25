package com.iara.bookservice.controller;

import com.iara.bookservice.model.Book;
import com.iara.bookservice.proxy.CambioProxy;
import com.iara.bookservice.repository.BookRepository;
import com.iara.bookservice.response.Cambio;
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

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return Book.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .currency(book.getCurrency())
                .price(book.getPrice())
                .title(book.getTitle())
                .environment(book.getEnvironment())
                .launchDate(book.getLaunchDate())
                .build();
    }

    @GetMapping("/{id}/{currency}")
    public Optional<Book> createBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        var book = repository.findById(id);

        if(book == null) {
            throw new RuntimeException("Book not found!");
        }

        var cambio = proxy.getCambio(book.get().getPrice(), "USD", currency);
        var port = environment.getProperty("local.server.port");
        book.get().setEnvironment("Book port: " + port + " Cambio Port: " + cambio.getEnvironment());
        book.get().setPrice(cambio.getConvertedValue());
        return book;
    }

//    @GetMapping("/{id}/{currency}")
//    public Optional<Book> createBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
//        var book = repository.findById(id);
//
//        if(book == null) {
//            throw new RuntimeException("Book not found!");
//        }
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("amount", book.get().getPrice().toString());
//        params.put("from", "USD");
//        params.put("to", currency);
//        var response = new RestTemplate()
//                .getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
//                        Cambio.class,
//                        params);
//        var cambio = response.getBody();
//        var port = environment.getProperty("local.server.port");
//        book.get().setEnvironment(port);
//        book.get().setPrice(cambio.getConvertedValue());
//        return book;
//    }
}
