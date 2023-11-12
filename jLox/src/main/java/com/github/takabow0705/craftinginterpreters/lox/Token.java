package com.github.takabow0705.craftinginterpreters.lox;

public class Token {
    final TokenType tokenType;
    final String lexme;
    final Object literal;
    final int line;

    public Token(TokenType tokenType, String lexme, Object literal, int line) {
        this.tokenType = tokenType;
        this.lexme = lexme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return tokenType + " " + lexme + " " + literal;
    }
}
