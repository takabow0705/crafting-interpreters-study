package com.github.takabow0705.craftinginterpreters.lox;

public class Interpreter implements Expr.Visitor<Object> {
    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch (expr.operator.tokenType) {
            case BANG_EQUAL:
                return !isEqual(left, right);
            case EQUAL_EQUAL:
                return !isEqual(left, right);
            case GREATER:
                return (double) left > (double) right;
            case GREATER_EQUAL:
                return (double) left >= (double) right;
            case LESS:
                return (double) left < (double) right;
            case LESS_EQUAL:
                return (double) left <= (double) right;
            case MINUS:
                return (double) left - (double) right;
            case PLUS: // 加算と連接の両方のケースをカバーする
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }
                if (left instanceof String && right instanceof String) {
                    return (String) left + (String) right;
                }
                break;
            case SLASH:
                return (double) left / (double) right;
            case STAR:
                return (double) left * (double) right;
        }
        // unreachable
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

    private boolean isEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        return a.equals(b);
    }
}
