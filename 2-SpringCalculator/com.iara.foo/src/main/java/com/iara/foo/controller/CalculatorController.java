package com.iara.foo.controller;

import com.iara.foo.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService service;

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
            throws Exception {

        return service.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
            throws Exception {
        return service.subtraction(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo)
            throws Exception {
        return service.multiplication(numberOne, numberTwo);
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
            throws Exception {
        return service.division(numberOne, numberTwo);
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
            throws Exception {
        return service.average(numberOne, numberTwo);
    }

    @RequestMapping(value = "/sqrt/{numberOne}", method = RequestMethod.GET)
    public Double sqrt(@PathVariable("numberOne") String numberOne)
            throws Exception {
        return service.sqrt(numberOne);
    }
}
