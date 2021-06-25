package cat.git;

import org.junit.Test;

/**
 * Oh shit,git!?
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-06-25
 */
public class Git {

    /**
     * reflog 命令 如： git reflog
     * 展示出所有的分支、所有的commit，包括删除的
     * 找到在犯错前的那个提交记录的索引号，然后执行：
     * git reset HEAD@{index}
     */
    @Test
    public void reflogTest(){
        System.out.println("commit1");
        // System.out.println("commit2");

        // 8ad204c (HEAD -> master, origin/master) HEAD@{0}: commit: git reflog test2
        // 47b772d HEAD@{1}: commit: git reflog test1

    }
}
