package org.example.events;

import org.springframework.context.ApplicationEvent;

public class loginEvent extends ApplicationEvent {

    private String name;

    public loginEvent(Object source, String name) {
        super(source);
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
