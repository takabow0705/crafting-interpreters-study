package com.github.takabow0705.craftinginterpreters.lox;

abstract class Expr {
    static class Binary extends Expr {
        Binary(Expr left, Token token, Expr right) {
            this.left = left;
            this.token = token;
            this.right = right;
        }

        final Expr left;
        final Token token;
        final Expr right;
    }

}
