package com.welfare.Smile;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MyClass {

    @GetMapping("/")
    public String abc() {
        return "Hello";
    }

    @GetMapping("/a")
    public String abc1() {
        return "Hello1";
    }

    @GetMapping("/a1")
    public String abc2() {
        return "Hello2";
    }

    @GetMapping("/a2")
    public String abc3() {
        return "Hello3";
    }

    @GetMapping("/a3")
    public String abc4() {
        return "Hello4";
    }

        private Dog dog = new Dog();

        @GetMapping("/ok1")
        public String ok11() {
            return dog.fun();

        }
}
