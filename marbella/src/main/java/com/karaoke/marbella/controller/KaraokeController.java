package com.karaoke.marbella.controller;


import com.karaoke.marbella.dao.Karaoke;
import com.karaoke.marbella.dao.KaraokeRepository;
import com.karaoke.marbella.exception.KaraokeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeRepository repository;

    @PostMapping("/")
    public ResponseEntity<Karaoke> create(@RequestBody Karaoke karaoke) {
        validateKaraoke(karaoke);
        karaoke.setCancion(karaoke.getCancion().toLowerCase()) ;
        return ResponseEntity.ok(repository.save(karaoke));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Karaoke>> getAll() {
        return  ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }

    private void validateKaraoke (Karaoke karaoke) {
        List<Karaoke> canciones = repository.findAll();
        isAnotherOneThere(karaoke, canciones);
        isQueueBigEnough(karaoke,canciones);
    }

    private void isQueueBigEnough(Karaoke karaoke, List<Karaoke> canciones) {
        if(canciones.size() >= 30) {
            throw  new KaraokeException("La lista de cancioes de momento es muy larga porfavor vuelva a intentar mas tarde");
        }
    }

    private void isAnotherOneThere(Karaoke karaoke, List<Karaoke> canciones) {
        boolean duplicate = canciones.stream().map(Karaoke::getCancion)
                .anyMatch(cancion -> cancion.equalsIgnoreCase(karaoke.getCancion()));
        if(duplicate) {
            throw  new KaraokeException(karaoke.getCancion() + " ya fue pedida por alguien mas y se encuentra en la lista de canciones");
        }
    }
}
