package com.github.takabow0705.craftinginterpreters.lox;

class LoxClass {
    final String name;

    LoxClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
