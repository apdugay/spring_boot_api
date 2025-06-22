package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Item> all() {
        return repository.findAll();
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return repository.save(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
