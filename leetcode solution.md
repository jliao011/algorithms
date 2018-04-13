#### [50 ~ 100](#50-powxn-medium)

|                                                              |
| ------------------------------------------------------------ |
|                                                              |
|                                                              |
| [419. Battleships in a Board (medium)](#419-battleships-in-a-board-medium) |



#### 32. Longest Valid Parentheses (hard)

Given a string containing just the characters `'('` and `')'`, find the length of the longest valid (well-formed) parentheses substring.

For `"(()"`, the longest valid parentheses substring is `"()"`, which has length = 2.

Another example is `")()())"`, where the longest valid parentheses substring is `"()()"`, which has length = 4.

```java
    public int longestValidParentheses(String s) {
        if(s==null || s.length()==0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // for first pair if ()
        int maxLen = 0;
        for(int i=0;i<s.length();i++){
           if(s.charAt(i) == '(')
               stack.push(i);
            else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);  // first not valid
                }else{
                    maxLen = Math.max(maxLen,i-stack.peek());
                }
            }
        }
        return maxLen;
    }
```

---------------------------------

#### 36. Valid Sudoku (medium)

Determine if a Sudoku is valid, according to: [Sudoku Puzzles - The Rules](http://sudoku.com.au/TheRules.aspx).

The Sudoku board could be partially filled, where empty cells are filled with the character `'.'`.

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

A partially filled sudoku which is valid.

**Note:**
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

```java
    public boolean isValidSudoku(char[][] board) {
        if(board==null || board.length!=9 || board[0].length!=9)
            return false;
        Set<Character> rowSet = new HashSet<>();
        Set<Character> colSet = new HashSet<>();
        Set<Character> cubSet = new HashSet<>();
        // check row, col, cube
        for(int row=0;row<9;row++){
            rowSet.clear();
            colSet.clear();
            cubSet.clear();
            for(int col=0;col<9;col++){
                // left to right
                if(board[row][col]!='.' && !rowSet.add(board[row][col]))
                    return false;
                // up to down
                if(board[col][row]!='.' && !colSet.add(board[col][row]))
                    return false;
                // map to a cube
                int cubeRow = 3 * (row / 3) + col / 3; 
                int cubeCol = 3 * (row % 3) + col % 3;
                if(board[cubeRow][cubeCol]!='.' && !cubSet.add(board[cubeRow][cubeCol]))
                    return false;
            }
        }
        return true;
    }
```

-------

#### 37. Sudoku Solver (hard)

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character `'.'`.

You may assume that there will be only one unique solution.

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

A sudoku puzzle...

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

...and its solution numbers marked in red.

```java
    public void solveSudoku(char[][] board) {
        if(board==null || board.length!=9 || board[0].length!=9)
            return;
        solver(board);
    }
    private boolean solver(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] != '.')
                    continue;
                for(char c='1';c<='9';c++){
                    if(isValid(board,i,j,c)){
                        board[i][j] = c;
                        if(solver(board))
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    private boolean isValid(char[][] board,int i,int j,char c){
        for(int k=0;k<9;k++){
            if(board[i][k] == c)    return false;
            if(board[k][j] == c)    return false;
            int row = 3 * (i / 3) + k / 3;
            int col = 3 * (j / 3) + k % 3;
            if(board[row][col] == c)    return false;
        }
        return true;
    }
```

-------------

#### 38. Count and Say (easy)

The count-and-say sequence is the sequence of integers with the first five terms as following:

```
1.     1
2.     11
3.     21
4.     1211
5.     111221
```

`1` is read off as `"one 1"` or `11`.
`11` is read off as `"two 1s"` or `21`.
`21` is read off as `"one 2`, then `one 1"` or `1211`.

Given an integer *n*, generate the *n*th term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

**Example 1:**

```
Input: 1
Output: "1"
```

**Example 2:**

```
Input: 4
Output: "1211"
```
```java
    public String countAndSay(int n) {
        if(n == 0)
            return "";
        String result = "1";
        for(int i=1;i<n;i++){
            StringBuilder sb = new StringBuilder();
            int count = 0;  // init at least count 0
            char c = result.charAt(0);  // head char
            // from 0, cuz append only in loop
            for(int j=0;j<result.length();j++){
                if(result.charAt(j) == c)
                    count ++;   // same char, count ++
                else{   // change head, append curr result
                    sb.append(count).append(c);
                    count = 1;
                    c = result.charAt(j);
                }
                // case '1' & last element
                if(j == result.length()-1)
                    sb.append(count).append(c);
            }
            result = sb.toString();
        }
        return result;
    }
```

----------

#### 39. Combination Sum (medium)

Given a **set** of candidate numbers (**C**) **(without duplicates)** and a target number (**T**), find all unique combinations in **C** where the candidate numbers sums to **T**. 

The **same** repeated number may be chosen from **C** **unlimited number of times**.

**Note:**

- All numbers (including target) will be positive integers.
- The solution set must not contain duplicate combinations.

For example, given candidate set `[2, 3, 6, 7]` and target `7`, 
A solution set is: 

```
[
  [7],
  [2, 2, 3]
]
```

```java
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // use backtracking
    	List<List<Integer>> result = new ArrayList<>();
        backtrack(result,candidates,new ArrayList<>(),target,0);
        return result;
    }
    private void backtrack(List<List<Integer>> result,int[] candidates,List<Integer> temp,int target,int idx){
        if(target < 0)
            return;
        if(target == 0){
            // should build a new list
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=idx;i<candidates.length;i++){
            temp.add(candidates[i]);
            // item repeatly used, should call i
            backtrack(result,candidates,temp,target-candidates[i],i);
            // remove after recursion
            temp.remove(temp.size()-1);
        }
    }
```

---------

#### 40. Combination Sum II (medium)

Given a collection of candidate numbers (**C**) and a target number (**T**), find all unique combinations in **C** where the candidate numbers sums to **T**.

Each number in **C** may only be used **once** in the combination.

**Note:**

- All numbers (including target) will be positive integers.
- The solution set must not contain duplicate combinations.

For example, given candidate set `[10, 1, 2, 7, 6, 1, 5]` and target `8`, 
A solution set is: 

```
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

```java
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // backtracking, duplicate element, use once
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);    // to skip duplicate
        backtrack(result,candidates,new ArrayList<>(),target,0);
        return result;
    }
    private void backtrack(List<List<Integer>> result,int[] candidates,List<Integer> temp,int target,int idx){
        if(target < 0)
            return;
        if(target == 0){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=idx;i<candidates.length;i++){
            // here i > idx not 0. to avoid duplicate
            // since same adjacent same element, so need sorting
            if(i>idx && candidates[i]==candidates[i-1])
                continue;
            temp.add(candidates[i]);
            backtrack(result,candidates,temp,target-candidates[i],i+1);
            temp.remove(temp.size()-1);
        }
    }
```

------------

#### 41. First Missing Positive (hard)

Given an unsorted integer array, find the first missing positive integer.

For example,
Given `[1,2,0]` return `3`,
and `[3,4,-1,1]` return `2`.

Your algorithm should run in *O*(*n*) time and uses constant space.

```java
    public int firstMissingPositive(int[] nums) {
        // swap pos num to corresponding index
        for(int i=0;i<nums.length;i++){
            // use nums[i]-1 as index, cannot have cycle
            while(nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[nums[i]-1]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        // traverse find nums[i]-1 != i
        for(int i=0;i<nums.length;i++)
            if(nums[i] != i + 1)
                return i + 1;
        // beyond the bound
        return nums.length + 1;
    }
```

---

#### 42. Trapping Rain Water (hard)

Given *n* non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given `[0,1,0,2,1,0,1,3,2,1,2,1]`, return `6`.

![img](http://www.leetcode.com/static/images/problemset/rainwatertrap.png)

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

**DP approach O(n) & O(n)** 

```java
    public int trap(int[] height) {
        // use dp 3 pass
        if(height.length == 0)
            return 0;
        // first pass find left max
        int[] left = new int[height.length];
        int leftMax = height[0];
        for(int i=0;i<height.length;i++){
            leftMax = Math.max(leftMax,height[i]);
            left[i] = leftMax;
        }
        // second pass find right max
        int[] right = new int[height.length];
        int rightMax = height[height.length-1];
        for(int i=height.length-1;i>=0;i--){
            rightMax = Math.max(rightMax,height[i]);
            right[i] = rightMax;
        }
        // third pass find water each idx
        int result = 0;
        for(int i=0;i<height.length;i++)
            result += Math.min(left[i],right[i]) - height[i];
        return result;
    }
```

**Stack  approach O(n) & O(n)**

```java
    public int trap(int[] height) {
        // stack approaching
        if(height.length == 0)
            return 0;
        // use stack to save index
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for(int i=0;i<height.length;i++){
            // if larger than prev
            while(!stack.isEmpty() && height[i]>height[stack.peek()]){
                int currIdx = stack.pop();
                if(stack.isEmpty())
                    break;
                // compare prev height and i height
                int h = Math.min(height[stack.peek()],height[i]) - height[currIdx];
                int w = i - stack.peek() - 1;
                result += h * w;
            }
            stack.push(i);
        }
        return result;
    }
```

**Two pointer approach O(n) & O(1)**

```java
    public int trap(int[] height) {
        // two pointer
        if(height.length == 0)
            return 0;
        // left & right max,
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int result = 0;
        // stop when left>right, should be <= 
        while(left <= right){
            // case left ++
            if(leftMax < rightMax){
                if(height[left] < leftMax)
                    result += leftMax - height[left];
                leftMax = Math.max(leftMax,height[left]);
                left ++;
            }else{  // case right --
                if(height[right] < rightMax)
                    result += rightMax - height[right];
                rightMax = Math.max(rightMax,height[right]);
                right --;
            }
        }
        return result;
    }
```

---

#### 43. Multiply Strings (medium)

Given two non-negative integers `num1` and `num2` represented as strings, return the product of `num1` and `num2`.

**Note:**

1. The length of both `num1` and `num2` is < 110.
2. Both `num1` and `num2` contains only digits `0-9`.
3. Both `num1` and `num2` does not contain any leading zero.
4. You **must not use any built-in BigInteger library** or **convert the inputs to integer** directly.

```java
    public String multiply(String num1, String num2) {
        if(num1==null || num1.length()==0)
            return "";
        if(num2==null || num2.length()==0)
            return "";
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        int m = num1.length(), n = num2.length();
        // num1 * num2 max len = m + n
        int[] result = new int[m+n];
        // i bit * j bit head is at i+j
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                int sum = n1 * n2 + result[i+j+1];
                // one digit
                result[i+j+1] = sum % 10;
                // ten digit
                result[i+j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<result.length;i++){
            if(!(sb.length()==0 && result[i]==0))
                sb.append(result[i]);
        }
        return sb.toString();
    }
```

---



#### 44. Wildcard Matching (hard)

Implement wildcard pattern matching with support for `'?'` and `'*'`.

```
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
```

Similar method to regular expression match, DP approach

```java
    public boolean isMatch(String s, String p) {
        if(s==null || p==null)
            return false;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        // go through first row, * could match ""
        // first col is false, dont match ""
        for(int j=1;j<=p.length();j++)
            if(p.charAt(j-1) == '*')
                dp[0][j] = dp[0][j-1];
        // case 1: p.char==(s.char, ?) => dp[i][j] = dp[i-1][j-1]
        // case 2: p.char==* => * is "" dp[i][j] = dp[i][j-1]
        //                   => * is not "" dp[i][j] = dp[i-1][j];
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='?')
                    dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(j-1) == '*')
                    // * is sequence and * is blank
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }
        return dp[s.length()][p.length()];
    }
```

---

#### 46. Permutations (distinct)(medium)

Given a collection of **distinct** numbers, return all possible permutations.

For example,
`[1,2,3]` have the following permutations:

```
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

**Backtracking add to temp**

```java
    public List<List<Integer>> permute(int[] nums) {
        // use backtracking, remember to use visited
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result,nums,new ArrayList<>(),new boolean[nums.length]);
        return result;
    }
    private void backtrack(List<List<Integer>> result,int[] nums,List<Integer> temp,boolean[] visited){
        if(temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        // check visited, i from 0 to n
        for(int i=0;i<nums.length;i++){
            if(visited[i])
                continue;
            visited[i] = true;
            temp.add(nums[i]);
            backtrack(result,nums,temp,visited);
            // reset when return to here
            visited[i] = false;
            temp.remove(temp.size()-1);
        }
    }
```

**Backtracking swap (quicker)**

```java
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result,nums,0);
        return result;
    }
    private void backtrack(List<List<Integer>> result,int[] nums,int idx){
        if(idx == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int num: nums)
                temp.add(num);
            result.add(temp);    
            return;
        }
        for(int i=idx;i<nums.length;i++){
            swap(nums,idx,i);
            // swap every position with idx
            backtrack(result,nums,idx+1);
            // swap back when return here
            swap(nums,idx,i);
        }
    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
```

---

#### 47. Permutations II (duplicates)(medium)

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
`[1,1,2]` have the following unique permutations:

```
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

```java
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // need sorting to remove duplicate
        Arrays.sort(nums);
        backtrack(result,nums,new ArrayList<>(),new boolean[nums.length]);
        return result;
    }
    private void backtrack(List<List<Integer>> result,int[] nums,List<Integer> temp,boolean[] used){
        if(temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=0;i<nums.length;i++){
            // both !used[i-1] and used[i-1] work
            if(used[i] || i>0&&nums[i]==nums[i-1]&&!used[i-1])
                continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack(result,nums,temp,used);
            used[i] = false;
            temp.remove(temp.size()-1);
        }
    }
```

---

#### 48. Rotate Image (medium)

Rotate the image by 90 degrees (clockwise).

**Note:**
You have to rotate the image **in-place**, which means you have to modify the input 2D matrix directly. **DO NOT** allocate another 2D matrix and do the rotation.

**clockwise: upside down first, then symmetry**

**anti-clockwise: left right first, then symmetry**

```java
    public void rotate(int[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0)
            return;
        if(matrix.length != matrix[0].length)
            return;
        // upside down
        int n = matrix.length;
        for(int col=0;col<n;col++){
            int up = 0, down = n-1;
            while(up < down){
                int temp = matrix[up][col];
                matrix[up][col] = matrix[down][col];
                matrix[down][col] = temp;
                up ++;
                down --;
            }
        }
        // symmetry
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
```

---

#### 49. Group Anagrams (medium)

Given an array of strings, group anagrams together.

For example, given: `["eat", "tea", "tan", "ate", "nat", "bat"]`, 
Return:

```
[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

**Note:** All inputs will be in lower-case.

**HashMap, return**

```java
    public List<List<String>> groupAnagrams(String[] strs) {
        // using hashtable
        // key is sorted str
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        if(strs.length == 0)
            return result;
        for(String str: strs){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            if(!map.containsKey(key))
                map.put(key,new ArrayList<>());
            map.get(key).add(str);            
        }
        // map.keySet() map.values()
        return new ArrayList<>(map.values());
    }
```

---

#### 50. Pow(x,n) (medium) 

**recursion**

```java
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n < 0){
            // deal with int.min
            // +2 could remain even or odd
            if(n == Integer.MIN_VALUE)
                n+=2;
            n = -n;
            x = 1 / x;
        }
        if(n % 2 == 0)
            return myPow(x*x,n/2);
        else
            return x * myPow(x*x,n/2);
    }
```

**iteration**

```java
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n < 0){
            // deal with int.min
            // +2 could remain even or odd
            if(n == Integer.MIN_VALUE)
                n+=2;
            n = -n;
            x = 1 / x;
        }
        double result = 1;
        while(n > 0){
            if((n & 1) == 1)  // n is odd
                result *= x;
            x *= x;
            n >>= 1;
        }
        return result;
    }
```

---

#### 51. N-Queens (hard)

**backtracking**

The *n*-queens puzzle is the problem of placing *n* queens on an *n*×*n* chessboard such that no two queens attack each other.

![img](https://leetcode.com/static/images/problemset/8-queens.png)

Given an integer *n*, return all distinct solutions to the *n*-queens puzzle.

Each solution contains a distinct board configuration of the *n*-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

```
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
```

```java
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // construct blank board
        char[][] board = new char[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j] = '.';
        // backtracking
        backtrack(result,board,0);
        return result; 
    }
    
    private void backtrack(List<List<String>> result,char[][] board,int col){
        if(col == board.length){
            result.add(build(board));
            return;
        }
        // for each row, try each col, only one loop need
        for(int row=0;row<board.length;row++){
            if(isValid(board,row,col)){
                board[row][col] = 'Q';
                backtrack(result,board,col+1);
                board[row][col] = '.';
            }
        }
    }
    
    private boolean isValid(char[][] board,int row,int col){
        int n = board.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == '.')
                    continue;
                // in same row, same col, same diagnal
                if(i==row  || j==col || Math.abs(i-row)==Math.abs(j-col))
                    return false;
            }
        }
        return true;
    }
    
    private List<String> build(char[][] board){
        // build board to a result instance
        int n = board.length;
        List<String> rows = new ArrayList<>();
        for(int i=0;i<n;i++)
            rows.add(String.valueOf(board[i]));
        return rows;
    }
```

---

#### 52. N-Queens II (hard)

Now, instead outputting board configurations, return the total number of distinct solutions.

```java
    public int totalNQueens(int n) {
        // only check valid cases
        // use 4 boolean arrays to mark:
        //     1. traverse each row, no check same row
        //     2. same col occupied
        //     3. 45 diagnal occupied: y=x+b => b=y-x constant
        //     4. 135 diagnal occupied y=-x+b => b=y+x constant
        //         board has 2n-1 diagnals each case
        int[] result = new int[1];
        boolean[] cols = new boolean[n];         // for col
        boolean[] diag1 = new boolean[2*n-1];   // for y-x
        boolean[] diag2 = new boolean[2*n-1];   // for y+x
        backtrack(result,0,cols,diag1,diag2);
        return result[0];        
    }
    private void backtrack(int[] result,int col,boolean[] cols,boolean[] diag1,boolean[] diag2){
        // traverse along rows, 
        int n = cols.length;
        if(col == n){
            result[0]++;
            return;
        }
        for(int row=0;row<n;row++){
            int idx1 = col - row + n - 1; // n-1 offset
            int idx2 = col + row;
            if(cols[row] || diag1[idx1] || diag2[idx2])
                continue;
            cols[row] = true;
            diag1[idx1] = true;
            diag2[idx2] = true;
            backtrack(result,col+1,cols,diag1,diag2);
            cols[row] = false;
            diag1[idx1] = false;
            diag2[idx2] = false;
        }
    }
```

---

#### 53. Maximum subarray (easy)

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array `[-2,1,-3,4,-1,2,1,-5,4]`,
the contiguous subarray `[4,-1,2,1]` has the largest sum = `6`.

```java
    public int maxSubArray(int[] nums) {
        // save currSum and maxSum 
        int currSum = 0, maxSum = nums[0];
        for(int num: nums){
            currSum = Math.max(num,num+currSum);
            maxSum = Math.max(maxSum,currSum);
        }
        return maxSum;
    }
```

---

#### 54. Spiral Matrix (medium)

Given a matrix of *m* x *n* elements (*m* rows, *n* columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

```
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
```

You should return `[1,2,3,6,9,8,7,4,5]`.

![SpiralMatrix](https://leetcode.com/problems/spiral-matrix/Figures/54_spiralmatrix.png)

```java
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix.length==0 || matrix[0].length==0)
            return result;
        int r1 = 0, r2 = matrix.length-1;
        int c1 = 0, c2 = matrix[0].length-1;
        while(r1<=r2 && c1<=c2){
            // add all r1
            for(int c=c1;c<=c2;c++)
                result.add(matrix[r1][c]);
            // add all c2
            for(int r=r1+1;r<=r2;r++)
                result.add(matrix[r][c2]);
            // if las row or col break
            if(r1==r2 || c1==c2)
                break;
            // add all r2
            for(int c=c2-1;c>=c1+1;c--)
                result.add(matrix[r2][c]);
            // add all c1
            for(int r=r2;r>=r1+1;r--)
                result.add(matrix[r][c1]);
            // update
            r1++;r2--;c1++;c2--;
        }
        return result;
    }
```

---

#### 55. Jump Game (medium)

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position. 

Determine if you are able to reach the last index.

For example:
A = `[2,3,1,1,4]`, return `true`.

A = `[3,2,1,0,4]`, return `false`.

**end to head, check whether it's a good point**

```java
    public boolean canJump(int[] nums) {
        if(nums.length == 0)
            return false;
        // last index that is a good point
        int last = nums.length - 1;
        for(int i=last-1;i>=0;i--){
            if(i + nums[i] >= last)
                last = i;
        }
        return last == 0;
    }
```

---

#### 56. Merge Intervals (medium)

Given a collection of intervals, merge all overlapping intervals.

For example,
Given `[1,3],[2,6],[8,10],[15,18]`,
return `[1,6],[8,10],[15,18]`.

```java
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if(intervals==null || intervals.size()==0)
            return result;
        Collections.sort(intervals,(o1,o2)->o1.start-o2.start);
        Interval prev = null;
        for(Interval curr : intervals){
            // result blank or no overlapping
            if(prev==null || curr.start>prev.end){
                prev = curr;
                result.add(prev);
            }else   // have overlapping
                prev.end = Math.max(prev.end,curr.end);
        }
        return result;
    }
```

---

#### 57. Insert Interval (hard)

Given a set of *non-overlapping* intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

**Example 1:**
Given intervals `[1,3],[6,9]`, insert and merge `[2,5]` in as `[1,5],[6,9]`.

**Example 2:**
Given `[1,2],[3,5],[6,7],[8,10],[12,16]`, insert and merge `[4,9]` in as `[1,2],[3,10],[12,16]`.

This is because the new interval `[4,9]` overlaps with `[3,5],[6,7],[8,10]`.

```java
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        // assume already sorted
        // Collections.sort(intervals,(o1,o2)->o1.start-o2.start);
        int idx = 0;
        // add intervals before newIntervals first, no overlapping
        while(idx<intervals.size() && intervals.get(idx).end<newInterval.start){
            result.add(intervals.get(idx));
            idx++;
        }
        // merge overlapping intervals, curr.end >= new.start
        while(idx<intervals.size() && intervals.get(idx).start<=newInterval.end){
            Interval curr = intervals.get(idx);
            newInterval.start = Math.min(newInterval.start,curr.start);
            newInterval.end = Math.max(newInterval.end,curr.end);
            idx++;
        }
        result.add(newInterval);
        // add intervals after newIntervals, no overlapping
        while(idx<intervals.size()){
            result.add(intervals.get(idx));
            idx++;
        }
        return result;
    }
```

---

#### 58. Length of Last Word (easy)

Given a string *s* consists of upper/lower-case alphabets and empty space characters `' '`, return the length of last word in the string.

If the last word does not exist, return 0.

**Note:** A word is defined as a character sequence consists of non-space characters only.

**Example:**

```
Input: "Hello World"
Output: 5
```

```java
    public int lengthOfLastWord(String s) {
        int tail = s.length()-1;
        while(tail>=0 && s.charAt(tail)==' ')
            tail--;
        int head = tail;
        while(head>=0 && s.charAt(head)!=' ')
            head--;
        return tail - head;
    }
```

---

#### 59. Spiral Matrix II (medium)

Given an integer *n*, generate a square matrix filled with elements from 1 to *n*2 in spiral order.

For example,
Given *n* = `3`,

You should return the following matrix:

```
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

```java
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int r1 = 0, r2 = n - 1;
        int c1 = 0, c2 = n - 1;
        while(r1<=r2 && c1<=c2){
            for(int c=c1;c<=c2;c++)
                result[r1][c] = num++;
            for(int r=r1+1;r<=r2;r++)
                result[r][c2] = num++;
            if(r1==r2 || c1==c2)
                break;
            for(int c=c2-1;c>=c1+1;c--)
                result[r2][c] = num++;
            for(int r=r2;r>=r1+1;r--)
                result[r][c1] = num++;
            r1++;r2--;c1++;c2--;
        }
        return result;
    }
```

---







----

#### 69. int sqrt(x) (easy)

**binary search**

```java
    public int mySqrt(int x) {
        if(x <= 1)
            return x;
        int left = 1, right = x;
        while(true){
            int mid = left + (right-left)/2;
            if(mid > x / mid)
                right = mid - 1;
            else{
                // mid^2 < x but mid+1 ^2 > x
                if(mid+1 > x/(mid+1))
                    return mid;
                left = mid + 1;
            }
        }
    }
```

---

#### 200. Number of Islands (medium)

Given a 2d grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**\*Example 1:* Answer: 1**

```
11110
11010
11000
00000
```

**\*Example 2:*** Answer: 3

```
11000
11000
00100
00011
```

**dfs approach time O(mn), space O(mn) worst** 

```java
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        // dfs approach, connected component idea
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // if 1, count ++, traverse neighbor
                if(grid[i][j] == '1'){
                    count ++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid,int i,int j){
        int m = grid.length, n = grid[0].length;
        // if out of bound, or not island
        if(i<0 || i>=m || j<0 || j>=n || grid[i][j]=='0')
            return;
        grid[i][j] = '0';   // set visited
        // visit up down left right
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
```

**BFS approach, time O(mn), space O(min(m,n))**

```java
    public int numIslands(char[][] grid) {
        // bfs approach
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        int count = 0;
        int m = grid.length, n = grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // only care about 1
                if(grid[i][j] == '1'){
                    count ++;
                    grid[i][j] = '0';
                    int[] curr = {i,j};
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(curr);
                    while(!queue.isEmpty()){
                        curr = queue.poll();
                        int row = curr[0], col = curr[1];
                        if(row-1>=0 && grid[row-1][col] == '1'){
                            queue.offer(new int[]{row-1,col});
                            grid[row-1][col] = '0';
                        }
                        if(row+1<m && grid[row+1][col] == '1'){
                            queue.offer(new int[]{row+1,col});
                            grid[row+1][col] = '0';
                        }
                        if(col-1>=0 && grid[row][col-1] == '1'){
                            queue.offer(new int[]{row,col-1});
                            grid[row][col-1] = '0';
                        }
                        if(col+1<n && grid[row][col+1] == '1'){
                            queue.offer(new int[]{row,col+1});
                            grid[row][col+1] = '0';
                        }                        
                    }
                }
            }
        }
        return count;
    }
```

---



#### 419. Battleships in a Board (medium)

Given an 2D board, count how many battleships are in it. The battleships are represented with 

`'X'`s, empty slots are represented with `'.'`s. You may assume the following rules:

- You receive a valid board, made of only battleships or empty slots.
- Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape `1xN` (1 row, N columns) or `Nx1` (N rows, 1 column), where N can be of any size.
- At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

**Example:**

```
X..X
...X
...X
```

In the above board there are 2 battleships.

**Invalid Example:**

```
...X
XXXX
...X
```

This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

```java
    public int countBattleships(char[][] board) {
        // idea only find ship head, left top cell
        if(board==null || board.length==0 || board[0].length==0)
            return 0;
        final int m = board.length, n = board[0].length;
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == '.')
                    continue;
                if(i>0 && board[i-1][j] == 'X')
                    continue;
                if(j>0 && board[i][j-1] == 'X')
                    continue;
                count ++;
            }
        }
        return count;
    }
```

