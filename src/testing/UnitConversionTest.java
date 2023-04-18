package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import recipes.Unit;
import utilities.UnitConversion;

/**
 * Test cases for the UnitConversion utility class.
 * 
 * @author Alex Pegram, KichIntel
 *
 */
class UnitConversionTest
{

  @Test
  void massNorm_tests()
  {
    assertEquals(0.0625, UnitConversion.convert("Apple", Unit.OUNCE, Unit.POUND, 1));
    double trunc = UnitConversion.convert("Apple",Unit.DRAM, Unit.GRAM, 1);
    trunc = Math.floor(trunc * 100000) / 100000;
    assertEquals(1.77184, trunc);
    assertEquals(768.0, UnitConversion.convert("Apple", Unit.POUND, Unit.DRAM, 3));
    double trunc2 = UnitConversion.convert("Apple", Unit.POUND, Unit.GRAM, 3);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(1360.77, trunc2);
  }
  @Test
  void volumeNorm_tests() {
    assertEquals(96.0, UnitConversion.convert("Apple",Unit.CUP, Unit.TEASPOON, 2));
    assertEquals(.0625, UnitConversion.convert("Apple",Unit.CUP, Unit.GALLON, 1));
    assertEquals(8, UnitConversion.convert("Apple",Unit.CUP, Unit.FLUID_OUNCE, 1)); 

  }
  @Test
  void specialCase_tests() {
    
    double trunc = UnitConversion.convert("Chicken",Unit.GRAM, Unit.MILLILITER, 10);
    trunc = Math.floor(trunc * 100) / 100;
    assertEquals(9.61, trunc);
    double trunc1 = UnitConversion.convert("Chicken",Unit.CUP, Unit.OUNCE, 1);
    trunc1 = Math.floor(trunc1 * 100) / 100;
    assertEquals(8.67, trunc1);

    assertEquals(0.0,  UnitConversion.convert("Alabama", Unit.POUND, Unit.CUP, 1.0));
    assertEquals(0.0,  UnitConversion.convert("Alabama", Unit.CUP, Unit.POUND, 1.0));
  }
  @Test
  void toMilliliters_tests() {

    double trunc = UnitConversion.convert("Apple",Unit.CUP, Unit.MILLILITER, 1);
    trunc = Math.floor(trunc * 100) / 100;
    assertEquals(236.58, trunc);
    double trunc1 = UnitConversion.convert("Apple",Unit.FLUID_OUNCE, Unit.MILLILITER, 1);
    trunc1 = Math.floor(trunc1 * 100) / 100;
    assertEquals(29.57, trunc1);
    double trunc2 = UnitConversion.convert("Apple",Unit.TABLESPOON, Unit.MILLILITER, 1);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(14.78, trunc2);
    double trunc3 = UnitConversion.convert("Apple",Unit.PINCH, Unit.MILLILITER, 1);
    trunc3 = Math.floor(trunc3 * 1000) / 1000;
    assertEquals(0.308, trunc3);
    double trunc4 = UnitConversion.convert("Apple",Unit.GALLON, Unit.MILLILITER, 1);
    trunc4 = Math.floor(trunc4 * 100) / 100;
    assertEquals(3785.41, trunc4);
  }
  @Test
  void fromMilliliters_test() {
    //Tests from Milliliters

    double trunc = UnitConversion.convert("Apple",Unit.MILLILITER, Unit.CUP, 20);
    trunc = Math.floor(trunc * 10000) / 10000;
    assertEquals(.0845, trunc);

    double trunc1 = UnitConversion.convert("Apple",Unit.MILLILITER, Unit.FLUID_OUNCE, 1);
    trunc1 = Math.floor(trunc1 * 1000) / 1000;
    assertEquals(.033, trunc1);
    double trunc2 = UnitConversion.convert("Apple",Unit.MILLILITER, Unit.TABLESPOON, 1);
    trunc2 = Math.floor(trunc2 * 1000) / 1000;
    assertEquals(0.067, trunc2);
    double trunc3 = UnitConversion.convert("Apple",Unit.MILLILITER, Unit.TEASPOON, 1);
    trunc3 = Math.floor(trunc3 * 1000) / 1000;
    assertEquals(.202, trunc3);
    double trunc4 = UnitConversion.convert("Apple",Unit.MILLILITER, Unit.GALLON, 50);
    trunc4 = Math.floor(trunc4 * 10000) / 10000;
    assertEquals(.0132, trunc4);
    
  }
}
  
