package com.github.takabow0705.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

public class LoxInstance {
    private LoxClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    public LoxInstance(LoxClass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }

    public Object get(Token name) {
        if (fields.containsKey(name.lexme)) {
            return fields.get(name.lexme);
        }
        throw new RuntimeError(name, "Undefined property '" + name.lexme + "'.");
    }
}
