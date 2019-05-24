public class InvertedPageIndex
{
    static MyHashTable record=new MyHashTable();
    static MySet<String> page_set=new MySet<String>();
    static float total_pages=0;
    
    public void addPage(PageEntry p)
    {
        if(page_set.IsMember(p.pagename))
        {
            System.out.println("Error: Pagename "+p.pagename+" already exists");
            return;
        }
        
        page_set.addElement(p.pagename);
        total_pages++;
        
        PageIndex pid=p.pid;
        MyLinkedList<WordEntry> word_list=pid.getWordEntries();
        node<WordEntry> it=word_list.front;
        while(it!=null)
        {
            record.addPositionsForWord(it.data);
            it=it.after;
        }
    }
    
    
    
    static MySet<PageEntry> getPagesWhichContainPhrase(String str[])
    {
        MySet<PageEntry> ans=new MySet<PageEntry>();
        MySet<PageEntry> p=getPagesWhichContainWord(str[0]);
        node<PageEntry> it=p.set_list.front;
        
        while(it!=null)
        {
            WordEntry w=it.data.pid.getWordEntry(str[0]);
            if(it.data.PhraseOccurence(w.tree.root,str)>0)
                ans.addElement(it.data);
            it=it.after;
        }
        return ans;
    }
    
    static MySet<PageEntry> getPagesWhichContainWord(String str)
    {   return record.getPagesWhichContainWord(str);    }
    
    WordEntry findWord(String str)
    {   return record.findWord(str);    }
    
    Boolean isWebpage(String pagename)
    {  return page_set.IsMember(pagename);   }
    
    
    
    
    
}
