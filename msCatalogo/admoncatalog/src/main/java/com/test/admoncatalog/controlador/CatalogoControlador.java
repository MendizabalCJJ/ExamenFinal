package com.test.admoncatalog.controlador;

import java.util.Optional;

import com.test.admoncatalog.modelos.Catalogo;
import com.test.admoncatalog.repositorios.CatalogoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/catalogo")
public class CatalogoControlador {
    @Autowired
    private CatalogoRepositorio repo;

    @GetMapping(value = "/{id}")
    public Optional<Catalogo> getCatalogo(@PathVariable int id) {
        return repo.findById(id);
    }
    
    @PostMapping
    public Catalogo postCatalogo(@RequestBody Catalogo body){
        return repo.save(body);
    }
}
