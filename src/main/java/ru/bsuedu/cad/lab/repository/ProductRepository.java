package ru.bsuedu.cad.lab.repository;
import org.springframework.data.repository.CrudRepository;
import ru.bsuedu.cad.lab.entity.Product;
public interface ProductRepository extends CrudRepository<Product, Integer> {}