package com.luv2code.springdemo.mvc;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/custom")
public class CustomerController {


    //add an initbinder to convert trim input strings
    //remove leading and trailing whitespace
    //resolve issue for our validation

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping("/showForm")
    public String showForm(Model theModel){

        theModel.addAttribute("customer", new Customer());
        return "customer-form";
    }
    @RequestMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindResult){

        System.out.println("Bind result: " + theBindResult);
        System.out.println("\n\n\n\n");
        if(theBindResult.hasErrors()){
            return "customer-form";
        }
        return "customer-confirmation";
    }
}
