package com.noideaindustry.app;

import com.noideaindustry.isartbot.IsartBot;

public class Application {
    private final IsartBot isartBot;

    public Application() {
        this.isartBot = new IsartBot();
    }

    public IsartBot getIsartBot() {
        return this.isartBot;
    }

    private static Application INSTANCE;

    public static Application getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        INSTANCE = new Application();
    }
}