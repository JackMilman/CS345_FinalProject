package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import recipes.Utensil;

/**
 * Test cases for the Utensil class.
 * 
 * @author Jack Milman, KichIntel
 *
 */
class UtensilTest
{

  private final String basicName = "name";
  private final String quirkyName = "extraName";

  private final String basicDetails = "details";
  private final String quirkyDetails = "extraDetails";

  @Test
  public void testGetName()
  {
    String expected = basicName;
    Utensil utensil = new Utensil(basicName, basicDetails);
    String actual = utensil.getName();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDetails()
  {
    String expected = basicDetails;
    Utensil utensil = new Utensil(basicName, basicDetails);
    String actual = utensil.getDetails();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetName()
  {
    String expected = quirkyName;
    Utensil utensil = new Utensil(basicName, basicDetails);
    utensil.setName(quirkyName);
    String actual = utensil.getName();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetDetails()
  {
    String expected = quirkyDetails;
    Utensil utensil = new Utensil(basicName, basicDetails);
    utensil.setDetails(quirkyDetails);
    String actual = utensil.getDetails();
    assertEquals(expected, actual);
  }

  @Test
  public void testEquals()
  {
    Utensil utensil = new Utensil(basicName, basicDetails);
    Utensil utensilTotallyDifferent = new Utensil(quirkyName, quirkyDetails);
    Utensil utensilSameNameDifferentDetails = new Utensil(quirkyName, basicDetails);
    Utensil utensilSameDetailsDifferentName = new Utensil(basicName, quirkyDetails);
    Utensil utensilTotallySame = new Utensil(basicName, basicDetails);

    assertEquals(utensil, utensilTotallySame);
    assertFalse(utensil.equals(utensilTotallyDifferent));
    assertFalse(utensil.equals(utensilSameNameDifferentDetails));
    assertFalse(utensil.equals(utensilSameDetailsDifferentName));
  }

  @Test
  public void testHashCode()
  {
    Utensil utensil = new Utensil(basicName, basicDetails);
    try
    {
      utensil.hashCode();
      // Should not reach this line
      fail();
    }
    catch (UnsupportedOperationException uoe)
    {
      // Want to get here!
    }
  }

}
