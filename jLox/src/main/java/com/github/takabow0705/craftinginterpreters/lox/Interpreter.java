package com.github.takabow0705.craftinginterpreters.lox;

public class Interpreter implements Expr.Visitor<Object> {
    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.tokenType) {
            case MINUS:
                return -(double) right;
            case BANG:
                return !isTruthy(right);
        }
        // unreachable
        return null;
    }

    private boolean isTruthy(Object o) {
        if (o == null) return false; // nullは常にfalseのブール値として扱う
        if (o instanceof Boolean) return (boolean) o;
        return true;
    }

    private Object evaluate(Expr expression) {
        return expression.accept(this);
    }

}
