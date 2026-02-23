package ru.netology.SpringBootDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.SpringBootDemo.domain.Person;

@RestController
// 1 вариант:
//@ConfigurationProperties("hello")
@RequestMapping("/error")
// для урока по исключениям, чтоб удобнее дделать провеку по id

public class MyDevController {
    @Value("${hello.from:Anonymous}")
    private String from;

// для 1-го варианта необходим сеттер для Spring чтобы установить поле
//    public void setFrom(String from) {
//        this.from = from;
//    }

    @PostMapping("/hello")
    private String hello(@Validated @RequestBody Person guest) {
        return String.format("Hello from %s to name %s age %d!", from,
                guest.getName(),
                guest.getAge());
    }

//    @GetMapping("/error")
//    public String error() {
//        System.out.println("Logic");
//        throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, " на самом деле здесь ResponceStatusException");
//    }

    @GetMapping("/{id}")
    //ResponseStatusException  это обработчик от SpringBoot
    public Person getPersonById(@PathVariable("id") long id) {
        try {
            throw new IllegalArgumentException("IllegalArgumentException");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.IM_USED, "ResponseStatusException", e);
        }
    }

    //Для второго варианта обработки исключений используем ExceptionHandler
    //име
    @GetMapping("/iae")

    public Person throwIae() {
        throw new IllegalArgumentException("throwIae");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> iaeHandler(IllegalArgumentException e) {
        //логика - закрыть файлы , завершить транзакции и прочее
        return new ResponseEntity<>("Exception in throwException method", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> reHandler() {
        return new ResponseEntity<>("Exception in throwException method", HttpStatus.I_AM_A_TEAPOT);
    }
}
