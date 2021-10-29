package frontend;

import AST.*;
import util.*;
import util.error.semanticError;

import java.util.Objects;

public class SemanticChecker implements ASTvisitor {
    public scope globalScope, nowScope;

    //For returnStmt
    public Type returnType, lambdaType;
    public boolean hasReturn = false;
    public boolean lambdaReturn = false;

    //For circulatory - record the number of cycle layers
    public int depth;

    //For "this" - if the current position in a class and record the information of the class
    public classType nowClass = null;

    public SemanticChecker(scope _globalScope) {
        depth = 0;
        globalScope = _globalScope;
    }

    @Override
    public void visit(progNode it) {
        //enter the program
        nowScope = globalScope;

        //check main function
        funcSymbol mainFunc = nowScope.funcGet("main", false, it.pos);
        if (!mainFunc.type.isInt()) throw new semanticError("Type of Main Error", it.pos);
        if (mainFunc.paraList.size() != 0) throw new semanticError("Parameters of Main", it.pos);

        //visit
        it.List.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(typeNode it) {

    }

    @Override
    public void visit(funcDefNode it) {
        //enter the function
        nowScope = new scope(nowScope);

        //update variables with parameters
        if (it.paraList != null) it.paraList.forEach(x -> nowScope.newVar(x.id,
                new varSymbol(nowScope.typeGet(x.type.type, x.pos), x.id), x.pos));

        //record function's type
        hasReturn = false;
        if (it.type != null) returnType = nowScope.typeGet(it.id, it.pos);
        else returnType = new literalType("void");

        //visit suite
        it.suite.accept(this);

        //check RETURN sentence
        if (it.id.equals("main")) hasReturn = true;
        if (!hasReturn && it.type != null && !it.type.type.equals("void"))
            throw new semanticError("Missing Return", it.pos);

        //exit function
        nowScope = nowScope.pScope;
    }

    @Override
    public void visit(classDefNode it) {
        //enter the class
        nowScope = new scope(nowScope);

        //update variables and functions with classType
        nowClass = (classType) nowScope.typeMap.get(it.id);
        nowClass.varMap.forEach((x, y) -> nowScope.newVar(x, y, it.pos));
        nowClass.funcMap.forEach((x, y) -> nowScope.newFunc(x, y, it.pos));

        //visit functions
        it.funcList.forEach(x -> x.accept(this));

        //visit structor
        if (it.struct != null) {
            if (!it.struct.id.equals(it.id)) throw new semanticError("Structor Error", it.pos);
            it.struct.accept(this);
        }

        //exit class
        nowClass = null;
        nowScope = nowScope.pScope;
    }

    @Override
    public void visit(varDecStmtNode it) {
        //get the type of this variable
        Type tmp = nowScope.typeGet(it.id, it.pos);

        //get the type of the expression and check
        if (it.expr != null) {
            it.expr.accept(this);
            if (!it.expr.type.typeEqual(tmp)) throw new semanticError("Variety Definition Error", it.pos);
        }

        //successful definition
        nowScope.newVar(it.id, new varSymbol(tmp, it.id), it.pos);
    }

    @Override
    public void visit(varDefStmtNode it) {
        it.varList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(suiteStmtNode it) {
        it.stmtList.forEach(x -> {
            //if it's a new suite, update the scope
            if (x instanceof suiteStmtNode) {
                //enter the suite
                nowScope = new scope(nowScope);
                //visit the suite
                x.accept(this);
                //exit the suite
                nowScope = nowScope.pScope;
            } else x.accept(this);
        });
    }

    @Override
    public void visit(ifStmtNode it) {
        //check the type of expression - bool
        it.expr.accept(this);
        if (!it.expr.type.isBool()) throw new semanticError("Invalid IFCondition", it.pos);

        //enter the trueStmt
        nowScope = new scope(nowScope);

        //visit the trueStmt
        it.trueStmt.accept(this);

        //exit the trueStmt
        nowScope = nowScope.pScope;

        //if it has "else"
        if (it.falseStmt != null) {
            nowScope = new scope(nowScope);
            it.falseStmt.accept(this);
            nowScope = nowScope.pScope;
        }
    }

    @Override
    public void visit(whileStmtNode it) {
        //check the type of expression - bool
        it.expr.accept(this);
        if (!it.expr.type.isBool()) throw new semanticError("Invalid WHILEcondition", it.pos);

        //enter the circulatory body
        depth++;
        nowScope = new scope(nowScope);

        //visit the body
        it.stmt.accept(this);

        //exit the body
        depth--;
        nowScope = nowScope.pScope;
    }

    @Override
    public void visit(forStmtNode it) {
        //visit the control conditions and check the condition
        if (it.init != null) it.init.accept(this);
        if (it.cond != null) {
            it.cond.accept(this);
            if (!it.cond.type.isBool()) throw new semanticError("Invalid FORcondition", it.pos);
        }
        if (it.incr != null) it.incr.accept(this);

        //enter the circulatory body
        depth++;
        nowScope = new scope(nowScope);

        //visit the body
        it.stmt.accept(this);

        //exit the body
        depth--;
        nowScope = nowScope.pScope;

    }

    @Override
    public void visit(returnStmtNode it) {
        hasReturn = true;
        if (it.expr != null) {
            if (lambdaReturn) {
                it.expr.accept(this);
                lambdaType = it.expr.type;
            } else {
                it.expr.accept(this);
                if (!returnType.typeEqual(it.expr.type)) throw new semanticError("Return Mismatch", it.pos);
            }
        } else {
            if (lambdaReturn) {
                lambdaType = new literalType("void");
            } else {
                if (!returnType.isVoid()) throw new semanticError("return Mismatch", it.pos);
            }
        }
    }

    @Override
    public void visit(breakStmtNode it) {
        if (depth < 1) throw new semanticError("Invalid Break", it.pos);
    }

    @Override
    public void visit(continueStmtNode it) {
        if (depth < 1) throw new semanticError("Invalid Continue", it.pos);
    }

    @Override
    public void visit(pureExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(emptyStmtNode it) {

    }

    @Override
    public void visit(intExprNode it) {
        it.type = new literalType("int");
    }

    @Override
    public void visit(boolExprNode it) {
        it.type = new literalType("bool");
    }

    @Override
    public void visit(stringExprNode it) {
        it.type = new literalType("string");
    }

    @Override
    public void visit(nullExprNode it) {
        it.type = new literalType("null");
    }

    @Override
    public void visit(thisExprNode it) {
        if (nowClass == null) throw new semanticError("Invalid use of THIS", it.pos);
        it.type = nowClass;
    }

    @Override
    public void visit(idExprNode it) {
        it.type = nowScope.varGet(it.id, true, it.pos).type;
    }

    @Override
    public void visit(lambdaExprNode it) {
        //enter the function
        nowScope = new scope(nowScope);

        //check the number of parameters and expressions
        if (it.paraList.size() != it.exprList.exprList.size())
            throw new semanticError("LAMBDA error number of parameters", it.pos);

        //get the type of the expressions
        if (it.exprList.exprList.size() != 0) {
            it.exprList.exprList.forEach(x -> x.accept(this));
        }

        //update the parameters
        if (it.paraList != null) it.paraList.forEach(x -> nowScope.newVar(x.id,
                new varSymbol(nowScope.typeGet(x.type.type, x.pos), x.id), x.pos));

        //check if the parameters match the expressions
        if (it.paraList != null) {
            for (int i = 0; i < it.paraList.size(); i++) {
                if (nowScope.typeGet(it.paraList.get(i).type.type, it.paraList.get(i).pos).
                        typeEqual(it.exprList.exprList.get(i).type))
                    throw new semanticError("LAMBDA parameters dismatch expressions", it.pos);
            }
        }

        //record function's type
        hasReturn = false;
        lambdaReturn = true;

        //visit suite
        it.suite.accept(this);

        //check RETURN sentence to update the information of this expression
        if (!hasReturn) {
            it.type = new literalType("void");
        } else {
            it.type = lambdaType;
        }

        //exit function
        lambdaReturn = false;
        nowScope = nowScope.pScope;
    }

    @Override
    public void visit(funcExprNode it) {
        //update the type of the expression before the dot
        //global function
        if (it.id instanceof idExprNode) it.id.type = nowScope.funcGet(((idExprNode) it.id).id, true, it.pos);
            //class function
        else it.id.accept(this);

        //check if the first expression is a function name
        if (!(it.id.type instanceof funcSymbol)) throw new semanticError("Undefined Function", it.pos);

        //get the information of the expressions
        it.exprList.accept(this);

        //check the number of parameters and expressions
        funcSymbol tmp = (funcSymbol) it.id.type;
        if (it.exprList.exprList.size() != tmp.paraList.size())
            throw new semanticError("Error number of parameters", it.pos);

        //check if the parameters match the expressions
        for (int i = 0; i < it.exprList.exprList.size(); i++) {
            if (it.exprList.exprList.get(i).type.typeEqual(tmp.paraList.get(i).type))
                throw new semanticError("Parameters dismatch expressions", it.pos);
        }

        //update the information of this expression
        it.type = tmp.type;
    }

    @Override
    public void visit(indexExprNode it) {
        it.bas.accept(this);
        it.off.accept(this);

        //check if the bas is an array
        if (!(it.bas.type instanceof arrayType)) throw new semanticError("Undifined Array", it.pos);

        //check if the off is a integer
        if (!(it.off.type.isInt())) throw new semanticError("Invalid Offset", it.pos);

        //update information of this expression
        arrayType tmp = (arrayType) it.bas.type;
        //reduce to basicType
        if (tmp.dim == 1) it.type = tmp.type;
            //dimensionality reduction
        else it.type = new arrayType(tmp.type, tmp.dim - 1);
    }

    @Override
    public void visit(classExprNode it) {
        it.name.accept(this);

        //array built-in function [<>.size()]
        if ((it.name.type instanceof arrayType) && it.isFunc && it.id.equals("size")) {
            funcSymbol res = new funcSymbol("size");
            res.type = new literalType("int");
            it.type = res;
            return;
        }

        //string built-in function [<>.length()]
        if ((it.name.type.isString()) && it.isFunc && it.id.equals("length")) {
            funcSymbol res = new funcSymbol("length");
            res.type = new literalType("int");
            it.type = res;
            return;
        }

        //string built-in function [<>.substring(left,right)]
        if ((it.name.type.isString()) && it.isFunc && it.id.equals("substring")) {
            funcSymbol res = new funcSymbol("substring");
            res.type = new literalType("string");
            res.paraList.add(new varSymbol(new literalType("int"), "left"));
            res.paraList.add(new varSymbol(new literalType("int"), "right"));
            it.type = res;
            return;
        }

        //string built-in function [<>.paserInt()]
        if ((it.name.type.isString()) && it.isFunc && it.id.equals("paserInt")) {
            funcSymbol res = new funcSymbol("paserInt");
            res.type = new literalType("int");
            it.type = res;
            return;
        }

        //string built-in function [<>.ord(pos)]
        if ((it.name.type.isString()) && it.isFunc && it.id.equals("ord")) {
            funcSymbol res = new funcSymbol("ord");
            res.type = new literalType("int");
            res.paraList.add(new varSymbol(new literalType("int"), "pos"));
            it.type = res;
            return;
        }

        //check if the expression is a class
        if (!(it.name.type instanceof classType)) throw new semanticError("Undefine Class", it.pos);

        //update the information of this expression from the class information
        classType preClass = (classType) it.name.type;
        if (it.isFunc) {
            if (!preClass.funcMap.containsKey(it.id)) throw new semanticError("Undefine Function in Class", it.pos);
            it.type = preClass.funcMap.get(it.id);
        } else {
            if (!preClass.varMap.containsKey(it.id)) throw new semanticError("Undefine Variable in Class", it.pos);
            it.type = preClass.varMap.get(it.id).type;
        }
    }

    @Override
    public void visit(sufExprNode it) {
        it.expr.accept(this);

        //'++'/'--' check the type of the expression - int
        if (!it.expr.type.isInt())
            throw new semanticError("Error SuffixExpression", it.pos);

        //check if the expression can be left value
        if (!it.expr.assign)
            throw new semanticError("Error Assign", it.pos);

        it.type = it.expr.type;
    }

    @Override
    public void visit(preExprNode it) {
        it.expr.accept(this);

        switch (it.op) {
            case "++", "--" -> {
                if (!it.expr.type.isInt())
                    throw new semanticError("Error PrefixExpression", it.pos);
                if (!it.expr.assign)
                    throw new semanticError("Error Assign", it.pos);
                //the expression can be left value
                it.assign = true;
                it.type = new literalType("int");
            }
            case "+", "-", "~" -> {
                if (!it.expr.type.isInt())
                    throw new semanticError("Error PrefixExpression", it.pos);
                it.type = new literalType("int");
            }
            case "!" -> {
                if (!it.expr.type.isBool())
                    throw new semanticError("Error PrefixExpression", it.pos);
                it.type = new literalType("bool");
            }
            default -> throw new semanticError("Error PrefixExpression's Operation", it.pos);
        }
    }

    @Override
    public void visit(newExprNode it) {
        if (it.exprList != null) {
            it.exprList.forEach(x -> {
                x.accept(this);
                if (!x.type.isInt()) throw new semanticError("New Array's parameter Error", it.pos);
            });
        }
        it.type = nowScope.typeGet(it.typeN.type, it.pos);
    }

    @Override
    public void visit(binaryExprNode it) {
        it.expr1.accept(this);
        it.expr2.accept(this);

        switch (it.op) {
            case "-":
            case "*":
            case "/":
            case "%":
            case "<<":
            case ">>":
            case "&":
            case "|":
            case "^":
                if (!it.expr1.type.isInt() || !it.expr2.type.isInt())
                    throw new semanticError("Invalid Operation", it.pos);
                it.type = new literalType("int");
                break;
            case "+":
                if (it.expr1.type.isInt() && it.expr2.type.isInt()) {
                    it.type = new literalType("int");
                } else if (it.expr1.type.isString() && it.expr2.type.isString()) {
                    it.type = new literalType("string");
                } else throw new semanticError("Invalid Operation", it.pos);
                break;
            case "<":
            case ">":
            case "<=":
            case ">=":
                if ((it.expr1.type.isInt() && it.expr2.type.isInt()) ||
                        (it.expr1.type.isString() && it.expr2.type.isString()))
                    it.type = new literalType("bool");
                else throw new semanticError("Invalid Operation", it.pos);
                break;
            case "&&":
            case "||":
                if (!it.expr1.type.isBool() || !it.expr2.type.isBool())
                    throw new semanticError("Invalid Operation", it.pos);
                it.type = new literalType("bool");
                break;
            case "==":
            case "!=":
                if (!it.expr1.type.typeEqual(it.expr2.type))
                    throw new semanticError("Invalid Operation", it.pos);
                it.type = new literalType("bool");
                break;
            case "=":
                if (!it.expr1.type.typeEqual(it.expr2.type))
                    throw new semanticError("Invalid Operation (Type dismatch))", it.pos);
                if (!it.expr1.assign)
                    throw new semanticError("Invalid Operation (Type of value Error)", it.pos);
                it.assign = true;
                it.type = it.expr1.type;
                break;
            default:
                throw new semanticError("Error Binary Expression's Operation", it.pos);
        }
    }

    @Override
    public void visit(exprListNode it) {
        it.exprList.forEach(x -> x.accept(this));
    }
}
