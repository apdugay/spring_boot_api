//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        ItemController itemController = new ItemController(new ItemRepository() {
            private final Map<Long, Item> store = new HashMap<>();
            private final AtomicLong idGenerator = new AtomicLong(1);


            @Override
            public List<Item> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<Item> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Item> S save(S entity) {
                if (entity.getId() == null) {
                    entity.setId(idGenerator.getAndIncrement());
                }
                store.put(entity.getId(), entity);
                return entity;
            }

            @Override
            public List<Item> findAll() {
                return new ArrayList<>(store.values());
            }

            @Override
            public <S extends Item> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public Optional<Item> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }


            @Override
            public List<Item> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Item entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Item> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Item> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Item> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteInBatch(Iterable<Item> entities) {
                ItemRepository.super.deleteInBatch(entities);
            }

            @Override
            public void deleteAllInBatch(Iterable<Item> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Item getOne(Long aLong) {
                return null;
            }

            @Override
            public Item getById(Long aLong) {
                return null;
            }

            @Override
            public Item getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Item> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Item> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends Item> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends Item> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Item> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Item> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Item, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
            // Implement the methods of ItemRepository here for testing
        });

        Item myItem = new Item();
        myItem.setName("Sample Item");

        itemController.create(myItem);

        System.out.println("Item created: " + myItem.getName());

        List<Item> items = itemController.all();

        System.out.println("num items in repository: " + items.size());
        System.out.println("item name is:" + items.get(0).getName());
    }


}