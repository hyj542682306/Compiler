package IR;

import IR.inst.*;

public interface IRvisitor {
    void visit(module it);

    void visit(function it);

    void visit(basicblock it);

    void visit(alloca it);

    void visit(binary it);

    void visit(br it);

    void visit(define it);

    void visit(global it);

    void visit(icmp it);

    void visit(load it);

    void visit(ret it);

    void visit(store it);

    void visit(call it);

    void visit(bitcast it);

    void visit(getelementptr it);
}
