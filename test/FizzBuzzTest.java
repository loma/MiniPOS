/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author loma
 */
public class FizzBuzzTest {
    FizzBuzz fb = new FizzBuzz();

    @Test
    public void testNumberZero() {
        Assert.assertEquals("0", fb.get(0));
    }
    @Test
    public void testNumberOne() {
        Assert.assertEquals("1", fb.get(1));
    }
    @Test
    public void testNumberThree() {
        Assert.assertEquals("Fizz", fb.get(3));
    }
    @Test
    public void testNumberFive() {
        Assert.assertEquals("Buzz", fb.get(5));
    }
    @Test
    public void testNumber15() {
        Assert.assertEquals("FizzBuzz", fb.get(15));
    }
}
