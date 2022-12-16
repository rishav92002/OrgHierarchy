class MyStack  {
	 int capacity=1;
	int front =0;
  Node [] stack = new Node[capacity];
   int top=-1;
	
	public  void push(Node p){
		if(top==capacity-1) {
		newStack(capacity,p);
		capacity = capacity*2;
        }else {
			top++;
			stack[top]=p;
		}
		}
	public  void newStack(int capacity, Node i) {
		Node[] newstack =  new Node[capacity*2];
		for(int j=0; j<capacity; j++) {
			newstack[j] = stack[j];
			}
		stack = newstack;
		top++;
		stack[top]= i;
		}

	public boolean isEmpty() {
		if((top-front+1)==0) {
			return true;
		}
		return false;
	}
	public Node geti(int i) {
		return stack[i];
		
	}
	public  Node remove() {
		Node temp= stack[front];
		Node temp1 = new Node(0,0);
		stack[front]=temp1;
		front++;
		return temp;
		//front++;
	}
	
	public int size() {
		
		return top-front+1;
	}
	public void seti(int i,int j) {
		MyStack nch = new MyStack();
		for(int k=i;k<j-1;k++) {
			stack[k]=stack[k+1];
		}
		stack[j-1]= new Node(0,0);;
		top--;
		
		
	}

}
// Tree node
class Node {
	
		 int id, level;
		 Node parent;
		 MyStack child = new MyStack();
		 
		 public Node(int id, int level) {
			 //this.child = child;
			 this.id= id;
			 this.level= level;
		 }
	}
 public class OrgHierarchy implements OrgHierarchyInterface{

//root node
	int len=0;
    Node root;

public static Node levelordersearchid(Node root,int id) {
	Node ans= root;

	if(root.id==id) {
		return root;
	}
	MyStack st = new MyStack();
    st.push(root); // Enqueue root
    while (!st.isEmpty())
    {
        int n = st.size();
 
        // If this node has children
        while (n > 0)
        {
            // Dequeue an item from queue
            // and print it
            Node p = st.remove();
            if(p.id==id) {
            	return p;
            }
 
            // Enqueue all children of
            // the dequeued item
            if(p.child==null) {
            	continue;
            }
            for (int i = 0; i < p.child.size(); i++) {
                st.push(p.child.geti(i));
            }
            n--;
        }
         
        
        
    }
    return ans; 
    
    }
public boolean isEmpty(){
	
	if(root==null) {
		return true;
	}
	return false;
} 

public int size(){
	
	return len;
}

public int level(int id) throws IllegalIDException{
	
	if(root.id==id) {
		return 1;
	}
	else {
		Node temp = levelordersearchid(root,id);
		if(temp.id==root.id) {
			throw new IllegalIDException("invalid id"); 
		}else {
		return temp.level;
		}
		
	}
	
} 

public void hireOwner(int id) throws NotEmptyException{
	
	if(root!=null) {
		throw new NotEmptyException("organisation is not empty"); 
	}else {
	Node temp = new Node(id,1);
	root = temp;
	}
	len++;
	root.parent=null;
}

public void hireEmployee(int id, int bossid) throws IllegalIDException{
	if(root.id==id) {
		throw new IllegalIDException("id already exist");
	}
	Node temp2= levelordersearchid(root,id);
	if(temp2.id== id) {
		throw new IllegalIDException("id already exist");	
	}
	Node temp = levelordersearchid(root,bossid);
	int loc= temp.level;
	Node temp1 = new Node(id,loc+1);
	temp.child.push(temp1);
	temp1.parent= temp;
	len++;
} 

public void fireEmployee(int id) throws IllegalIDException{
	if(root.id==id) {
		throw new IllegalIDException("organisation is not empty");
	}
	Node temp2= levelordersearchid(root,id);
	if(temp2.id!= id) {
		throw new IllegalIDException("id donot exist");	
	}
	
	MyStack st= new MyStack();  
	st.push(root);
	while(!st.isEmpty()) {
		int n= st.size();
		while(n>0) {
			Node p = st.remove();
			int x1 = p.id;
			if(p.child==null) {
				continue;
			}
			int x2 = p.child.size();
			for(int i=0; i<x2; i++) {
				int x3 = p.child.geti(i).id;
				if(p.child.geti(i).id==id) {
					if(p.child.size()==1) {
						MyStack nc = new MyStack();
						int x4=nc.size();//to be deleted
						p.child= nc;
						len--;
						return;
					}else {
						int k= p.child.size();
						MyStack nc = new MyStack();
						for(int g=0; g<p.child.size(); g++) {
							int id2 =p.child.geti(i).id; 
							if(g!=i) {
								nc.push(p.child.geti(g));
							}
						}
						p.child = nc;
						
						len--;
						int length=len;
						return;
						}
						
				}else {
						st.push(p.child.geti(i));
					}
				}
			n--;
					
			
		}
	}
	
	
	
}
public void fireEmployee(int id, int manageid) throws IllegalIDException{
	if(root.id==id) {
		throw new IllegalIDException("notapplicable");
	}
	Node t1= levelordersearchid(root,id);
	if(t1.id!= id) {
		throw new IllegalIDException("id donot exist");	
	}
	Node t2= levelordersearchid(root,manageid);
	if(t2.id!= manageid) {
		throw new IllegalIDException("id donot exist");	
	}
	
	Node temp = levelordersearchid( root,manageid);
	 int x12= temp.level;
	 int x13 = temp.level;
	MyStack st= new MyStack();
	st.push(root);
	while(!st.isEmpty()) {
		int n= st.size();
		while(n>0) {
			Node p = st.remove();
			int y = p.id;
			if(p.child==null) {
				n--;
				continue;
			}
			int y1 = p.child.size();
			for(int i=0; i<p.child.size(); i++) {
				int x23= p.child.geti(i).id;
				if(p.child.geti(i).id==id) {
					int x21= p.child.geti(i).child.size();
					if(x21!=0) {
					for(int x=0; x<p.child.geti(i).child.size(); x++) {
						int id1 = p.child.geti(i).child.geti(x).id;
						temp.child.push(p.child.geti(i).child.geti(x));
						p.child.geti(i).child.geti(x).parent= temp;
						
						int sz1 = temp.child.size();
						int idd = temp.child.geti(sz1-1).id;
					}
					}
					if(p.child.size()==1) {
						MyStack nc = new MyStack(); 
						p.child= nc;
						
						len--;
						return;
					}else {
						int k= p.child.size();
						MyStack nc1 = new MyStack();
						for(int g=0; g<p.child.size(); g++) {
							if(g==i) {
								continue;
							}
							nc1.push(p.child.geti(g));
							
						}
						
						p.child = nc1;
						int xx= p.child.size();
						len--;
						return;
						}
						
					}else {
						st.push(p.child.geti(i));
					}
				}
			n--;
					
			
		}
	}
} 

public int boss(int id) throws IllegalIDException{
	if(root.id==id) {
		throw new IllegalIDException("notapplicable");
	}
	Node t1= levelordersearchid(root,id);
	if(t1.id!= id) {
		throw new IllegalIDException("id donot exist");	
	}

	Node temp = levelordersearchid( root, id);
	return temp.parent.id;
	
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException{
	
	if(root.id==id1|| root.id==id2) {
		return -1;
	}
	Node temp = levelordersearchid( root, id1);
	if(temp.id!= id1) {
		throw new IllegalIDException("id donot exist");	
	}
  Node temp1 = levelordersearchid( root, id2);
  if(temp1.id!= id2) {
		throw new IllegalIDException("id donot exist");	
	}
	int l1 = temp.id;
	int l2 = temp1.id;
	MyStack st1 = new MyStack();
	MyStack st2 =new MyStack();
	
	while(temp.parent!=null) {
		Node x = temp.parent;
		int l3 = x.id;
		st1.push(temp.parent);
		temp= temp.parent;
	}
	while(temp1.parent!=null) {
		Node x = temp1.parent;
		int l3 = x.id;
		st2.push(temp1.parent);
		temp1= temp1.parent;
	}
	int l5 = st1.size();
	int l6 = st2.size();
	int ans=0;
	

	if(l5<l6) {
		
	
	 while(l5>0) {
		 if(st1.geti(l5-1).id==st2.geti(l6-1).id) {
			ans = st1.geti(l5-1).id; 
		 }else {
			 break;
		 }
		 l5--;
		 l6--;
		 
	 }
	}else {
		 while(l6>0) {
			 if(st1.geti(l5-1).id==st2.geti(l6-1).id) {
				ans = st2.geti(l6-1).id; 
			 }else {
				 break;
			 }
			 l5--;
			 l6--;
			 
		 }
	}
	 return ans;
	 
}

public String toString(int id) throws IllegalIDException{
	Node temp = levelordersearchid( root, id);
	if(temp.id!= id) {
		throw new IllegalIDException("id donot exist");	
	}
	String ans = id+" ";
	
	
	MyStack st = new MyStack();
    st.push(temp); // Enqueue root
    while (!st.isEmpty())
    {
        int n = st.size();
        MyStack st1 = new MyStack();
 
        
        while (n > 0)
        {
            // Dequeue an item from queue
            // and print it
            Node p = st.remove();
            int y = p.id;
         // If this node has children
            if(p.child==null) {
            	n--;
            	continue;
            }
          int y1 = p.child.size();
             
           for (int i = 0; i < p.child.size(); i++) {
        	   int x12 = p.child.geti(i).id;
            	st1.push(p.child.geti(i));
                st.push(p.child.geti(i));
            }
            n--;
        }
        int x= st1.size();
        int arr[] =new int[x];
        for(int i=0; i<x;i++) {
        	int x13 = st1.geti(i).id;
        	arr[i]= st1.geti(i).id;
        }
        mergesort ms = new mergesort();
        ms.mergesort(arr, new int[arr.length], 0, arr.length - 1);
        
        for(int i=0; i<x;i++) {
        	ans= ans+arr[i]+" ";
        }
        
        
        }

    return ans;
	
}
class mergesort{
	void mergesort(int[] arr, int[] temp, int x, int y){
        if(x < y){ // base case
            int mid = x + (y - x) / 2; // overflow condition (low + high) / 2;
            mergesort(arr, temp, x, mid);
            mergesort(arr, temp, mid + 1, y);
            merge(arr, temp, x, mid, y);
        }
    }

     void merge(int[] arr, int[] temp, int x, int mid, int y) {
        for(int i = x; i <= y; i++){
            temp[i] = arr[i];
        }
        int i =x; // traverse left sorted subarray
        int j = mid + 1; // traverse right sorted subarray
        int k = x; // will merge both arrays into original array (arr)

        while(i <= mid && j <= y){
            if(temp[i] <= temp[j]){
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while(i <= mid){
            arr[k] = temp[i];
            k++;
            i++;
        }
    }
	
	    
	 
}
}
 



