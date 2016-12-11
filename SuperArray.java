//James Smith/Chester Huang
//APCS1 pd5
//HW43 -- Array of Titanium
//2016-12-11

/*==================================================
  class SuperArray version 2.0
  Wrapper class for array. Facilitates resizing, 
  expansion on-demand, and read/write capability on elements.
  ==================================================*/

public class SuperArray implements ListInt 
{
    private int[] _data;  //underlying container structure
    private int _lastPos; //marker for last meaningful value
    private int _size;    //number of meaingful values

    //default constructor
    //initializes 10-item array
    public SuperArray() 
    { 
	_data = new int[10];
	_lastPos = -1;
	_size = 0;	
    }


    //output array in [a,b,c] format
    //eg, for int[] a = {1,2,3} ...
    //toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }


    //double capacity of this instance of SuperArray 
    private void expand() 
    { 
	int[] temp = new int[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }


    //accessor method -- return value at specified index
    public int get( int index ) 
    {
	return _data[index];
    }


    //mutator method -- set index to newVal, return old value at index
    public int set( int index, int newVal ) 
    {
 	int temp = _data[index];
	_data[index] = newVal;
	return temp;
    }



    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( int newVal ) 
    { 
	//first expand if necessary
	if ( _size >= _data.length )
	    expand(); 
	_data[_lastPos+1] = newVal;
	_lastPos++;
	_size++;
    }


    //inserts an item at index
    //shifts existing elements (starting at index) right 1 slot
    public void add( int index, int newVal )
    {
	//first expand if necessary
	if ( _size >= _data.length )
	    expand();
	//traverse R->L, shifting elements to right 1 slot
	for( int i = _size; i > index; i-- ) {
	    _data[i] = _data[i-1]; 
	} 
	_data[index] = newVal;
	_lastPos++;
	_size++;
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) 
    { 
	for( int i=index; i < _size - 1; i++ ) {
	    _data[i] = _data[i+1];
	}
	_data[_size-1] = 0; //unnecessary
	_size--;
	_lastPos--;
    }


    //return number of meaningful items in _data
    public int size() 
    { 
	return _size;
    }


    //main method for testing
    public static void main( String[] args ) 
    {
        ListInt salad = new SuperArray();
	System.out.println("Printing empty SuperArray salad...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());
	    

	salad.add(5);
	salad.add(4);
        salad.add(3);
        salad.add(2);
        salad.add(1);

	System.out.println("Printing populated SuperArray salad...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());

        salad.remove(3);
	System.out.println("Printing SuperArray salad post-remove...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());
        salad.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());

        salad.add(3,99);  //Q: Significance of this test call?
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());
        salad.add(2,88);
	System.out.println("Printing SuperArray salad post-insert...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());
        salad.add(1,77);
	System.out.println("Printing SuperArray salad post-insert...");
	System.out.println(salad);
	System.out.println("Printing SuperArray salad's size:");
	System.out.println(salad.size());

	System.out.println("Expecting error on add:");
	salad.add(1000,3);
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }//end main()

}//end class SuperArray
