package cat.arithmetic.leetcode.expiredLeetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author nanyin
 * @class LFUCache.java
 * @description 460. LFU缓存
 * @create 16:41 2020-04-05
 * <p>
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，
 * 当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 一个项目的使用次数就是该项目被插入后对其调用 get 和 put 函数的次数之和。使用次数会在对应项目被移除后置为 0。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2  );// capacity (缓存容量)
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LFUCache {

    // 依据题目，需要准备一个capacity作为容量
    private int capacity;
    private Map<Integer,Node> cache;
    // 存储每个频次对应的双向链表
    private Map<Integer, LinkedHashSet<Node>> freqMap;
    // 记录最小频率
    private int minFreq;
    // 记录cahce的大小
    private int size;

    // 定一个Node结构
    class Node{
        private Integer key;
        private Integer value;
        private Integer freq;;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    /**
     * @title: LFUCache
     * @description 构造函数
     * @param capacity
     * @return
     * @author: nanyin
     * @update: 18:26 2020-04-05
     */
    public LFUCache(int capacity) {
        this.cache = new HashMap<>(capacity);
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 1;
    }

    /**
     * @title: get
     * @description get函数，每次获取key的时候，如果找到需要增加使用次数
     * @param key
     * @return int
     * @author: nanyin
     * @update: 18:27 2020-04-05
     */
    public int get(int key) {
        Node node = this.cache.get(key);
        if(node == null){
            return -1;
        }
        freqInc(node);
        // 调整 node 中的next域
        return node.value;
    }

    public void put(int key, int value) {
        if(this.capacity == 0){
            return;
        }
        Node node = this.cache.get(key);
        if(node == null){
            Node newNode = new Node(key,value);
            // cache中没有，添加一个节点
            if(this.capacity == this.size){
                // 删除节点
                removeMinNode();
            }
            // 再添加节点
            addNode(newNode);
        }else{
            node.value = value;
            // 说明重复,重置freqmap中的内容
            freqInc(node);
        }

    }

    public void addNode(Node node){
        int freq = node.freq;
        LinkedHashSet<Node> newSet = freqMap.get(freq);
        if(newSet == null){
            newSet = new LinkedHashSet<>();
            freqMap.put(freq,newSet);
        }
        newSet.add(node);
        this.cache.put(node.key,node);
        // 每次添加节点都重置minFreq
        this.minFreq = node.freq;
        size++;
    }

    public int removeMinNode(){
        LinkedHashSet<Node> nodes = freqMap.get(this.minFreq);
        Node node = nodes.iterator().next();
        // 每次remove元素时，需要将两种结构中的都移除掉
        nodes.remove(node);
        this.cache.remove(node.key);
        size--;
        return node.key;
    }

    /**
     * @title: freqInc
     * @description 调整使用次数的函数，每次添加旧节点和获取节点时使用
     * @param node
     * @return void
     * @author: nanyin
     * @update: 18:29 2020-04-05
     */
    private void freqInc(Node node){
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        // 将原来的次数中删除
        set.remove(node);
        if(freq == minFreq && set.size() == 0){
            // 如果低频次中没有数据，说明需要增加minFreq
            minFreq ++;
        }
        node.freq ++;
        LinkedHashSet<Node> newSet = freqMap.get(freq+1);
        if(newSet == null){
            // 创建一个set
            LinkedHashSet<Node> newLinkedHashSet = new LinkedHashSet<>();
            newLinkedHashSet.add(node);
            freqMap.put(freq+1,newLinkedHashSet);
        }else{
            newSet.add(node);
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        System.out.println(lfuCache.get(1));;
        lfuCache.put(3,3);
        System.out.println(lfuCache.get(2));;
        System.out.println(lfuCache.get(3));;
        lfuCache.put(4,4);
        System.out.println(lfuCache.get(1));;
        System.out.println(lfuCache.get(3));;
        System.out.println(lfuCache.get(4));;

    }


}
