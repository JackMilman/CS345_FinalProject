package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import utilities.Check;

class CheckTest
{

  @Test
  void forContainsTest()
  {
    String[] haystack = {"Horse","Cow","Dog","Cat"};
    assertTrue(Check.forContains(haystack, "Horse"));
    assertTrue(Check.forContains(haystack, "Cow"));
    assertTrue(Check.forContains(haystack, "Dog"));
    assertTrue(Check.forContains(haystack, "Cat"));
    
    assertFalse(Check.forContains(haystack, "Aardvark"));
    assertFalse(Check.forContains(haystack, "HORSE"));

  }

  @Test
  void forContainsTest_empty()
  {
    String[] haystack = {};
    assertFalse(Check.forContains(haystack, "Aardvark"));
  }

  @Test
  void forContainsTest_null()
  {
    assertFalse(Check.forContains((List<String>) null, "Aardvark"));
    assertFalse(Check.forContains((Iterable) null, "Aardvark"));
    
    assertFalse(Check.forContains((List<String>) null, null));
    assertFalse(Check.forContains((Iterable) null, null));
  }
}
