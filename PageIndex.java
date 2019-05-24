public class PageIndex
{
    
    MyLinkedList<WordEntry> wordentries=new MyLinkedList<WordEntry>();
    
    
    public void addPositionForWord(String w, Position p)
    {
        node<WordEntry> temp=wordentries.front;
        while(temp!=null)
        {
            if(temp.data.str.equals(w))
            {
                temp.data.addPosition(p);
                return;
            }
            temp=temp.after;
        }
        WordEntry newptr=new WordEntry(w);
        newptr.addPosition(p);
        wordentries.Insert(newptr);
    }
    
    MyLinkedList<WordEntry> getWordEntries()
    {   return wordentries; }
    
    WordEntry getWordEntry(String word)
    {
        node<WordEntry> it=wordentries.front;
        while(it!=null)
        {
            if(it.data.str.equals(word))
                return it.data;
            it=it.after;
        }
        return null;
    }
    
    
    void print_pageindex()
    {
        node<WordEntry> it=wordentries.front;
        while(it!=null)
        {
            System.out.println("\n\n"+it.data.str);
            node<Position> it2=it.data.positions.front;
            while(it2!=null)
            {
                System.out.print(it2.data.getWordIndex()+" ");
                it2=it2.after;
            }
            
            it=it.after;
        }
    }
    
    
    
}
