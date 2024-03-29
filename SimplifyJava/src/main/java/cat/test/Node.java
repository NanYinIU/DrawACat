package cat.test;

/**
 * 基本的 Node 节点
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-12-29
 */

public class Node {

  private Long length;

  public Long getLength() { return length; }

  public void setLength(Long length) { this.length = length; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  private String name;

  @Override
  public String toString() {
    return "Node{"
        + "length=" + length + ", name='" + name + '\'' + '}';
  }
}
