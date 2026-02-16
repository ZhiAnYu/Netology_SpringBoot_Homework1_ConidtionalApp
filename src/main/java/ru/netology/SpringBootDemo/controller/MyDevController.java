package ru.netology.SpringBootDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.SpringBootDemo.domain.Person;

@RestController
// 1 вариант:
//@ConfigurationProperties("hello")

public class MyDevController {
    @Value("${hello.from:Anonymous}")
    private String from;

// для 1-го варианта необходим сеттер для Spring чтобы установить поле
//    public void setFrom(String from) {
//        this.from = from;
//    }

    @PostMapping("/hello")
    private String hello(@RequestBody Person guest) {
        return String.format("Hello from %s to name %s age %d!", from,
                guest.getName(),
                guest.getAge());
    }
}
