## 递归

> 递归和循环没有明显的边界（①编程语言指令结构只有if else/for while loop/recursion，②机器码通过跳转(goto)实现代码逻辑复用）。

### 递归需要满足的3个条件

只要同时满足以下三个条件，就可以用递归来解决：

1. 一个问题的解可以分解为几个子问题的解（子问题就是数据规模更小的问题）
2. 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
3. 存在递归终止条件

### 编写递归程序

#### 递归代码编写步骤

写递归代码的关键就是找到如何将大问题分解为小问题的规律（**重复子问题**），并且基于此写出**递推公式**，然后再推敲终止条件（**递归终止条件**），最后将递推公式和终止条件翻译成代码。

#### 递归模板

1. recursion terminator 递归终止条件

2. process current level logic

3. dril down 下探

4. reverse current states if needed 清理当前层

   

Python递归模板：

```python
# Python
def recursion(level, param1, param2, ...): 
    # recursion terminator 
    if level > MAX_LEVEL: 
	   process_result 
	   return 
    # process logic in current level 
    process(level, data...) 
    # drill down 
    self.recursion(level + 1, p1, ...) 
    # reverse the current level status if needed
```

Java递归模板：

```java
// Java
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
 
}
```

### 写递归程序的3个注意点

1. 抵制人肉递归 不要人肉进行递归(最大误区)

2. 找最近重复性 找到最近最简方法,将其拆解成可重复解决的问题(重复子问题)

3. 数学归纳法思维 数据归纳法思维
   

## 分治、回溯

分治、回溯都是特殊的递归。

分治代码模板：

```python
# Python
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
	print_result 
	return 
  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 
  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …
  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```

