///-----------------------------------------------------------------------///
/// @file Calculator.java                                                 ///
/// @brief Contains the implementation of Calculator                      ///
///-----------------------------------------------------------------------///
/// @copyright (c) 2021 by Roman Berngardt. All rights are absolutely not ///
/// reserved.                                                             ///
///                                                                       ///
/// PLEASE FEEL FREE TO ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE    ///
/// HEADER AT ALL.                                                        ///
///                                                                       ///
/// This code is free software; you can redistribute it and/or modify it  ///
/// without any restrictions.                                             ///
///                                                                       ///
/// This code is distributed as a part of home work (task 2.9.x) in the   ///
/// hope that it will work correctly and will be useful, but WITHOUT ANY  ///
/// WARRANTY; without even the implied warranty of MERCHANTABILITY or     ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2021-10-20                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The {@code Calculator} class contains implementation of
 * a simple calculator as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Calculator {

    /**
     * Constant which has to be used as tolerance value
     * during comparison of float values against zero
     * (as the float values can not exactly equal to zero) */
    private final float EPS = 0.000_000_001F;

    /**
     * The current result of the calculations as well
     * as the first operand at the next operation. */
    private float currentResult = 0.0F;

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        Calculator calculator = Calculator.getInstance();

        calculator.printBanner();
        calculator.performCalculations();
        calculator.sayGoodBye();
    }

    /**
     * @brief Get the instance of this class
     * @return The instance of the Calculator-class */
    public static Calculator getInstance() {
        return INSTANCE_OF_ME;
    }

    /**
     * @brief Preforms all the calculations */
    private void performCalculations() {
        do {
            System.out.println("ТЕКУЩИЙ РЕЗУЛЬТАТ ВЫЧИСЛЕНИЙ: " + currentResult);
            Operation currentOperation = promptAndReadOperation();
            if (Operation.CLR == currentOperation) {
                // Got the CLR opcode -> reset and continue
                currentResult = 0.0F;
                continue;
            } else if (Operation.SD == currentOperation) {
                // Got the ShutDown opcode -> hold on!
                break;
            } else {
                // Got something else -> continue checking...
                float op2 = promptAndReadValue(); // read the value of the next operand

                // Check, what has to be done...
                switch (currentOperation) {
                    case ADD: // Got the ADD opcode -> perform addition
                        currentResult += op2;
                        break;

                    case SUB: // Got the SUB opcode -> perform subtraction
                        currentResult -= op2;
                        break;

                    case MUL: // Got the MUL opcode -> perform multiplication
                        currentResult *= op2;
                        break;

                    case DIV: // Got the DIV opcode -> perform division
                        if ((op2 >= -1.0 * EPS) & (op2 <= EPS)) {
                            // The second operand has a value of zero -> reject!
                            System.out.println("ОШИБКА: На ноль делить нельзя!");
                            continue;
                        } else {
                            // Everything seems to be fine -> perform division
                            currentResult /= op2;
                        }
                        break;

                    default: // Unexpected value -> Something seems to went really wrong!
                        System.out.println("ОШИБКА: Неизвестная операция!");
                        break;
                }
            }
        } while (true);
    }

    /**
     * @brief Prompt and read next operation code
     * @return Operation code
     */
    private Operation promptAndReadOperation() {
        boolean succeded = false;
        Operation returnValue = Operation.INVALID;
        while (!succeded) {
            System.out.print("Введите операцию [+, -, *, /, s, c]: ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();
            if (userInput.length() == 1) {
                char opCode = userInput.toUpperCase().charAt(0);
                switch (opCode) {
                    case 'S':
                        returnValue = Operation.SD;
                        succeded = true;
                        break;
                    case 'C':
                        returnValue = Operation.CLR;
                        succeded = true;
                        break;
                    case '+':
                        returnValue = Operation.ADD;
                        succeded = true;
                        break;
                    case '-':
                        returnValue = Operation.SUB;
                        succeded = true;
                        break;
                    case '*':
                        returnValue = Operation.MUL;
                        succeded = true;
                        break;
                    case '/':
                        returnValue = Operation.DIV;
                        succeded = true;
                        break;
                    default:
                        System.out.println("ОШИБКА: Операция '" + opCode + "' не поддерживается!");
                        break;
                }
            } else {
                System.out.println("ОШИБКА: Некорректная длинна операции! Введите только один символ.");
            }
        }
        return returnValue;
    }

    /**
     * @brief Prompt and read value of the next operand.
     * @return Float value of the next operand. */
    private float promptAndReadValue() {
        boolean succeded = false;
        float returnValue = 0.0F;

        while (!succeded) {
            System.out.print("Введите операнд: ");
            try {
                Scanner scanner = new Scanner(System.in);
                returnValue = scanner.nextFloat();
                succeded = true;
            } catch (InputMismatchException ime) {
                System.out.println("ОШИБКА: Это не число! Попробуйте ещё раз.");
            }
        }

        return returnValue;
    }

    /**
     * @brief Just a bit more courtesy. :) */
    private void sayGoodBye() {
        System.out.print(
                "\r\nСпасибо, что воспользовались этим замечательным иструментом!\r\n" +
                        "До новых встречь!\r\n\n"
        );
    }

    /**
     * @brief Print the name of the program and some version information */
    private void printBanner() {
        System.out.print(
                "\r\n**********************************************************************\r\n" +
                        "*                    \"БЕСКОНЕЧНЫЙ\" КАЛЬКУЛЯТОР                       *\r\n" +
                        "*--------------------------------------------------------------------*\r\n" +
                        "*   ВЕРСИЯ: 1.0              Copyright (C) 2021 by Roman Berngardt   *\r\n" +
                        "**********************************************************************\r\n\n"
        );
    }

    /**
     * @brief Possible operation codes */
    private enum Operation {ADD, SUB, MUL, DIV, CLR, SD, INVALID}

    /**
     * @brief Private constructor of the class.
     *        Don't let anyone from outside to instantiate this class */
    private Calculator() {}

    /**
     * @brief Create private instance of the class */
    private static final Calculator INSTANCE_OF_ME = new Calculator();
}
