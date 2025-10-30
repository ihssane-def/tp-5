package test;

import entities.Category;
import entities.Product;
import metier.ProductDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import util.HibernateConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(HibernateConfig.class)
@Transactional
@Rollback
public class ProductDaoTest {

    @Autowired
    private ProductDaoImpl productDao;

    @Test
    public void testCreateProduct() {
        Category category = new Category("Informatique");
        Product product = new Product("Ordinateur", 8000.0, category);

        boolean created = productDao.create(product);
        assertTrue(created, "Le produit doit être créé avec succès");
        assertNotNull(product.getId());
    }
}
