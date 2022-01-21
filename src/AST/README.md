# AST List

+ ROOT: progNode

+ ASTnode
    + progNode - *program*
    + typeNode - *varType*
    + defNode
        + varDefNode (直接与varDefStmtNode合并) - *varDef*
        + funcDefNode - *funcDef*
        + classDefNode - *classDef*
    + stmtNode - *statement*
        + varDecStmtNode - *varDeclaration+parameter*
        + varDefStmtNode - *varDef* (注意这里将Def里的type直接全部分配给了Declaration，Def里不保存)
        + suiteStmtNode - *suiteStmt*
        + ifStmtNode - *ifStmt*
        + whileStmtNode - *whileStmt*
        + forStmtNode - *forStmt*
        + returnStmtNode - *returnStmt*
        + breakStmtNode - *breakStmt*
        + continueStmtNode - *continueStmt*
        + pureExprStmtNode - *pureExprStmt*
        + emptyStmtNode - *emptyStmt*
    + exprNode - *expression*
        + intExprNode
        + boolExprNode
        + stringExprNode
        + nullExprNode - all of the above - *constExpr*
        + idExprNode - *idExpr*
        + thisExprNpde - *thisExpr*
        + lambdaExprNode - *lambdaExpr*
        + funcExprNode - *funcExpr*
        + indexExprNode - *indexExpr*
        + classExprNode - *classExpr*
        + sufExprNode - *sufExpr*
        + preExprNode - *preExpr*
        + newExprNode - *newExpr*
        + binaryExprNode - *binaryExpr*
        + exprListNode - *expressionList*

+ ASTvisitor