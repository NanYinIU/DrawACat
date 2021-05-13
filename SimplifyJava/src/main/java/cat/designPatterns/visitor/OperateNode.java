package cat.designPatterns.visitor;

public abstract class OperateNode {
    abstract void accept(Visitor visitor);
}
