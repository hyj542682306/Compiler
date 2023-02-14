# Compiler

 An Mx* Compiler that includes semantic checking, code generation and optimization.
 
 For the specific introduction of Mx* language, please refer to [this repo](https://github.com/ACMClassCourses/Compiler-Design-Implementation) or [this page](https://github.com/hyj542682306/Compiler/blob/main/Mx.md).
 
 ## Feature
 
 + LLVM-like intermediate representation.
 + Completed graph-coloring and M2R optimization.


## Performance

Better than O1 compilation at each test point, that is, the running cycle is less than O1.

## Structure
```
📦Compiler
 ┣ 📂src
 ┃ ┣ 📂ASM
 ┃ ┃ ┣ 📂inst
 ┃ ┃ ┃ ┣ 📜Inst.java
 ┃ ┃ ┃ ┣ 📜arithmetic.java
 ┃ ┃ ┃ ┣ 📜branch.java
 ┃ ┃ ┃ ┣ 📜call.java
 ┃ ┃ ┃ ┣ 📜compare.java
 ┃ ┃ ┃ ┣ 📜j.java
 ┃ ┃ ┃ ┣ 📜la.java
 ┃ ┃ ┃ ┣ 📜li.java
 ┃ ┃ ┃ ┣ 📜lw.java
 ┃ ┃ ┃ ┣ 📜mv.java
 ┃ ┃ ┃ ┣ 📜ret.java
 ┃ ┃ ┃ ┗ 📜sw.java
 ┃ ┃ ┣ 📂operand
 ┃ ┃ ┃ ┣ 📜ASMOperand.java
 ┃ ┃ ┃ ┣ 📜ASMregister.java
 ┃ ┃ ┃ ┣ 📜globalRegister.java
 ┃ ┃ ┃ ┣ 📜immediate.java
 ┃ ┃ ┃ ┣ 📜physicalRegister.java
 ┃ ┃ ┃ ┣ 📜string.java
 ┃ ┃ ┃ ┗ 📜virtualRegister.java
 ┃ ┃ ┣ 📜ASMblock.java
 ┃ ┃ ┣ 📜ASMbfunction.java
 ┃ ┃ ┣ 📜ASMmodule.java
 ┃ ┃ ┗ 📜ASMvisitor.java
 ┃ ┣ 📂AST
 ┃ ┃ ┣ 📂defNode
 ┃ ┃ ┃ ┣ 📜classDefNode.java
 ┃ ┃ ┃ ┣ 📜defNode.java
 ┃ ┃ ┃ ┗ 📜funcDefNode.java
 ┃ ┃ ┣ 📂exprNode
 ┃ ┃ ┃ ┣ 📜binaryExprNode.java
 ┃ ┃ ┃ ┣ 📜boolExprNode.java
 ┃ ┃ ┃ ┣ 📜classExprNode.java
 ┃ ┃ ┃ ┣ 📜exprListNode.java
 ┃ ┃ ┃ ┣ 📜exprNode.java
 ┃ ┃ ┃ ┣ 📜funcExprNode.java
 ┃ ┃ ┃ ┣ 📜idExprNode.java
 ┃ ┃ ┃ ┣ 📜indexExprNode.java
 ┃ ┃ ┃ ┣ 📜intExprNode.java
 ┃ ┃ ┃ ┣ 📜lambdaExprNode.java
 ┃ ┃ ┃ ┣ 📜newExprNode.java
 ┃ ┃ ┃ ┣ 📜nullExprNode.java
 ┃ ┃ ┃ ┣ 📜preExprNode.java
 ┃ ┃ ┃ ┣ 📜pureExprStmtNode.java
 ┃ ┃ ┃ ┣ 📜stringExprNode.java
 ┃ ┃ ┃ ┣ 📜sufExprNode.java
 ┃ ┃ ┃ ┗ 📜thisExprNode.java
 ┃ ┃ ┣ 📂stmtNode
 ┃ ┃ ┃ ┣ 📜breakStmtNode.java
 ┃ ┃ ┃ ┣ 📜continueStmtNode.java
 ┃ ┃ ┃ ┣ 📜emptyStmtNode.java
 ┃ ┃ ┃ ┣ 📜forStmtNode.java
 ┃ ┃ ┃ ┣ 📜ifStmtNode.java
 ┃ ┃ ┃ ┣ 📜returnStmtNode.java
 ┃ ┃ ┃ ┣ 📜stmtNode.java
 ┃ ┃ ┃ ┣ 📜suiteStmtNode.java
 ┃ ┃ ┃ ┣ 📜varDecStmtNode.java
 ┃ ┃ ┃ ┣ 📜varDefStmtNode.java
 ┃ ┃ ┃ ┗ 📜whileStmtNode.java
 ┃ ┃ ┣ 📜ASTNode.java
 ┃ ┃ ┣ 📜ASTvisitor.java
 ┃ ┃ ┣ 📜progNode.java
 ┃ ┃ ┗ 📜typeNode.java
 ┃ ┣ 📂IR
 ┃ ┃ ┣ 📂inst
 ┃ ┃ ┃ ┣ 📜Inst.java
 ┃ ┃ ┃ ┣ 📜alloca.java
 ┃ ┃ ┃ ┣ 📜binary.java
 ┃ ┃ ┃ ┣ 📜bitcast.java
 ┃ ┃ ┃ ┣ 📜br.java
 ┃ ┃ ┃ ┣ 📜call.java
 ┃ ┃ ┃ ┣ 📜define.java
 ┃ ┃ ┃ ┣ 📜getelementptr.java
 ┃ ┃ ┃ ┣ 📜global.java
 ┃ ┃ ┃ ┣ 📜icmp.java
 ┃ ┃ ┃ ┣ 📜load.java
 ┃ ┃ ┃ ┣ 📜ret.java
 ┃ ┃ ┃ ┗ 📜store.java
 ┃ ┃ ┣ 📂operand
 ┃ ┃ ┃ ┣ 📜Operand.java
 ┃ ┃ ┃ ┣ 📜boolConst.java
 ┃ ┃ ┃ ┣ 📜globalVariable.java
 ┃ ┃ ┃ ┣ 📜intConst.java
 ┃ ┃ ┃ ┣ 📜nullOperand.java
 ┃ ┃ ┃ ┣ 📜register.java
 ┃ ┃ ┃ ┣ 📜stringConst.java
 ┃ ┃ ┃ ┗ 📜temporary.java
 ┃ ┃ ┣ 📂type
 ┃ ┃ ┃ ┣ 📜IRType.java
 ┃ ┃ ┃ ┣ 📜arrayType.java
 ┃ ┃ ┃ ┣ 📜classType.java
 ┃ ┃ ┃ ┣ 📜intType.java
 ┃ ┃ ┃ ┣ 📜pointerType.java
 ┃ ┃ ┃ ┗ 📜voidType.java
 ┃ ┃ ┣ 📜IRscope.java
 ┃ ┃ ┣ 📜IRvisitor.java
 ┃ ┃ ┣ 📜basicblock.java
 ┃ ┃ ┣ 📜function.java
 ┃ ┃ ┗ 📜module.java
 ┃ ┣ 📂backend
 ┃ ┃ ┣ 📜ASMBuilder.java
 ┃ ┃ ┣ 📜ASMPrinter.java
 ┃ ┃ ┣ 📜ASMRegisterAllocator.java
 ┃ ┃ ┣ 📜IRBuilder.java
 ┃ ┃ ┗ 📜IRPrinter.java
 ┃ ┣ 📂frontend
 ┃ ┃ ┣ 📜ASTBuilder.java
 ┃ ┃ ┣ 📜SemanticChecker.java
 ┃ ┃ ┣ 📜SymbolCollector.java
 ┃ ┃ ┗ 📜TypeCollector.java
 ┃ ┣ 📂optimize
 ┃ ┃ ┣ 📜GraphColoring.java
 ┃ ┃ ┗ 📜MemToReg.java
 ┃ ┣ 📂parser
 ┃ ┃ ┣ 📜Mx_lite.g4
 ┃ ┃ ┣ 📜Mx_lite.interp
 ┃ ┃ ┣ 📜Mx_lite.tokens
 ┃ ┃ ┣ 📜Mx_liteBaseListener.java
 ┃ ┃ ┣ 📜Mx_liteBaseVisitor.java
 ┃ ┃ ┣ 📜Mx_liteLexer.interp
 ┃ ┃ ┣ 📜Mx_liteLexer.java
 ┃ ┃ ┣ 📜Mx_liteLexer.tokens
 ┃ ┃ ┣ 📜Mx_liteListener.java
 ┃ ┃ ┣ 📜Mx_liteParser.java
 ┃ ┃ ┗ 📜Mx_liteVisitor.java
 ┃ ┣ 📂util
 ┃ ┃ ┣ 📂error
 ┃ ┃ ┣ 📂type
 ┃ ┃ ┃ ┣ 📜Type.java
 ┃ ┃ ┃ ┣ 📜arrayType.java
 ┃ ┃ ┃ ┣ 📜classType.java
 ┃ ┃ ┃ ┣ 📜funcSymbol.java
 ┃ ┃ ┃ ┣ 📜literalType.java
 ┃ ┃ ┃ ┗ 📜varSymbol.java
 ┃ ┃ ┣ 📜Mx_liteErrorListener.java
 ┃ ┃ ┣ 📜position.java
 ┃ ┃ ┗ 📜scope.java
 ┃ ┗ 📜Main.java
 ┣ 📜build.bash
 ┣ 📜builtin.s
 ┣ 📜codegen.bash
 ┗ 📜semantic.bash
```
