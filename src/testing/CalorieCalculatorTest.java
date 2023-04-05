package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.CalorieCalculatorWindow;

class CalorieCalculatorTest {
	
	public static void test() {
		CalorieCalculatorWindow calc = CalorieCalculatorWindow.createInstance();
	}
	
	public static void main(String[] args) {
		test();
	}

}
