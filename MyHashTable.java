class array_node
{
    WordEntry w;
    array_node after;
}


public class MyHashTable
{
    array_node[] records=new array_node[137];
    
    
    private int getHashIndex(String str)
    {
        int s=0;
        for(int i=0;i<str.length();i++)
            s+=(int)str.charAt(i);
        return s%137;
    }

    
    
    void addPositionsForWord(WordEntry input_w)
    {
        
        
        
        String input_str=input_w.str;
        int ind=getHashIndex(input_str);
        
        array_node it=records[ind];
        
        
        
        if(it==null)                                    //first element
        {
            //System.out.println("First element::Adding word " + input_str +" at index "+ind);
            records[ind]=new array_node();
            records[ind].w=new WordEntry(input_w);
            return;
        }
        
        
        while(it.after!=null)                           //traverses till 2nd last element
        {
            
            if(it.w.str.equals(input_str))
            {
                it.w.addPositions(input_w.positions);
                return;
            }
            it=it.after;
        }
        
        
        if(it.w.str.equals(input_str))                  //last element
        {
            it.w.addPositions(input_w.positions);
            return;
        }
        
        
        array_node newptr=new array_node();             //insertion at end
        newptr.w=new WordEntry(input_w);
        it.after=newptr;
        //System.out.println("New Element::Adding word " + input_str +" at index "+ind);

        
    }
    
    
    
    
    MySet<PageEntry> getPagesWhichContainWord(String str)
    {
        MySet<PageEntry> ans=new MySet<PageEntry>();
        int ind=getHashIndex(str);
        
        
        array_node it=records[ind];
        while(it!=null && it.w.str.equals(str)==false)
            it=it.after;
        if(it==null)
            return ans;
        
        node<Position> it2= it.w.positions.front;
        while(it2!=null)
        {
            ans.addElement(it2.data.getPageEntry());
            it2=it2.after;
        }
        
        return ans;
    }
    
    
    WordEntry findWord(String str)
    {
        int ind=getHashIndex(str);
        
        
        array_node it=records[ind];
        while(it!=null)
        {
            if(it.w.str.equals(str))
                return it.w;
            it=it.after;
        }
        return null;
    }
    
    
    void print_words(int n)
    {
        array_node it=records[n];
        while(it!=null)
        {
            System.out.println(it.w.str);
            it=it.after;
        }

    }
    
    
    
}
