package spring.exam.web.mvc.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MvcController.NotFoundException.class)
    public String handleNotFoundException(
            MvcController.NotFoundException notFoundException, Model model) {
        model.addAttribute("message", notFoundException.getMessage());
        return "notfound";
    }
}
