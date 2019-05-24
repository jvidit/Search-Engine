import java.util.*;



public class MyLinkedList<X>{
	public node<X> front;
	
	
	public MyLinkedList()
	{	front=null;		}
	
	public boolean IsEmpty()
	{	return (front==null);	}
	
	public void Insert(X d)
	{
		node<X> newnode=new node<X>(d);
		
		if(front==null)
			front=newnode;
		else
		{
			front.before=newnode;
			newnode.after=front;
			front=newnode;
		}
	}
	
	public void Remove(X d)
	{
		node<X> temp=front;
		while(temp!=null)
		{
			if(temp.data.equals(d))
			{
                if(temp==front)
                {
                    front=front.after;
                    if(front!=null)
                        front.before=null;
                    return;
                }
				if(temp.before!=null)
					temp.before.after=temp.after;
				if(temp.after!=null)
					temp.after.before=temp.before;
				return;
			}
			temp=temp.after;
		}
		System.out.println("Data element not present");
	}
	
	public void printll()
	{
		node<X> temp=front;
		while(temp!=null)
		{
			System.out.println(temp.data+" ");
			temp=temp.after;
		}
		if(this.IsEmpty())
			System.out.println("Nothing to display");
	}
	
}
