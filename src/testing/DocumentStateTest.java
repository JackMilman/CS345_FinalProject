package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.DocumentState;
import gui.StepEditor;

class DocumentStateTest
{

  @Test
  void test()
  {
    DocumentState[] values = DocumentState.values();
    assertEquals(3, values.length);
  }

}
