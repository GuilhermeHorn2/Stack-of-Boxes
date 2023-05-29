package misc;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class main_misc2 {
	
	public static class box{
		
		public int d;
		public int w;
		public int h;
		
		public box(int a,int b,int c){
			d = a;
			w = b;
			h = c;
		}
		public boolean can_stack(box b){
			
			if(d > b.d && w > b.w && h > b.h) {
				return true;
			}
			return false;
		}
		
	}
   
	
	
	public static void main(String[] args) {
	//(a+b)%k = ((a % k)+(b % k)) % k    || ||
	
		ArrayList<box> boxes = new ArrayList<>();
		boxes.add(new box(1,1,1));
		boxes.add(new box(2,1,2));
		boxes.add(new box(2,2,2));
		boxes.add(new box(3,3,3));
		boxes.add(new box(1,1,99));
		System.out.println(max_stack(boxes));
		
		
	}	
	
	private static boolean exist_possible_stack(ArrayList<box> boxes,box b){
		
		for(int i = 0;i < boxes.size();i++) {
			if(b.can_stack(boxes.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private static int max_stack_with_root(box root,ArrayList<box> boxes,int h){
		
		
		
		if(!exist_possible_stack(boxes,root)){
			//base case:no stack possible,so just return h of this stack + the local root
			return h+root.h;
		}
		
		int max = 0;
		for(int i = 0;i < boxes.size();i++){
			
			box b = boxes.get(i);
			if(root.can_stack(b)){
				int x = max_stack_with_root(b,boxes,h+root.h);
				if(x >= max) {
					max = x;
				}
			}
		}
		return max;
		
	}
	
	private static int max_stack(ArrayList<box> boxes) {
		
		ArrayList<Integer> all_stacks = new ArrayList<>();
		for(int i = 0;i < boxes.size();i++){
			all_stacks.add(max_stack_with_root(boxes.get(i),boxes,0));
		}
		all_stacks.sort(Comparator.naturalOrder());
		return all_stacks.get(all_stacks.size()-1);
		
	}
	
	
	} 
	
