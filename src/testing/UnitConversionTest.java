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
    assertEquals(0.0625, x.conversion("OUNCE", "POUND", 1));
    double trunc = x.conversion("DRAM", "GRAM", 1);
    trunc = Math.floor(trunc * 100000) / 100000;
    assertEquals(1.77184, trunc);
    assertEquals(768.0, x.conversion("POUND", "DRAM", 3));
    double trunc2 = x.conversion("POUND", "GRAM", 3);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(1360.77, trunc2);
  }
  @Test
  void volumeNorm_tests() {
    UnitConversion x = new UnitConversion();
    assertEquals(96.0, x.conversion("CUP", "TEASPOON", 2));
    assertEquals(.0625, x.conversion("CUP", "GALLON", 1));
    assertEquals(0, x.conversion("CUP", "DRAM", 0));
    assertEquals(8, x.conversion("CUP", "FLUID_OUNCE", 1)); 
  }
  @Test
  void specialCase_tests() {
    //Mismatch
    UnitConversion x = new UnitConversion();
    assertEquals(0.0, x.conversion("CUP", "GRAM", 2));
  }
  @Test
  void toMilliliters_tests() {
    UnitConversion x = new UnitConversion();
    double trunc = x.conversion("CUP", "MILLILITER", 1);
    trunc = Math.floor(trunc * 100) / 100;
    assertEquals(236.58, trunc);
    double trunc1 = x.conversion("FLUID_OUNCE", "MILLILITER", 1);
    trunc1 = Math.floor(trunc1 * 100) / 100;
    assertEquals(29.57, trunc1);
    double trunc2 = x.conversion("TABLESPOON", "MILLILITER", 1);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(14.78, trunc2);
    double trunc3 = x.conversion("PINCH", "MILLILITER", 1);
    trunc3 = Math.floor(trunc3 * 1000) / 1000;
    assertEquals(0.308, trunc3);
    double trunc4 = x.conversion("GALLON", "MILLILITER", 1);
    trunc4 = Math.floor(trunc4 * 100) / 100;
    assertEquals(3785.41, trunc4);
  }
  @Test
  void fromMilliliters_test() {
    //Tests from Milliliters
  UnitConversion x = new UnitConversion();
    double trunc = x.conversion("MILLILITER", "CUP", 20);
    trunc = Math.floor(trunc * 10000) / 10000;
    assertEquals(.0845, trunc);
    double trunc1 = x.conversion("MILLILITER", "FLUID_OUNCE", 1);
    trunc1 = Math.floor(trunc1 * 1000) / 1000;
    assertEquals(.033, trunc1);
    double trunc2 = x.conversion("MILLILITER", "TABLESPOON", 1);
    trunc2 = Math.floor(trunc2 * 1000) / 1000;
    assertEquals(0.067, trunc2);
    double trunc3 = x.conversion("MILLILITER", "TEASPOON", 1);
    trunc3 = Math.floor(trunc3 * 1000) / 1000;
    assertEquals(.202, trunc3);
    double trunc4 = x.conversion("MILLILITER", "GALLON", 50);
    trunc4 = Math.floor(trunc4 * 10000) / 10000;
    assertEquals(.0132, trunc4);
    
  }
}
  
