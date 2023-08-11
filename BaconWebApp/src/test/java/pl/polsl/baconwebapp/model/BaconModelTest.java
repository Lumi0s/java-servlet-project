/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.baconwebapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Mateusz Kies
 */
public class BaconModelTest {
    
    BaconModel testModel = new BaconModel();
  
    /**
     * Test of encode method, of class BaconModel.
     */
    @Test
    public void testEncode() 
    {
        assertEquals("lorem iPSUM dOLOR sIt AMet, Con",testModel.encode("apple"),"Encryption is wrong");
    }

    /**
     * Parametrized test of encode method, of class BaconModel.
     * @param input the inputs being 
     * @param expected the expected outcomes
     */
    @ParameterizedTest
    @CsvSource(value = {"banana:loreM ipsum dOLoR sit ameT, CoNsecte", "cherry:lorEm ipSUM doLor Sit aMEt, coNSEcte", "orange:lOREm IpsuM dolor sIT aMet, COnseCte"}, delimiter = ':')
    public void parametrizedTestEncode(String input, String expected) {
        assertEquals(expected, testModel.encode(input));
    }
        
    /**
     * Test of decode method, of class BaconModel.
     */
    @Test
    public void testDecode()
    {
        assertEquals("apple",testModel.decode("lorem iPSUM dOLOR sIt AMet, Con"),"Decryption is wrong");
    }

     /**
     * Parametrized test of decode method, of class BaconModel.
     * @param input the inputs being 
     * @param expected the expected outcomes
     */
    @ParameterizedTest
    @CsvSource(value = {"loreM ipsum dOLoR sit ameT, CoNsecte:banana", "lorEm ipSUM doLor Sit aMEt, coNSEcte:cherry", "lOREm IpsuM dolor sIT aMet, COnseCte:orange"}, delimiter = ':')
    public void parametrizedTestDecode(String input, String expected) {
        assertEquals(expected, testModel.decode(input));
    }
    
    /**
     * Test of validateData method, of class BaconModel.
     */
    @Test
    public void testValidateData() 
    {
       assertEquals(true,testModel.validateData("lorem iPSUM dOLOR sIt AMet, Con"),"Data validation is wrong");
       assertEquals(false,testModel.validateData("lorem iPSUM dOLOR sIt AMet, C"),"Data validation is wrong");
    }
    
}
