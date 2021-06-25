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

    /**
     * 提交了 commit 以后立马发现，妈蛋，我忘了在某个等号后面加空格了。
     * 当然，你也可以提交一个新的 commit 然后利用 rebase -i 命令来合并它们，
     * 但我觉得我的这种方式比你快 100 万倍
     *
     * 使用 git commit --amend 追加到最近的一个commit上,最后只能看到一条commit
     *
     * 修改commit信息，也可以直接使用 git commit --amend，在编辑器里面修改后即可
     */
    @Test
    public void amendTest(){
        System.out.println("commit1");
        System.out.println("commit2");
    }

    /**
     *
     * 哎呦我去，我把这个 commit 提交错分支了！
     * 撤回这次提交，但保留改动的内容
     * git reset HEAD~ --soft // HEAD~表示最近一次的提交
     * git stash
     * 现在切到正确的那个分支去
     * git checkout name-of-the-correct-branch
     * git stash pop
     * git add . # 或者你可以添加指定的文件
     * git commit -m "your message here";
     * 现在你的改动就在正确的分支上啦
     */
    public void stashTest(){

    }

    public void diff(){
        System.out.println("after add use git diff .... 看不到diff");
        System.out.println("after add use git diff --staged .... 看的到diff");
    }
}
