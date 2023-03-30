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
    assertEquals(0.0625, x.conversion("OUNCES", "POUND", 1));
    double trunc = x.conversion("DRAMS", "GRAMS", 1);
    trunc = Math.floor(trunc * 100000) / 100000;
    assertEquals(1.77184, trunc);
    assertEquals(768.0, x.conversion("POUND", "DRAMS", 3));
    double trunc2 = x.conversion("POUND", "GRAMS", 3);
    trunc2 = Math.floor(trunc2 * 100) / 100;
    assertEquals(1360.77, trunc2);
  }
  

}
