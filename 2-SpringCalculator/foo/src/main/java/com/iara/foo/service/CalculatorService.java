package com.iara.foo.service;

import com.iara.foo.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Double sum(String numberOne, String numberTwo)
            throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, type a numeric value!");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber != null && isNumeric(strNumber)) {
            String number = strNumber.replaceAll(",", ".");
            return Double.valueOf(number);
        }

        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        try {
            Double.parseDouble(strNumber.replaceAll(",", "."));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Double subtraction(String numberOne, String numberTwo)
            throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, type a numeric value!");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    public Double multiplication(String numberOne, String numberTwo)
            throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, type a numeric value!");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public Double division(String numberOne, String numberTwo)
            throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, type a numeric value!");
        }
        if (convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Please, divisor cannot be 0!");
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public Double average(String numberOne, String numberTwo)
            throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, type a numeric value!");
        }

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    public Double sqrt(String numberOne)
            throws Exception {
        if (!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please, type a numeric value!");
        }

        return Math.sqrt(Double.parseDouble(numberOne));
    }
}
