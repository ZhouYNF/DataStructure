package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "卢俊义");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero3 = new HeroNode(3, "吴用");


        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.addByOrder(hero2);


        System.out.println("原来链表的情况~~");
        list.list();

        System.out.println("修改后链表的情况~~~");
        HeroNode hero5 = new HeroNode(3, "aaaaaaa");
        list.update(hero5);
        list.list();

        System.out.println("删除后链表的情况~~~");
        list.delete(1);
        list.list();

        System.out.println("返回当前链表的个数");
        System.out.println(getLength(list.getHrad()));
        System.out.println("返回当前链表的倒数第一个");
        System.out.println(findLastIndexNode(list, 1));

        System.out.println("反转");
        reversetList(list.getHrad());
        list.list();

        System.out.println("使用栈的方式反转");
        reversePrint(list.getHrad());
    }

    /**
     * 返回倒数第x个数据
     *
     * @param list
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(SingleLinkedList list, int index) {
        if (list.getHrad().next == null) { //空链表
            return null;
        }
        int size = getLength(list.getHrad());
        if (size < index || index <= 0) {
            System.out.println("长度越界");
        }

        HeroNode cur = list.getHrad().next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }

        return cur;
    }


    /**
     * 获取链表的个数
     *
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    //将单链表反转
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //原表节点
        HeroNode cur = head.next;

        //新表当前节点
        HeroNode next =null;
        HeroNode reverseHead = new HeroNode(0, "");

        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        head.next = reverseHead.next;
    }

    //方式2：
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode cur = head.next;
        Stack<HeroNode> heroNodes = new Stack<HeroNode>();
        while (cur != null){
            heroNodes.push(cur);
            cur = cur.next;
        }

        while (heroNodes.size() > 0){
            System.out.println(heroNodes.pop());
        }

    }


}

/**
 * //定义SingleLinkedList 节点管理
 */
class SingleLinkedList {
    private HeroNode hero = new HeroNode(0, "");

    /**
     * 返回头节点
     *
     * @return
     */
    public HeroNode getHrad() {
        return hero;
    }

    /**
     * 这是简单的添加功能
     *
     * @param hero
     */
    public void add(HeroNode hero) {
        //获取头节点
        HeroNode temp = getHrad();
        while (true) {
            //找到最后一个节点退出
            if (temp.next == null) {
                break;
            }
            //将下一节点，赋值给临时变量
            temp = temp.next;
        }
        //将新的数据，直接放在最后
        temp.next = hero;
    }

    /**
     * 这是一个有序的添加
     *
     * @param hero
     */
    public void addByOrder(HeroNode hero) {
        //获取头节点
        HeroNode temp = getHrad();
        //作为是否可以插入的判断，
        boolean flag = false;
        while (true) {
            //如果是最后一个节点或者当前节点的下一节点的值比当前添加的值大，就可以添加
            //因为是从小到大排序，所以当前节点的下一节点的值大的时候，就会放在他前面
            if (temp.next == null || temp.next.no > hero.no) {
                break;
            }
            //找到一样的就退出，不插入
            if (temp.next.no == hero.no) {
                flag = true;
                break;
            }
            //将下一节点传入
            temp = temp.next;
        }
        //判断是否可以添加
        if (flag) {
            System.out.println("已有数据，不能插入");
        } else {
            //当前节点的下一节点的值放在传入对象的next中
            hero.next = temp.next;
            //再将当前对象放入前一个temp.next中
            temp.next = hero;
        }
    }

    /**
     * 修改
     *
     * @param hero
     */
    public void update(HeroNode hero) {
        boolean flag = false;
        HeroNode temp = getHrad().next;
        if (temp == null) {
            System.out.println("链表为空,不能修改");
            return;
        }

        while (true) {
            if (temp.next == null) {
                System.out.println("链表遍历完成，并未找到需要修改的数据");
                break;
            }
            //当找一样就退出
            if (temp.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            //将值替换
            temp.name = hero.name;
        } else {
            System.out.println("修改失败");
        }
    }

    public void delete(int index) {
        boolean flag = false;
        HeroNode temp = getHrad();
        if (temp.next == null) {
            System.out.println("链表为空,不能删除");
            return;
        }
        while (true) {
            if (temp.next == null) {
                System.out.println("链表遍历完成，并未找到需要删除的数据");
                break;
            }
            //如果当前值被删除，则找不到前后节点，所有要找当前节点的下一节点
            if (temp.next.no == index) {
                flag = true;
                break;
            }
            temp = temp.next;

        }

        if (flag) {
            //直接将当前节点的下下节点将接上，遗弃不需要的节点，会不会造成内存占用？
            temp.next = temp.next.next;
        } else {
            System.out.println("没有删除的对象");
        }

    }

    public void list() {
        HeroNode temp = getHrad();
        if (temp.next == null) {
            System.out.println("链表为空，不能便利");
        }
        //第一节点为空
        temp = temp.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }


}

/**
 * 链表节点
 */
class HeroNode {
    public int no;
    public String name;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }
}