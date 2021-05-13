package cat.designPatterns.visitor;

public interface Visitor {
    void visit(OperateNodeA operateNodeA);
    void visit(OperateNodeB operateNodeB);
}

