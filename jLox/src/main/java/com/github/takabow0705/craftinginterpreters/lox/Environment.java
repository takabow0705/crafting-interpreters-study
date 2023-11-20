package com.github.takabow0705.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Environment() {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    Object get(Token name) {
        if (values.containsKey(name.lexme)) {
            return values.get(name.lexme);
        }

        if (enclosing != null) return enclosing.get(name);
        throw new RuntimeError(name, "Undefined variable '" + name.lexme + "'.");
    }

    public void assign(Token name, Object value) {
        if (values.containsKey(name.lexme)) {
            values.put(name.lexme, value);
            return;
        }
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }
        throw new RuntimeError(name, "Undefined variable '" + name.lexme + "'.");
    }
}
