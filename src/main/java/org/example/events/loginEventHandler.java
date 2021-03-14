package org.example.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class loginEventHandler implements ApplicationListener<loginEvent> {


    @Override
    public void onApplicationEvent(loginEvent loginEvent) {
        System.out.println("User with name: " + loginEvent.getName() + " logged in");
    }
}
