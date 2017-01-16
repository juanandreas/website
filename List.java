/*
List.java
Juan Andreas
jandreas
PA1
*/

class List{
	
	private class Node{
		// Fields
		int data;
		Node prev;
		Node next;
		
		// Constructor
		Node(int data){
			this.data = data;
			prev = null;
			next = null;
		}
		
		// toString()
		public String toString(){
			return String.valueOf(data);
		}
	}
	
	// Fields
	private Node front;
	private Node back;
	private Node cursorE;
	private int length;
	private int cursorI;
	
	// Constructor
	public List(){
		front = back = null;
		length = 0;
		cursorI = -1;
	}
	
	// Access functions -------------------------------------------------------
	
	// length()
	// returns length of List
	int length(){
		return length;
	}
	
	// index()
	// If cursor is defined, returns index of the cursor element
	// otherwise return -1
	int index(){
		return cursorI;
	}
	
	// front()
	// Returns front element
	// Pre: length()>0
	int front(){
		if(length <= 0){
			throw new RuntimeException("Pre: length>0");
		}
		return front.data;
	}
	
	// back()
	// Returns back element 
	// Pre: length()>0
	int back(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		return back.data;
	}
	
	// get()
	// Returns cursor element
	// Pre: length()>0, index()>=0
	int get(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.index() <= -1){
			throw new RuntimeException("Pre: index()=>0");
		}
		return cursorE.data;
	}
	
	// equals()
	// Returns true if this List and L are the same int seq.
	boolean equals(List L){
		boolean eq = true;
		Node N, M;
		N = this.front;
		M = L.front;
		if(this.length == L.length){
			while(eq && N!=null){
				eq = (N.data == M.data);
				N = N.next;
				M = M.next;
			}
			return eq;
		}else{
			return false;
		}
	}
	
	// Manipulation procedures-------------------------------------------------
	
	// clear()
	// Resets this List to its original empty state
	void clear(){
		Node front = null;
		Node back = null;
		cursorE = null;
		length = 0;
		cursorI = -1;
	}
	
	// moveFront()
	// Places cursor under front element
	// Pre: length != 0
	void moveFront(){
		if(length > 0){
			cursorE = this.front;
			cursorI = 0;
		}
	}
	
	// moveBack()
	// Places cursor under back element
	// Pre: length != 0
	void moveBack(){
		if(length > 0){
			cursorE = this.back;
			cursorI = length-1;
		}
	}
	
	// movePrev()
	// Moves cursor one step toward front of list
	// cursor must be defined, does nothing otherwise
	// cursor falls off list when called on front
	void movePrev(){
		if(this.index() != -1 && this.index() > 0){
			cursorE = cursorE.prev;
			cursorI--;
		}else if(this.index() != -1 && this.index() == 0){
			cursorE = null;
			cursorI = -1;
			//TERMINATE LOOP
		}
	}
	
	// moveNext()
	// Moves cursor one step toward back of list
	// cursor must be defined, does nothing otherwise 
	// cursor falls of list when called on back
	void moveNext(){
		if(this.index() != -1 && this.get() != this.back()){
			cursorE = cursorE.next;
			cursorI++;
		}else if(this.index() != -1 && this.get() == this.back()){
			cursorE = null;
			cursorI = -1;
			//TERMINATES LOOP
		}
	}
	
	// prepend()
	// Insert new element into list before front element
	void prepend(int data){
		Node N = new Node(data);
		if(length == 0){
			front = back = N;
		}else{
			Node P;
			P = front;
			front.prev = N;
			front = N;
			N.next = P;
			P.prev = N;
		}
		length++;
	}
	
	// append()
	// Inserts new element into list after back element
	void append(int data){
		Node N = new Node(data);
		// if List is empty 
		if(length == 0){
			front = back = N;
		}else{
			// if List non-empty, insert after back element
			Node P;
			P = back;
			back.next = N;
			back = N;
			N.prev = P;
			P.next = N;
		}
		length++;
	}
	
	// insertBefore()
	// Insert new element before cursor
	// Pre: length()>0, index()>=0
	void insertBefore(int data){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.index() < 0){
			throw new RuntimeException("Pre: index()>=0");
		}
		Node N = new Node(data);
		Node M = cursorE;
		Node P = M.prev; // Node points to the element before cursor
		if(this.index() == 0 && cursorE != null){
			this.prepend(data);
		}else{
			M.prev = N;
			P.next = N;
			N.next = M;
			N.prev = P;
			length++;
		}
		cursorI++;
	}
	
	// insertAfter()
	// Insert new element after cursor
	// Pre: length()>0, index()>=0
	void insertAfter(int data){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.index() < 0){
			throw new RuntimeException("Pre: index()>=0");
		}
		Node N = new Node(data);
		Node M = cursorE;
		Node P = M.next;
		if(this.index() == this.length()-1 && cursorE != null){
			this.append(data);
		}else{
			M.next = N;
			P.prev = N;
			N.next = P;
			N.prev = M;
			length++;
		}
	}
	
	// deleteFront()
	// Deletes the front element
	// Pre: length()>0
	void deleteFront(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.length > 1){
			front = front.next;
			front.prev = null;
		}else{
			front = back = null;
		}
		length--;
	}
	
	// deleteBack()
	// Deletes the back element
	// Pre: length()>0, index()>=0
	void deleteBack(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.index() < 0){
			throw new RuntimeException("Pre: index()>=0");
		}
		if(this.length > 1){
			back = back.prev;
			back.next = null;
		}else{
			front = back = null;
		}
		length--;
	}
	
	// delete()
	// Deletes the cursor element, making cursor undef
	// Pre: length()>0, index()>=0
	void delete(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.index() < 0){
			throw new RuntimeException("Pre: index()>=0");
		}
		Node M = cursorE.prev;
		M.next = cursorE.next;
		cursorE = null;
		length--;
	}
	
	// Other methods ----------------------------------------------------------
	
	// toString()
	// Overrides Object's toString method
	// Returns a String representation of this List
	public String toString(){
		Node N = front;
		String s = "";
		while(N != null){
			s += N.data+" ";
			N = N.next;
		}
		return s;
	}
	
	// copy()
	// Returns a new List representing of the same integer sequence of this List
	// Post: cursor in List is undefined. List is unchanged
	List copy(){
		List copy = new List();
		Node N = front;
		for(int i = 0; i<length; i++){
			int n = N.data;
			copy.append(n);
			N = N.next;
		}
		//Post:
		cursorI = -1;
		cursorE = null;
		
		return copy;
	}
	
	// concat()
	// Returns a new List which is concatenation of this List followed by L
	// Post: cursor is undefined. State of this List and L are unchanged
	List concat(List L){
		List concat = new List();
		Node M = this.front;
		Node N = L.front;
		int tLen = this.length + L.length;
		for(int i = 0; i<tLen; i++){
			if(i<this.length){
				concat.append(M.data);
				M = M.next;
			}else if(i >= this.length){
				concat.append(N.data);
				N = N.next;
			}
		}
		//Post:
		cursorI = -1;
		cursorE = null;
		
		return concat;
	}
}