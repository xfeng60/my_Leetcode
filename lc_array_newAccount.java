import java.util.*;


public class lc_array_newAccount{

	public List<String> summaryRanges(int[] nums) {       
        List<String> result = new LinkedList<>();
        for(int i=0;i<nums.length;){
        	
        	int j=i+1;
        	while(j<nums.length && nums[j]==nums[j-1]+1) j++;
        	if(j==i+1) result.add(nums[i]+"");
        	else{
        		//str = 
        		result.add(nums[i]+"" +  "->" + nums[j-1]);
        	}

        	i=j;
        }

        return result;    
    }

    public boolean containsDuplicate(int[] nums){
    	HashSet<Integer> seen  = new HashSet<>();
    	for(int i=0; i<nums.length; i++){
	    	if(!seen.add(nums[i]))
	    		return true;
    	}
    	return false;
    }

    public boolean containsNearbyDuplicate(int[] nums,int k){
    	HashSet<Integer> seen = new HashSet<>();

    	for(int i=0;i<nums.length;i++){
    		if(i>k) seen.remove(nums[i-k-1]);
    		if(!seen.add(nums[i]))
	    		return true;
    	}

    	return false;
    }

    //use TreeSet!
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> seen = new TreeSet<>();

        for(int i=0;i<nums.length;i++){
            if(i>k) seen.remove((long)nums[i-k-1]);
    	   
           Long floor = seen.floor((long)nums[i]);
           Long ceiling = seen.ceiling((long)nums[i]);

           if( floor!=null&&floor>=nums[i]-t || ceiling!=null&&ceiling<=nums[i]+t)	
                return true;
            seen.add((long)nums[i]);
                	
        }


    	return false;

    }

    public int removeElement(int[] nums, int val) {
        if(nums.length<2) return nums.length;
        int index=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1]) 
                nums[++index]=nums[i];
        }
        return index+1;
    }

    public int removeDuplicates(int[] nums) {
        return removeMoreThanKOccurrance(nums,2);
        
    }
    private int removeMoreThanKOccurrance(int[] nums, int k){
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if(index<k || nums[i]!=nums[index-k])
                nums[index++]=nums[i];
        }
        return index;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> seen = new HashMap<>();
        int left=0,right=0;
        for(int i=0;i<nums.length;i++){
            if(seen.containsKey(target-nums[i])){
                left = seen.get(target-nums[i]);
                right = i+1;
                System.out.printf("seen this :%d,%d\n",left,right);
                break;
            }else{
                System.out.printf("not see: %d\n",i);
                seen.put(nums[i],i++);
            }
        }
        int[] result = new int[2];
        result[0]=left;
        result[1]=right;
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            while(i!=0 && i<nums.length-2 &&nums[i]==nums[i-1]) i++;
            int j=i+1,k=nums.length-1;
            while(j<k){
                int temp = nums[i]+nums[j]+nums[k];
                if(temp==0){
                    //List<Integer> lst = new LinkedList<>(Arrays.asList(nums[i],nums[j],nums[k]));
                    result.add(Arrays.asList(nums[i],nums[j++],nums[k--]));
                    while(j<k && nums[j]==nums[j-1]) j++;
                    while(k>j && nums[k]==nums[k+1]) k--;
                }else{
                    if(temp>0) k--;
                    if(temp<0) j++;
                }
            }
        }
        return result;
    }


    public int[] plusOne(int[] digits) {
        int i=digits.length -1;
        for(;i>=0;i--){
            if(digits[i]==9) digits[i]=0;
            else{
                digits[i]++;
                return digits;
            }
        }
        
        int[] newDigits = new int[digits.length+1];
        newDigits[0]=1;
        return newDigits;
    }

    public void moveZeroes(int[] nums) {
        int index=0,cur =0;
        while(cur<nums.length){
            if(nums[cur]!=0)
                nums[index ++]=nums[cur];
            cur++;
        }
        while(index<nums.length){
            nums[index++]=0;
        }

    }

    // public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    //     List<List<Integer>> result = new ArrayList<>();
    //     Arrays.sort(candidates);
    //     combinationSume2helper(result,candidates,0,new ArrayList<Integer>(),target);
    //     return result;
    // }
    // private void combinationSume2helper(List<List<Integer>>  result, int[] nums, int index, List<Integer> temp, int target){
    //     if(target == 0){
    //         result.add(new ArrayList(temp));
    //         return;
    //     }
    //     if(target<0){
    //         return;
    //     }
        
    //     for(int i=index; i<nums.length;i++){
    //         temp.add(nums[i]);
    //         combinationSume2helper(result,nums,i+1,temp,target-nums[i]);
    //         temp.remove(temp.size()-1);
    //     }
    // }

    public boolean exist(char[][] board, String word) {
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(existHelper(board,word,0,i,j)) return true;
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, String word, int index,int i, int j){
        if(i<0||j<0||i>=board.length||j>=board[0].length||board[i][j]!=word.charAt(index))
            return false;
        if(index == word.length()) return true;

        return existHelper(board,word,index+1,i-1,j)||existHelper(board,word,index+1,i,j-1)||existHelper(board,word,index+1,i+1,j)||existHelper(board,word,index+1,i,j+1);
    }

     public boolean isIsomorphic(String s, String t) {
        char[] s1= s.toCharArray();
        char[] t1=t.toCharArray();
        HashMap<Character,Character> map = new HashMap<>();
        for(int i=0;i<s1.length;i++){
            System.out.printf("%d\n",i);
            Character ch = map.put(s1[i],t1[i]);
            if( ch!=null && !(ch.equals(t1[i])) ) return false;
           // if( (!(ch.equals(null))) && ! (ch.equals(t1[i]))) return false;
        }
        return true;
    }

    // public boolean isIsomorphic(String s, String t) {
    //     char[] s1= s.toCharArray();
    //     char[] t1=t.toCharArray();
    //     HashMap<Character,Character> map = new HashMap<>();
    //     for(int i=0;i<s1.length;i++){
    //         if( (map.containsKey(s1[i])) ^ (map.containsValue(t1[i])) ) 
    //             return false;
    //         Character ch = map.put(s1[i],t1[i]);
    //         if(ch!=null && !ch.equals(t1[i])) return false;
            

    //     }
    //     return true;
    // }

    public int minSubArrayLen(int s, int[] nums) {
        //if(nums.length==0) return 0;
        int l=0,r=0,sum=0,min = nums.length+1;
        while(r<nums.length){
            while(r<nums.length && sum<s){
                sum += nums[r++];
            }
            if(sum < s) break;
            while(l<r && sum-nums[l]>=s){
                sum -= nums[l];
                l++;
                
            }
            min = Math.min(min,r-l);
            System.out.printf("l=%d,r=%d,min = %d",l,r,min);

            sum -= nums[l++];
        }
        return min==nums.length+1?0:min;
    }

    public boolean wordPattern(String pattern, String str) {
        
    }


    public static void main(String[] args){
        lc_array_newAccount test = new lc_array_newAccount();
        // int[] nums = {3,2,4};
        // test.twoSum(nums,6);
       // test.removeElement(nums,1);
        // String s1="cat dog dog cat";
        // String[] strs = s1.split(" ");
        // System.out.printf("strs.length: %d,strs[0]= %s\n",strs.length,strs[0]);
        // HashMap<String,String> putReturn = new HashMap<>();
        // System.out.printf("%s,%s\n",putReturn.put("aa","01"),putReturn.put("aa","02"));
        //test.isIsomorphic("paper","title");
        int[] nums = {2,3,1,2,4,3};
        test.minSubArrayLen(7,nums);
    }

}