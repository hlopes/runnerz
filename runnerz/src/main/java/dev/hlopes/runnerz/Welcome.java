package dev.hlopes.runnerz;

import org.springframework.stereotype.Component;

@Component
public class Welcome {

    public String getWelcome() {
        return "Welcome test";
    }
}
