package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utilities.UnitConversion;

class UnitConversionTest
{

  @Test
  void massNorm_tests()
  {
    UnitConversion x = new UnitConversion();
    assertEquals(0.0625, x.convert("OUNCE", "POUND", 1));
    double trunc = x.convert("DRAM", "GRAM", 1);
    trunc = Math.floor(trunc * 100000) / 100000;
    assertEquals(1.77184, trunc);
    assertEquals(768.0, x.convert("POUND", "DRAM", 3));
    double trunc2 = x.convert("POUND", "GRAM", 3);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(1360.77, trunc2);
  }
  @Test
  void volumeNorm_tests() {
    UnitConversion x = new UnitConversion();
    assertEquals(96.0, x.convert("CUP", "TEASPOON", 2));
    assertEquals(.0625, x.convert("CUP", "GALLON", 1));
    assertEquals(8, x.convert("CUP", "FLUID_OUNCE", 1)); 
  }
  @Test
  void specialCase_tests() {
    //Mismatch
    UnitConversion x = new UnitConversion("Chicken");
    
    double trunc = x.convert("GRAM", "MILLILITER", 10);
    trunc = Math.floor(trunc * 100) / 100;
    assertEquals(9.61, trunc);
    double trunc1 = x.convert("CUP", "OUNCE", 1);
    trunc1 = Math.floor(trunc1 * 100) / 100;
    assertEquals(8.67, trunc1);
    UnitConversion y = new UnitConversion("INVALID_INGREDIENT");
    assertEquals(0.0,  y.mass_to_volume("POUND", "CUP", 1.0));
    assertEquals(0.0,  y.mass_to_volume("CUP", "POUND", 1.0));
  }
  @Test
  void toMilliliters_tests() {
    UnitConversion x = new UnitConversion();
    double trunc = x.convert("CUP", "MILLILITER", 1);
    trunc = Math.floor(trunc * 100) / 100;
    assertEquals(236.58, trunc);
    double trunc1 = x.convert("FLUID_OUNCE", "MILLILITER", 1);
    trunc1 = Math.floor(trunc1 * 100) / 100;
    assertEquals(29.57, trunc1);
    double trunc2 = x.convert("TABLESPOON", "MILLILITER", 1);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(14.78, trunc2);
    double trunc3 = x.convert("PINCH", "MILLILITER", 1);
    trunc3 = Math.floor(trunc3 * 1000) / 1000;
    assertEquals(0.308, trunc3);
    double trunc4 = x.convert("GALLON", "MILLILITER", 1);
    trunc4 = Math.floor(trunc4 * 100) / 100;
    assertEquals(3785.41, trunc4);
  }
  @Test
  void fromMilliliters_test() {
    //Tests from Milliliters
  UnitConversion x = new UnitConversion();
    double trunc = x.convert("MILLILITER", "CUP", 20);
    trunc = Math.floor(trunc * 10000) / 10000;
    assertEquals(.0845, trunc);
    double trunc1 = x.convert("MILLILITER", "FLUID_OUNCE", 1);
    trunc1 = Math.floor(trunc1 * 1000) / 1000;
    assertEquals(.033, trunc1);
    double trunc2 = x.convert("MILLILITER", "TABLESPOON", 1);
    trunc2 = Math.floor(trunc2 * 1000) / 1000;
    assertEquals(0.067, trunc2);
    double trunc3 = x.convert("MILLILITER", "TEASPOON", 1);
    trunc3 = Math.floor(trunc3 * 1000) / 1000;
    assertEquals(.202, trunc3);
    double trunc4 = x.convert("MILLILITER", "GALLON", 50);
    trunc4 = Math.floor(trunc4 * 10000) / 10000;
    assertEquals(.0132, trunc4);
    
  }
}
  
