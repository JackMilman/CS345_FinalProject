package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import recipes.Unit;

class UnitTest
{
  private final String cupName = "Cup";
  private final String gallonName = "Gallon";
  private final String literName = "Liter";

  @Test
  public void testGetName()
  {
    assertEquals(cupName, Unit.CUP.getName());
    assertEquals(gallonName, Unit.GALLON.getName());
    assertEquals(literName, Unit.LITER.getName());
  }

  @Test
  public void testParseUnit()
  {
    assertEquals(Unit.CUP, Unit.parseUnit(cupName));
    assertEquals(Unit.GALLON, Unit.parseUnit(gallonName));
    assertEquals(Unit.LITER, Unit.parseUnit(literName));
    assertEquals(null, Unit.parseUnit("Not a unit"));
    assertEquals(null, Unit.parseUnit(null));
  }

}
