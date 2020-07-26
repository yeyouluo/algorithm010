## 字典树（Trie）

### 概念定义

又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串，所以经常被搜索引擎系统用于文本词频统计。

### 适用场景

一次建树，多次查询。 

> 在HanLP中，使用trie树做字典。

### 基本性质

1. 结点本身不存完整单词；
2. 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串；
3. 每个结点的所有子结点路径代表的字符都不相同。

> 优点：最大限度的减少无谓的字符串比较，查找效率比哈希表高。
>
> #### 为什么 Trie 树根节点不存储数据（哨兵模式）
>
> 根节点其实是一个初始化的空 TrieNode 节点，表示一个空字符串的状态，并不匹配任何字符串。而其指向下一节点的路径（即 links 数组）才构成字符串前缀。所以说 Trie 树不存储数据，实际上是一种状态的表示。

### 核心思想

- Trie树的核心思想是空间换时间；
- 利用字符串的公共前缀降低查询时间的开销以达到提高效率的目的。

### Trie树代码模板

```java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```

## 并查集（ Disjoint / Union Find）

### 概念定义

并查集，在一些有N个元素的[集合](https://baike.baidu.com/item/集合/2908117)应用问题中，我们通常是在开始时让每个元素构成一个单元素的集合，然后按一定顺序将属于同一组的元素所在的集合合并，其间要反复查找一个元素在哪个集合中。

### 适用场景

- 组团、配对问题：是否好友，是否在一个集合中
- 分组：群合并

### 基本操作

- makeSet ( s ) : 建立并查集，包含s个元素的集合
- unionSet ( x,y ) : 把元素x和元素y所在集合合并
- find (x) : 找到元素x所在集合的代表

### 并查集代码模板

```java
class UnionFind {
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```

## 高级搜索

### 剪枝

剪枝核心思想是提前判定某些分支不用走，从而达到优化目的。

#### **初级搜索**

1. 朴素搜索
2. 优化方式：不重复、剪枝
3. 搜索方向：DFS、BFS

#### **高级搜索**

1. 剪枝
2. 双向搜索、启发式搜索

#### **回溯法**

回溯法采用试错思想，尝试分步的去解决一个问题。在分步解决问题的过程中，当发现现有的分步不能得到正确的解答时，它将取消上一步甚至上几步的计算，再通过其他的分步再次尝试。

回溯法通常用递归实现，可能出现两种情况：

- 找到一个可能存在的正确答案
- 尝试了所有可能的方法后宣告没有答案

最坏情况下，时间复杂度是指数级的。

### 双向BFS

#### 概念定义

从前后两端同时开始BFS遍历。维护两个set，每次将节点加入较短的set，直到endset中找到结果。

> 个人理解：类似于双指针实现的夹逼法，只是将指针换做了BFS整体的业务逻辑，可以极大程度的缩减程序消耗。

#### 代码模板

```java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String,List<String>> tempMap = new HashMap<String,List<String>>();
        // 定义两个队列
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        // 定义visited数组
        Set<String> visited = new HashSet<String>();
        q1.add(beginWord);
        q2.add(endWord);
        int step = 1;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String cur : q1) {
                // 前后遍历相交的时候，说明找到结果了
                if (q2.contains(cur)) {
                    return step;
                }
                // 记录visited
                visited.add(cur);
                // 处理当前节点
                char[] curArr = cur.toCharArray();
                for (int i = 0; i < curArr.length; i++) {
                    char c = curArr[i];
                    curArr[i] = '*';
                    List<String> list = tempMap.get(String.valueOf(curArr));
                    if (list != null && list.size() > 0) {
                        for (String s : list) {
                            // 将相邻节点加入队列
                            if (!visited.contains(s)) {
                                temp.add(s);
                            }
                        }
                    }
                    curArr[i] = c;
                }
            }
            // 步数+1，前后队列互换
            step++;
            q1 = q2;
            q2 = temp;
        }
        return 0;
    }
```



### 启发式搜索 Heuristic Search(A*)

#### **代码模板**

```python
def AstarSearch(graph, start, end):
	# 将BFS的队列换成优先队列
	pq = collections.priority_queue() # 优先级 —> 估价函数
	pq.append([start]) 
	visited.add(start)
	while pq: 
		node = pq.pop()
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
   unvisited = [node for node in nodes if node not in visited]
		pq.push(unvisited)
```

#### 估价函数

> A*搜索重点是找到估价函数。

启发式函数：h(n)，用来评价哪些节点最有希望是我们要找的节点，返回一个非负实数，可以认为是从n的目标节点路径的估计成本。
启发式函数是一种告知搜索方向的方法，它提供了一种明智的方法来猜测哪个邻居节点会导向一个目标。

## AVL树

### 概念定义

自平衡二叉查找树。在AVL树中任何节点的两个子树的高度最大差别为1，所以它也被称为**高度平衡树**。

### 平衡因子（Balance Factor）

左子树高度减去右子树高度（有时相反）

### 旋转操作

1. 右右子树 -> 左旋
2. 左左子树 -> 右旋
3. 左右子树 -> 左右旋
4. 右左子树 -> 右左旋

### AVL总结

1. 平衡二叉搜索树

2. 每个节点存balance factor = {-1,0,1}

3. 四种旋转操作

>  不足：节点需要存储额外信息、且调整次数频繁

## 红黑树（Red-black Tree）

### 概念定义

红黑树是一种**近似平衡**的二叉搜索树，满足任何一个节点的左右子树的**高度差小于两倍**。

### 基本性质

1. 每个节点要么是红色，要么是黑色
2. 根结点是黑色
3. 每个叶子结点（null结点，空节点）是黑色的
4. 不能有相邻接的两个红色结点
5. 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点

### AVL树&红黑树对比

- 查询：AVL树优于红黑树，因为AVL树是严格平衡的
- 插入 & 删除：红黑树优于AVL树，红黑树是近似平衡的
- 存储：红黑树优于AVL树，因为AVL树每个节点需要存储balance factor,红黑树只需要1bit存储颜色信息
- 红黑树大多数用在语言类库，AVL多用在读多写少，比如数据库中

### 拓展：平衡树

> - [平衡树](https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree)

