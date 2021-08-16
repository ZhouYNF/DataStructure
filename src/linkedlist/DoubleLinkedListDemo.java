package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        HeroNode2 heroNode1 = new HeroNode2(1, "aaa");
        HeroNode2 heroNode2 = new HeroNode2(2, "bbb");
        HeroNode2 heroNode3 = new HeroNode2(3, "ccc");
        linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.add(heroNode3);
        linkedList.list();


    }

}

// 创建一个双向链表的类
class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "");

    public HeroNode2 getHead() {
        return head;
    }

    // 遍历双向链表的方法
    // 显示链表[遍历]
    public void list() {
        HeroNode2 temp = getHead().next;
        if (temp == null) {
            System.out.println("链表为空,不能遍历");
        }

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode2 hero) {
        HeroNode2 temp = getHead();
        boolean flag = true;
        while (true) {
            if (temp.next == null || temp.next.no > hero.no) {
                break;
            }
            if (temp.next.no == hero.no) {
                System.out.println("已有相同编号,不能插入");
                flag = false;
                break;
            }
            temp = temp.next;
        }
        HeroNode2 next;
        if (flag) {
            hero.next = temp.next;
            temp.next =hero;
            //hero.pre = temp;
            //temp.next.pre = hero ;
        }else{
            System.out.println("不能插入！！！");
        }

    }

}

/**
 * 链表节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre;//只想上一个节点

    //构造器
    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //为了显示方法，我们重新toString
    /*@Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }*/

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", next=" + next +
                ", pre=" + pre +
                '}';
    }
}