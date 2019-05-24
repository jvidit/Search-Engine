public class MySet<X>{
	
    int size=0;
    
	public MyLinkedList<X> set_list;
	
	public MySet()
	{	set_list=new MyLinkedList<X>();	}
    
    public MySet(MySet<X> a)
    {	this.set_list=a.set_list;	}

	
	public boolean IsEmpty()
	{	return set_list.IsEmpty();		}

		
	public boolean IsMember(X o)
	{
		node<X> temp=set_list.front;
		while(temp!=null)
		{
			if(temp.data.equals(o))
				return true;
			temp=temp.after;
		}
		return false;
	}
	
	public void addElement(X o)
	{
        if(IsMember(o)==false)
        {
            set_list.Insert(o);
            size++;
        }
    }
	
	public void Delete(X o)
	{
        if(IsMember(o))
        {
            set_list.Remove(o);
            size--;
        }
    }
	
	public MySet<X> Union(MySet<X> a)
	{
		MySet<X> ans=new MySet<X>(a);
		node<X> temp=set_list.front;
		while(temp!=null)
		{
			if(a.IsMember(temp.data)==false)
				ans.addElement(temp.data);
			temp=temp.after;
		}
		return ans;
	}
	
	
	public MySet<X> Intersection(MySet<X> a)
	{
		MySet<X> ans=new MySet<X>();
		node<X> temp=set_list.front;
		while(temp!=null)
		{
			if(a.IsMember(temp.data)==true)
				ans.addElement(temp.data);
			temp=temp.after;
		}
		return ans;
	}

    
    public void printset()
    {   this.set_list.printll();   }
}
