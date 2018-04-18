#### 1 ~ 200

| 1 ~ 100                                                      | 101 ~ 200                                                    |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
|                                                              | [#120-triangle-medium](#120-triangle-medium)                 |
| [#22-generate-parentheses-medium](#22-generate-parentheses-medium) |                                                              |
| [#32-longest-valid-parentheses-hard](#32-longest-valid-parentheses-hard) |                                                              |
| [#33-search-in-rotated-sorted-array-medium](#33-search-in-rotated-sorted-array-medium) |                                                              |
| [#42-trapping-rain-water-hard](#42-trapping-rain-water-hard) |                                                              |
|                                                              | [#149-max-points-on-a-line-hard](#149-max-points-on-a-line-hard) |
|                                                              |                                                              |
| [#61-rotate-list-medium](#61-rotate-list-medium)             |                                                              |
| [#62-unique-paths-medium](#62-unique-paths-medium)           |                                                              |
| [#63-unique-paths-ii-medium](#63-unique-paths-ii-medium)     |                                                              |
| [#64-minimum-path-sum-medium](#64-minimum-path-sum-medium)   |                                                              |
| [#66-plus-one-easy](#66-plus-one-easy)                       |                                                              |
| [#67-add-binary-easy](#67-add-binary-easy)                   |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |
| [#70-climbing-stairs-easy](#70-climbing-stairs-easy)         |                                                              |
| [#71-simplify-path-medium](#71-simplify-path-medium)         |                                                              |
| [#73-set-matrix-zeros-medium](#73-set-matrix-zeros-medium)   |                                                              |
| [#74-search-a-2d-matrix-medium](#74-search-a-2d-matrix-medium) |                                                              |
| [#75-sort-colors-medium](#75-sort-colors-medium)             |                                                              |
| [#76-minimun-window-substring-hard](#76-minimun-window-substring-hard) |                                                              |
| [#77-combinations-medium](#77-combinations-medium)           |                                                              |
| [#78-subsets-medium](#78-subsets-medium)                     |                                                              |
| [#79-word-search-medium](#79-word-search-medium)             |                                                              |
| [#80-remove-duplicates-from-sorted-array-ii-medium](#80-remove-duplicates-from-sorted-array-ii-medium) |                                                              |
| [**#81-search-in-rotated-sorted-array-ii-medium**](#81-search-in-rotated-sorted-array-ii-medium) |                                                              |
| [#82-remove-duplicates-from-sorted-list-ii-medium](#82-remove-duplicates-from-sorted-list-ii-medium) |                                                              |
| [#83-remove-duplicates-from-sorted-list-easy](#83-remove-duplicates-from-sorted-list-easy) |                                                              |
| [#84-largest-rectangle-in-histogram-hard](#84-largest-rectangle-in-histogram-hard) |                                                              |

#### 201 ~ 400

| 201 ~ 300                                                    | 301 ~ 400                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [#201-bitwise-and-of-numbers-range-medium](#201-bitwise-and-of-numbers-range-medium) |                                                              |
|                                                              | [#419-battleships-in-a-board-medium](#419-battleships-in-a-board-medium) |
| [#241-different-ways-to-add-parentheses-medium](#241-different-ways-to-add-parentheses-medium) |                                                              |
|                                                              |                                                              |
| [#243-shortest-word-distance-easy](#243-shortest-word-distance-easy) |                                                              |
| [#244-shortest-word-distance-ii-medium](#244-shortest-word-distance-ii-medium) |                                                              |
| [#245-shortest-word-distance-iii-medium](#245-shortest-word-distance-iii-medium) |                                                              |

#### 401 ~ 600

| 401 ~ 500 | 501 ~ 600                                                    |
| --------- | ------------------------------------------------------------ |
|           | [#560-subarray-sum-equals-k-medium](#560-subarray-sum-equals-k-medium) |
|           |                                                              |
|           |                                                              |



#### #22-generate-parentheses-medium

Given *n* pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given *n* = 3, a solution set is:

```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

```java
    public List<String> generateParenthesis(int n) {
        // utilizing backtracking
        List<String> result = new ArrayList<>();
        backtrack(result,new StringBuilder(),0,0,n);
        return result;
    }
    private void backtrack(List<String> result,StringBuilder temp,int left,int right,int n){
        if(temp.length() == n * 2){
            result.add(temp.toString());
            return;
        }
        int len = temp.length();
        // append (
        if(left < n){
            temp.append('(');
            backtrack(result,temp,left+1,right,n);
            temp.setLength(len);
        }
        // append )
        if(right < left){
            temp.append(')');
            backtrack(result,temp,left,right+1,n);
            temp.setLength(len);
        }
    }
```



---

#### #32-longest-valid-parentheses-hard

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

#### #33-search-in-rotated-sorted-array-medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`).

You are given a target value to search. If found in the array return its index, otherwise return `-1`.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of *O*(log *n*).

**Example 1:**

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**

```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

```java
    public int search(int[] nums, int target) {
        if(nums==null || nums.length==0)
            return -1;
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target)
                return mid;
            // left half not rotate
            if(nums[left] <= nums[mid]){
                // mid in not rotate part
                if(nums[left]<=target && target<nums[mid])
                    right = mid - 1;
                // mid in rotate part
                else left = mid + 1;
            // right half not rotate
            }else{
                // mid in not rotate part
                if(nums[mid]<target && target<=nums[right])
                    left = mid + 1;
                // mid in rotate part
                else right = mid -1;
            }
        }
        return -1;
    }
```

---

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

#### #42-trapping-rain-water-hard

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

#### 61. Rotate List (medium)

Given a list, rotate the list to the right by *k* places, where *k* is non-negative.

**Example:**

```
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
```

```java
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode rotateRight(ListNode head, int k) {
        // notice corner case
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0), fast = dummy;
        dummy.next = head;
        // find length first, and end node
        int len = 0;
        while(fast.next != null){
            len ++;
            fast = fast.next;
        }
        // find node before new head
        // k may larger than len => %
        ListNode slow = dummy;
        for(int i=0;i<len-k%len;i++)
            slow = slow.next;
        // rotation order, 
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
    }   
```

---

#### 62. Unique Paths (medium)

A robot is located at the top-left corner of a *m* x *n* grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

![img](https://leetcode.com/static/images/problemset/robot_maze.png)

Above is a 3 x 7 grid. How many possible unique paths are there?

**Note:** *m* and *n* will be at most 100.

```java
    public int uniquePaths(int m, int n) {
        // time O(mn), space O(min(m,n))
        if(m <= 0 || n <=0)
            return 0;
        // use 1D dp 
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }
```

---

#### 63. Unique Paths II (medium)

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as `1` and `0` respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

```
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
```

The total number of unique paths is `2`.

```java
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // similar to previous, use 1D DP
        if(obstacleGrid==null || obstacleGrid.length==0 || obstacleGrid[0].length==0)
            return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;  // init head as 0
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // obstacle, curr count reset 0
                if(obstacleGrid[i][j] == 1)
                    dp[j] = 0;
                // accumulate when j > 0
                else if(j > 0)
                    dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }
```

---

#### 64. Minimum Path Sum (medium)

Given a *m* x *n* grid filled with non-negative numbers, find a path from top left to bottom right which *minimizes* the sum of all numbers along its path.

**Note:** You can only move either down or right at any point in time.

**Example 1:**

```
[[1,3,1],
 [1,5,1],
 [4,2,1]]
```

Given the above grid map, return 

```
7
```

. Because the path 1→3→1→1→1 minimizes the sum.

```java
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        int m = grid.length, n = grid[0].length;
        // go through first row
        for(int j=1;j<n;j++)
            grid[0][j] += grid[0][j-1];
        // go through first col
        for(int i=1;i<m;i++)
            grid[i][0] += grid[i-1][0];
        // go through other
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
        return grid[m-1][n-1];
    }
```

#### 66. Plus One (easy)

Given a non-negative integer represented as a **non-empty** array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

```java
    public int[] plusOne(int[] digits) {
        if(digits==null || digits.length==0)
            return new int[0];
        // if 9, continue check previous,
        // if not 9, +1 will stop
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i] == 9)
                digits[i] = 0;
            else{
                digits[i] ++;
                break;
            }
        }
        // if all 0
        if(digits[0] == 0){
            int[] result = new int[digits.length+1];
            result[0] = 1;
            return result;
        }
        return digits;
    }
```

---

#### 67-add-binary-easy

Given two binary strings, return their sum (also a binary string).

For example,
a = `"11"`
b = `"1"`
Return `"100"`.

```java
    public String addBinary(String a, String b) {
        if(a == null || b == null)
            return "";
        // use string builder and reverse
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int sum = 0;
        while(i >= 0 || j >= 0){
            if(i >= 0)
                sum += a.charAt(i--) - '0';
            if(j >= 0)
                sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
            sum /= 2;
        }
        if(sum != 0)
            sb.append(sum);
        return sb.reverse().toString();    
    }
```

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

#### #70-climbing-stairs-easy

You are climbing a stair case. It takes *n* steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

**Note:** Given *n* will be a positive integer.

**Example 1:**

```
Input: 2
Output:  2
Explanation:  There are two ways to climb to the top.

1. 1 step + 1 step
2. 2 steps
```

**Example 2:**

```
Input: 3
Output:  3
Explanation:  There are three ways to climb to the top.

1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

```java
    public int climbStairs(int n) {
        // n step is count n-1 step + count n-2 step
        if(n <= 2)
            return n;
        int first = 1, second = 2, curr = 0;
        for(int i=3;i<=n;i++){
            curr = first + second;
            first = second;
            second = curr;
        }
        return curr;
    }
```

---

#### #71-simplify-path-medium

Given an absolute path for a file (Unix-style), simplify it.

For example,
**path** = `"/home/"`, => `"/home"`
**path** = `"/a/./b/../../c/"`, => `"/c"`

- Did you consider the case where **path** = `"/../"`?
  In this case, you should return `"/"`.
- Another corner case is the path might contain multiple slashes `'/'` together, such as `"/home//foo/"`.
  In this case, you should ignore redundant slashes and return `"/home/foo"`.

```java
    public String simplifyPath(String path) {
        // use stack .. pop else push
        if(path == null || path.length() == 0)
            return "";
        Stack<String> stack = new Stack<>();
        for(String s : path.split("/")){
            // case .(curr folder) continue
            if(s.equals("."))
                continue;
            // case .. prev folder
            else if(s.equals("..")){
                if(!stack.isEmpty())
                    stack.pop();
            // case name dir, not empty, not ///
            }else if(!s.isEmpty()){
                stack.push(s);
            }
        }
        // case /
        if(stack.isEmpty())
            return "/";
        // pop and build result
        StringBuilder sb = new StringBuilder();
        // build list from stack
        List<String> list = new ArrayList<>(stack);
        for(String s : list)
            sb.append("/").append(s);
        return sb.toString();
    }
```

---

#### #73-set-matrix-zeros-medium

Given a *m* x *n* matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Did you use extra space?
A straight forward solution using O(*m**n*) space is probably a bad idea.
A simple improvement uses O(*m* + *n*) space, but still not the best solution.
Could you devise a constant space solution?

```java
    public void setZeroes(int[][] matrix) {
        // set all leading row & col to zeros
        if(matrix.length==0 || matrix[0].length==0)
            return;
        boolean firstRow = false, firstCol = false;
        int row = matrix.length, col = matrix[0].length;
        // check first col
        for(int i=0;i<row;i++)
            if(matrix[i][0] == 0)
                firstCol = true;
        // check first row
        for(int j=0;j<col;j++)
            if(matrix[0][j] == 0)
                firstRow = true;
        // the other
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // set 0 skip first row & col
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                if(matrix[i][0]==0 || matrix[0][j]==0)
                    matrix[i][j] = 0;
            }
        }
        // update first row & col can cause problem
        // update first row & col
        if(firstRow)
            for(int j=0;j<col;j++)
                matrix[0][j] = 0;
        if(firstCol)
            for(int i=0;i<row;i++)
                matrix[i][0] = 0;
    }
```

---

#### #74-search-a-2d-matrix-medium

Write an efficient algorithm that searches for a value in an *m* x *n* matrix. This matrix has the following properties:

- Integers in each row are sorted from left to right.
- The first integer of each row is greater than the last integer of the previous row.

For example,

Consider the following matrix:

```
[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
```

Given **target** = `3`, return `true`.

```java
    public boolean searchMatrix(int[][] matrix, int target) {
        // convert to binary search
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;
        final int m = matrix.length, n = matrix[0].length;
        // left right should be index
        int left = 0, right = m * n - 1;
        while(left <= right){
            int mid = left + (right-left)/2;
            int row = mid / n;
            int col = mid % n;
            if(matrix[row][col] == target)
                return true;
            else if(matrix[row][col] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
```

---

#### #75-sort-colors-medium

Given an array with *n* objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

**Note:**
You are not suppose to use the library's sort function for this problem.

```java
    public void sortColors(int[] nums) {
        //  0000|i|1111|j|????|k|2222
        if(nums==null || nums.length==0)
            return;
        int i = 0, j = 0, k = nums.length-1;
        // j is unvisited, notice j = k
        while(j <= k){
            if(nums[j] == 0){
                swap(nums,i++,j++);
            }else if(nums[j] == 1)
                j++;
            else if(nums[j] == 2)
                swap(nums,j,k--);
        }
    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
```

```java
    public void sortColors(int[] nums) {
        //  0000|i|1111|j|????|k|2222
        if(nums==null || nums.length==0)
            return;
        int i = 0, j = 0, k = nums.length-1;
        // j is unvisited, notice j = k
        while(j <= k){
            if(nums[j] == 0){
                nums[j++] = nums[i];
                nums[i++] = 0;
            }else if(nums[j] == 1){
                j ++;
            }else if(nums[j] == 2){
                nums[j] = nums[k];
                nums[k--] = 2;
            }
        }
    }
```

---

#### #76-minimun-window-substring-hard

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
**S** = `"ADOBECODEBANC"`
**T** = `"ABC"`

Minimum window is `"BANC"`.

**Note:**
If there is no such window in S that covers all characters in T, return the empty string `""`.

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

```java
    public String minWindow(String s, String t) {
        if(s==null || t==null)
            return "";
        // find t histogram first
        int[] bin = new int[256];
        for(char c : t.toCharArray())
            bin[c] ++;
        // sliding window
        int head = 0, count = t.length();
        int minLen = s.length() + 1, minHead = 0;
        for(int tail=0;tail<s.length();tail++){
            if(bin[s.charAt(tail)] > 0)
                count --;
            bin[s.charAt(tail)]--;
            // valid edge condition
            while(count == 0){
                // update min then shift window
                if(tail-head+1 < minLen){
                    minLen = tail - head + 1;
                    minHead = head;
                }
                if(bin[s.charAt(head)] == 0)
                    count ++;
                bin[s.charAt(head)]++;
                head++;
            }
        }
        return minLen > s.length() ? "" : s.substring(minHead,minHead+minLen);
    }
```

---

#### #77-combinations-medium

Given two integers *n* and *k*, return all possible combinations of *k* numbers out of 1 ... *n*.

For example,
If *n* = 4 and *k* = 2, a solution is:

```
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

```java
    public List<List<Integer>> combine(int n, int k) {
        // backtracking
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result,new ArrayList<>(),1,n,k);
        return result;
    }
    private void backtrack(List<List<Integer>> result,List<Integer> temp,int start,int end,int count){
        // optimization here, if start to close to end so no enough elements
        if(count - temp.size() > end - start + 1)
            return;
        //////////////////////////////////////
        if(temp.size() == count){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start;i<=end;i++){
            temp.add(i);
            backtrack(result,temp,i+1,end,count);
            temp.remove(temp.size()-1);
        }
    }
```

---

#### #78-subsets-medium

Given a set of **distinct** integers, *nums*, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

For example,
If **nums** = `[1,2,3]`, a solution is:

```
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

```java
    public List<List<Integer>> subsets(int[] nums) {
        // backtracking // distinct
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result,nums,new ArrayList<>(),0);
        return result;
    }
    private void backtrack(List<List<Integer>> result, int[] nums,List<Integer> temp,int idx){
        // add result each level
        result.add(new ArrayList<>(temp));
        for(int i=idx;i<nums.length;i++){
            temp.add(nums[i]);
            backtrack(result,nums,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }
```

---

#### #79-word-search-medium

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given **board** = 

```
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
```

word = `"ABCCED"`, -> returns `true`,

word = `"SEE"`, -> returns `true`,

word = `"ABCB"`, -> returns `false`.

```java
    public boolean exist(char[][] board, String word) {
        // similar to num of island
        // use ^256 to obtain space O(1)
        if(board==null || board.length==0 || board[0].length==0)
            return false;
        final int m = board.length, n = board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(helper(board,word,0,i,j))
                    return true;
            }
        }
        return false;
    }
    private boolean helper(char[][] board,String word,int idx,int x,int y){
        if(idx == word.length())
            return true;
        if(x<0 || x>=board.length || y<0 || y>=board[0].length || 
           word.charAt(idx)!=board[x][y])
            return false;
        // set to not a valid letter
        board[x][y] ^= 256;
        if(helper(board,word,idx+1,x-1,y) ||
          helper(board,word,idx+1,x+1,y) ||
          helper(board,word,idx+1,x,y-1) ||
          helper(board,word,idx+1,x,y+1))
            return true;
        // reset to letter
        board[x][y] ^= 256;
        return false;
    }
```

---

#### #80-remove-duplicates-from-sorted-array-ii-medium

Follow up for "Remove Duplicates":
What if duplicates are allowed at most *twice*?

For example,
Given sorted array *nums* = `[1,1,1,2,2,3]`,

Your function should return length = `5`, with the first five elements of *nums* being `1`, `1`, `2`, `2` and `3`. It doesn't matter what you leave beyond the new length.

```java
    public int removeDuplicates(int[] nums) {
        if(nums==null)
            return 0;
        if(nums.length <= 2)
            return nums.length;
        int tail = 2;
        // similar but check tail - 2
        for(int i=2;i<nums.length;i++){
            if(nums[i] != nums[tail-2])
                nums[tail++] = nums[i];
        }
        return tail;
    }
    public int removeDuplicates(int[] nums) {
        // optimized
        if(nums==null)
            return 0;
        if(nums.length <= 2)
            return nums.length;
        int tail = 0;
        for(int num : nums){
            if(tail < 2 || num != nums[tail-2])
                nums[tail++] = num;
        }
        return tail;
    }
```

---

#### #81-search-in-rotated-sorted-array-ii-medium

> *Follow up* for "Search in Rotated Sorted Array":
> What if *duplicates* are allowed?
>
> Would this affect the run-time complexity? How and why?

[#33-search-in-rotated-sorted-array-medium](#33-search-in-rotated-sorted-array-medium)

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `0 1 2 4 5 6 7` might become `4 5 6 7 0 1 2`).

Write a function to determine if a given target is in the array.

The array may contain duplicates.

```java
    public boolean search(int[] nums, int target) {
// 1) everytime check if targe == nums[mid], if so, we find it.
// 2) otherwise, we check if the first half is in order (i.e. nums[left]<=nums[mid]) 
//   and if so, go to step 3), otherwise, the second half is in order,   go to step 4)
// 3) check if target in the range of [left, mid-1] (i.e. nums[left]<=target < nums[mid]), 
//         if so, do search in the first half, i.e. right = mid-1; otherwise, search in the second half left = mid+1;
// 4)  check if target in the range of [mid+1, right] (i.e. nums[mid]<target <= nums[right]), 
//         if so, do search in the second half, i.e. left = mid+1; otherwise search in the first half right = mid-1;
        if(nums==null || nums.length==0)
            return false;
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target)
                return true;
            // difference to deal with duplicate here
            if(nums[left]==nums[mid] && nums[mid]==nums[right]){
            left++;
            right--;
            // left half not rotate
            }else if(nums[left] <= nums[mid]){
                // mid in not rotate part
                if(nums[left]<=target && target<nums[mid])
                    right = mid - 1;
                // mid in rotate part
                else left = mid + 1;
            // right half not rotate
            }else if(nums[mid] <= nums[right]){
                // mid in not rotate part
                if(nums[mid]<target && target<=nums[right])
                    left = mid + 1;
                // mid in rotate part
                else right = mid -1;
            }
        }
        return false;       
    }
```

---

#### #82-remove-duplicates-from-sorted-list-ii-medium

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only *distinct* numbers from the original list.

For example,
Given `1->2->3->3->4->4->5`, return `1->2->5`.
Given `1->1->1->2->3`, return `2->3`.

```java
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy, curr = head;
        dummy.next = head;
        while(curr != null){
            // if next val = curr val, check next
            while(curr.next!=null && curr.val==curr.next.val)
                curr = curr.next;
            // if curr is distinct
            if(prev.next == curr)
                prev = prev.next;
            else    
            // prev stays, case next sequence duplication
            // curr is the last duplication
                prev.next = curr.next;   
            curr = curr.next;
            
        }
        return dummy.next;
    }
```

---

#### #83-remove-duplicates-from-sorted-list-easy

Given a sorted linked list, delete all duplicates such that each element appear only *once*.

For example,
Given `1->1->2`, return `1->2`.
Given `1->1->2->3->3`, return `1->2->3`.

**iteration**

```java
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode curr = head;
        while(curr != null){
            while(curr.next!=null && curr.val==curr.next.val)
                curr.next = curr.next.next;
            curr = curr.next;
        }
        return head;
    }
```

**recursion**

```java
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        head.next = deleteDuplicates(head.next);
        // here head.next != null
        return head.val == head.next.val ? head.next : head;
    }
```

---

#### #84-largest-rectangle-in-histogram-hard

Given *n* non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

![img](https://leetcode.com/static/images/problemset/histogram.png)

Above is a histogram where width of each bar is 1, given height = `[2,1,5,6,2,3]`.

![img](https://leetcode.com/static/images/problemset/histogram_area.png)

The largest rectangle is shown in the shaded area, which has area = `10` unit.

For example,
Given heights = `[2,1,5,6,2,3]`,
return `10`.

[#42-trapping-rain-water-hard](#42-trapping-rain-water-hard)

```java
	// Maintains an increasing stack
	// encounter smaller height, check all previous areas.
	public int largestRectangleArea(int[] heights) {
		Stack<Integer> idxStack = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i <= heights.length; i++) {
			// push -1 for last, to pop all previous
			int height = i == heights.length ? 0 : heights[i];
			// when increasing is broken
			while (!idxStack.isEmpty() && heights[idxStack.peek()] >= height) {
				// get previous higher one, compute previous area
				// it's all area from smaller to the highest
				int h = heights[idxStack.pop()];
				// if is empty, there is a bottom rectangle from current to head
				int w = idxStack.isEmpty() ? i : i - 1 - idxStack.peek();
				maxArea = Math.max(maxArea, h * w);
			}
			idxStack.push(i);
		}
		return maxArea;
	}
```

**optimized beats 98%, check left then right idea **

```java
    public int largestRectangleArea(int[] heights) {
        // save left and right index
        // width is most left & right idx whose h is larger
        if(heights==null || heights.length==0)
            return 0;
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        // find left/right index whose h is larger
        // utilize previous result to reduce run time
        for(int i=0;i<heights.length;i++){
            int l = i-1;
            while(l>=0 && heights[l]>=heights[i])
                l = left[l];
            left[i] = l;
        }
        for(int i=heights.length-1;i>=0;i--){
            int r = i+1;
            while(r<heights.length && heights[r]>=heights[i])
                r = right[r];
            right[i] = r;
        }        
        // then for h, the width is r - l - 1;
        int maxArea = 0;
        for(int i=0;i<heights.length;i++){
            int w = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea,heights[i]*w);
        }
        return maxArea;
    }
```



---

#### #120-triangle-medium

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```

The minimum path sum from top to bottom is `11` (i.e., 2 + 3 + 5 + 1 = 11).

**Note:**
Bonus point if you are able to do this using only *O*(*n*) extra space, where *n* is the total number of rows in the triangle.

```java
    public int minimumTotal(List<List<Integer>> triangle) {
        // add bottom up, so that space O(1)
        if(triangle.size() == 0)
            return 0;
        int n = triangle.size();
        // n+1 for the last line
        int[] memo = new int[n+1];
        // for location i, check i & i+1
        for(int i=n-1; i>=0; i--){
            for(int j=0; j<triangle.get(i).size(); j++){
                int curr = triangle.get(i).get(j);
                // memo is init as 0s
                memo[j] = Math.min(memo[j],memo[j+1]) + curr;
            }
        }
        return memo[0];
    }
```



---

#### #149-max-points-on-a-line-hard

Given *n* points on a 2D plane, find the maximum number of points that lie on the same straight line.



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

#### #201-bitwise-and-of-numbers-range-medium

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

```java
    public int rangeBitwiseAnd(int m, int n) {
        // right shift, all tail digits are 0
        // until m == n, this is the head
        // append 0 tail to head
        if(m == 0)
            // 0 & x == 0
            return 0;
        int tailLen = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            tailLen ++;
        }
        return m << tailLen;
    }
```

------



---

#### #241-different-ways-to-add-parentheses-medium

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are `+`, `-` and `*`.

Example 1

Input: `"2-1-1"`.

```
((2-1)-1) = 0
(2-(1-1)) = 2
```

Output: `[0, 2]`

Example 2

Input: `"2*3-4*5"`

```
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
```

Output: `[-34, -14, -10, -10, 10]`

```java
    public List<Integer> diffWaysToCompute(String input) {
        // use recursion
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            // divide input to two part
            if(c=='+' || c=='-' || c=='*'){
                String left = input.substring(0,i);
                String right = input.substring(i+1);
                List<Integer> list1 = diffWaysToCompute(left);
                List<Integer> list2 = diffWaysToCompute(right);
                // combine to subset
                for(int num1 : list1){
                    for(int num2 : list2){
                        int temp = 0;
                        switch(c){
                            case '+': 
                                temp = num1 + num2;
                                break;
                            case '-':
                                temp = num1 - num2;
                                break;
                            case '*':
                                temp = num1 * num2;
                                break;
                        }
                        // one possible result
                        result.add(temp);
                    }
                }
            }
        }
        // case theres no operator in input
        if(result.size() == 0)
            result.add(Integer.parseInt(input));
        return result;
    }
```

**add memory and optimization**

```java
    public List<Integer> diffWaysToCompute(String input) {
        Map<String,List<Integer>> map = new HashMap<>();
        return dfs(input,map,0,input.length());
    }
    private List<Integer> dfs(String input,Map<String,List<Integer>> map,int start,int end){
        // use recursion
        // if this expression is saved
        String expression = input.substring(start,end);
        if(map.containsKey(expression))
            return map.get(expression);
        List<Integer> result = new ArrayList<>();
        for(int i=start;i<end;i++){
            char c = input.charAt(i);
            // divide input to two part
            if(c=='+' || c=='-' || c=='*'){
                List<Integer> list1 = dfs(input,map,start,i);
                List<Integer> list2 = dfs(input,map,i+1,end);
                // combine to subset
                for(int num1 : list1){
                    for(int num2 : list2){
                        int temp = 0;
                        switch(c){
                            case '+': 
                                temp = num1 + num2;
                                break;
                            case '-':
                                temp = num1 - num2;
                                break;
                            case '*':
                                temp = num1 * num2;
                                break;
                        }
                        // one possible result
                        result.add(temp);
                    }
                }
            }
        }
        // case theres no operator in input
        if(result.size() == 0)
            result.add(Integer.parseInt(expression));
        map.put(expression,result);
        return result;        
    }
```

---



---

#### #243-shortest-word-distance-easy

Given a list of words and two words *word1* and *word2*, return the shortest distance between these two words in the list.

For example,
Assume that words = `["practice", "makes", "perfect", "coding", "makes"]`.

Given *word1* = `“coding”`, *word2* = `“practice”`, return 3.
Given *word1* = `"makes"`, *word2* = `"coding"`, return 1.

**Note:**
You may assume that *word1* **does not equal to** *word2*, and *word1* and *word2* are both in the list.

```java
    public int shortestDistance(String[] words, String word1, String word2) {
        if(words==null || words.length==0)
            return 0;
        int idx1 = -1, idx2 = -1, min = words.length;
        for(int i=0;i<words.length;i++){
            if(words[i].equals(word1))
                idx1 = i;
            else if(words[i].equals(word2))
                idx2 = i;
            // need to check whether idx are set
            if(idx1!=-1 && idx2!=-1)
                min = Math.min(min,Math.abs(idx1-idx2));
        }
        return min;
    }
```

---

#### #244-shortest-word-distance-ii-medium

This is a **follow up** of [Shortest Word Distance](https://leetcode.com/problems/shortest-word-distance). The only difference is now you are given the list of words and your method will be called *repeatedly* many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words *word1* and *word2* and return the shortest distance between these two words in the list.

For example,
Assume that words = `["practice", "makes", "perfect", "coding", "makes"]`.

Given *word1* = `“coding”`, *word2* = `“practice”`, return 3.
Given *word1* = `"makes"`, *word2* = `"coding"`, return 1.

**Note:**
You may assume that *word1* **does not equal to** *word2*, and *word1* and *word2* are both in the list.

```java
class WordDistance {
    Map<String,List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for(int i=0;i<words.length;i++){
            if(!map.containsKey(words[i]))
                map.put(words[i],new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        int idx1 = 0, idx2 = 0;
        // utilize two pointer to optimize
        while(idx1<l1.size() && idx2<l2.size()){
            min = Math.min(min,Math.abs(l1.get(idx1)-l2.get(idx2)));
            if(l1.get(idx1) < l2.get(idx2))
                idx1++;
            else
                idx2++;
        }
        return min;
    }
}
```

---

#### #245-shortest-word-distance-iii-medium

This is a **follow up** of [Shortest Word Distance](https://leetcode.com/problems/shortest-word-distance). The only difference is now *word1* could be the same as *word2*.

Given a list of words and two words *word1* and *word2*, return the shortest distance between these two words in the list.

*word1* and *word2* may be the same and they represent two individual words in the list.

For example,
Assume that words = `["practice", "makes", "perfect", "coding", "makes"]`.

Given *word1* = `“makes”`, *word2* = `“coding”`, return 1.
Given *word1* = `"makes"`, *word2* = `"makes"`, return 3.

**Note:**
You may assume *word1* and *word2* are both in the list.

```java
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(words==null || words.length==0)
            return 0;
        int min = words.length;
        int idx1 = -1, idx2 = -1;
        for(int i=0; i<words.length; i++){
            if(words[i].equals(word1))
                idx1 = i;
            if(words[i].equals(word2)){
                if(word1.equals(word2))
                // set idx1 to prev idx2
                    idx1 = idx2;
                idx2 = i;
            }
            if(idx1 != -1 && idx2 != -1){
                min = Math.min(min,Math.abs(idx1-idx2));
            }
        }
        return min;
    }
```

**quicker**

```java
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(words==null || words.length==0)
            return 0;
        int min = words.length;
        int idx1 = -1, idx2 = -1;
        if(word1.equals(word2)){
            for(int i=0;i<words.length;i++){
                if(words[i].equals(word1)){
                    if(idx1 == -1)
                        idx1 = i;
                    else{
                        min = Math.min(min,i-idx1);
                        idx1 = i;
                    }
                }
            }            
        }else{
            for(int i=0;i<words.length;i++){
                if(words[i].equals(word1))
                    idx1 = i;
                else if(words[i].equals(word2))
                    idx2 = i;
                if(idx1!=-1 && idx2!=-1)
                    min = Math.min(min,Math.abs(idx1-idx2));
            }
        }
        return min;
    }
```

---





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

---

#### #560-subarray-sum-equals-k-medium

Given an array of integers and an integer **k**, you need to find the total number of continuous subarrays whose sum equals to **k**.

**Example 1:**

```
Input:nums = [1,1,1], k = 2
Output: 2
```

**Note:**

1. The length of the array is in range [1, 20,000].
2. The range of numbers in the array is [-1000, 1000] and the range of the integer **k** is [-1e7, 1e7].

```java
    public int subarraySum(int[] nums, int k) {
        if(nums==null || nums.length==0)
            return 0;
        // use map to save curr sum & count
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);   // 
        int count = 0, sum = 0;
        for(int num : nums){
            sum += num;
            count += map.getOrDefault(sum-k,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }
```

