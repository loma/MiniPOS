/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Product.Product;
import Product.ProductType;
import Product.SQLGenerator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author loma
 */
public class SQLGeneratorTest {
    
    public SQLGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void insertProductSQL() {
        // prepare
        Product p = new Product(ProductType.PRODUCT);
        SQLGenerator sqlGenerator = new SQLGenerator(p);

        // execute
        String sqlStatement = sqlGenerator.getInsertSQL();

        // assert
        Assert.assertEquals("insert into products (id, name, price, purchased_price, quantity) values('null', 'null', 0.000000, 0.000000, 0);", sqlStatement);
    }

    @Test
    public void insertSaleProductSQL() {
        // prepare
        Product p = new Product(ProductType.SALE);
        SQLGenerator sqlGenerator = new SQLGenerator(p);

        // execute
        String sqlStatement = sqlGenerator.getInsertSQL();

        // assert
        Assert.assertEquals("insert into sale_details (sale_id, product_id, quantity, price) values(0, 'null', 1, 0.000000);", sqlStatement);
    }
}
