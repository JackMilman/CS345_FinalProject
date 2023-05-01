package testing.guiTests;

import org.junit.jupiter.api.Test;

import gui.CalorieCalculatorWindow;

class CalorieCalculatorTest {

  @Test
  void coverageTest()
  {
    CalorieCalculatorWindow.getCalorieCalculatorWindow();
  }
  
  public static void openWindow() {
    CalorieCalculatorWindow.getCalorieCalculatorWindow();
  }
  
  public static void main(String[] args) {
    openWindow();
  }
}
