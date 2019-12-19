package Tree;

/**
 * Created by yuehu on 12/14/19.
 */
public class TestTree {
    public int check(int cur) {
        boolean flag = false;
        if (cur == 4) {
            flag = true;
        }
        if (flag == true) {
            return 3;
        }
        return 1;
    }
    public static void main(String[] args) {
        TestTree s = new TestTree();
        System.out.println(s.check(4));
    }
}
