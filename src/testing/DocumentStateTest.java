package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gui.DocumentState;

class DocumentStateTest
{

  @Test
  void test()
  {
    DocumentState[] values = DocumentState.values();
    assertEquals(3, values.length);
  }

}
