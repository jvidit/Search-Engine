import java.util.*;

public class SearchEngine
{
    InvertedPageIndex ipi;
    
    public SearchEngine()
    {   ipi=new InvertedPageIndex();    }
    
    String toSingle(String s)
    {
        if(s.equals("stacks"))
            return "stack";
        if(s.equals("structures"))
            return "structure";
        if(s.equals("applications"))
            return "application";
        return s;
    }

    
    void performAction(String actionMessage)
    {
        
        if(actionMessage.equals(""))
            return ;
        String[] arr=actionMessage.split(" ");
        
        
        String s=arr[0],x,y="";
        x=arr[1];
        if(arr.length==3)
            y=arr[2];
        
        
        

    
    
        if(s.equals("addPage"))
        {
            try
            {
                PageEntry pentry=new PageEntry(x);
                ipi.addPage(pentry);
                return;
            }
            catch(IllegalArgumentException e)
            {   System.out.println("File " + x + " not found");
                return;
            }
            
        }
    
        if(s.equals("queryFindPagesWhichContainWord"))
        {
            x=x.toLowerCase();
            x=toSingle(x);
            MySet<PageEntry> set=ipi.getPagesWhichContainWord(x);
            if(set.IsEmpty())
            {
                System.out.println("No webpage contains word "+x);
                return;
            }
            MyLinkedList<PageEntry> list=set.set_list;
            node<PageEntry> it=list.front;
            String ans="";
            while(it.after!=null)
            {
                ans=ans+it.data.pagename+", ";
                it=it.after;
            }
            ans+=it.data.pagename;
            System.out.println(ans);
            return;
        }
    
        if(s.equals("queryFindPositionsOfWordInAPage"))
        {
            x=x.toLowerCase();
            x=toSingle(x);
            WordEntry w=ipi.findWord(x);
            
            if(ipi.isWebpage(y)==false)
            {
                System.out.println("Webpage " + y + " not found");
                return;
            }

            if(w==null)
            {
                System.out.println("Webpage " + y + " does not contain word " + x);
                return;
            }
            
            
            
            node<Position> it=w.positions.front;
            String ans="";
            while(it.after!=null)
            {
                if(it.data.getPageEntry().pagename.equals(y))
                    ans=ans + it.data.getWordIndex() + ", ";
                it=it.after;
            }
            if(it.data.getPageEntry().pagename.equals(y))
                ans=ans + it.data.getWordIndex();
            else if(ans.equals("")==false)
                ans=ans.substring(0,ans.length()-2);
            
            
            if(ans.equals(""))
                ans="Webpage " + y + " does not contain word " + x;
            System.out.println(ans);
            return;
        }
        
        for(int i=1;i<arr.length;i++)
        {
            arr[i]=arr[i].toLowerCase();
            arr[i]=toSingle(arr[i]);
        }
        
        if(s.equals("queryFindPagesWhichContainAllWords"))
        {
            MySet<PageEntry> base=ipi.getPagesWhichContainWord(arr[1]);
            MySet<SearchResult> ans=new MySet<SearchResult>();
            if(base==null)
            {
                System.out.println("No webpage has all the words");
                return;
            }
            
            
            node<PageEntry> it=base.set_list.front;
            while(it!=null)
            {
                int present=1;
                for(int i=2;i<arr.length;i++)
                {
                    if(it.data.pid.getWordEntry(arr[i])==null)
                    {
                        present=0;
                        break;
                    }
                }
                if(present==1)
                {
                    SearchResult result=new SearchResult(it.data,it.data.getRelevanceOfPage(Arrays.copyOfRange(arr,1,arr.length),false));
                    ans.addElement(result);
                }
                it=it.after;
            }
            if(ans.IsEmpty())
            {
                System.out.println("No webpage has all the words");
                return;
            }
            
            ArrayList<SearchResult> ans_list=MySort.sortThisList(ans);
            for(int i=0;i<ans_list.size()-1;i++)
            {
                System.out.print(ans_list.get(i).getPageEntry().pagename+", ");
                //System.out.println(ans_list.get(i).getPageEntry().pagename+" "+ans_list.get(i).getRelevance());
            }
            System.out.println(ans_list.get(ans_list.size()-1).getPageEntry().pagename);
            //System.out.println(ans_list.get(ans_list.size()-1).getPageEntry().pagename+" "+ans_list.get(ans_list.size()-1).getRelevance());
        }
        
        
        if(s.equals("queryFindPagesWhichContainAnyOfTheseWords"))
        {
            MySet<SearchResult> ans=new MySet<SearchResult>();
            MySet<PageEntry> all_pages=new MySet<PageEntry>();
            
            for(int i=1;i<arr.length;i++)
                all_pages=all_pages.Union(ipi.getPagesWhichContainWord(arr[i]));
            
            if(all_pages.IsEmpty())
            {
                System.out.println("No webpage contains either of the words");
                return;
            }
            
            node<PageEntry> it=all_pages.set_list.front;
            while(it!=null)
            {
                SearchResult result=new SearchResult(it.data,it.data.getRelevanceOfPage(Arrays.copyOfRange(arr,1,arr.length),false));
                ans.addElement(result);
                it=it.after;
            }
            
            ArrayList<SearchResult> ans_list=MySort.sortThisList(ans);
            for(int i=0;i<ans_list.size()-1;i++)
            {
                System.out.print(ans_list.get(i).getPageEntry().pagename+", ");
                //System.out.println(ans_list.get(i).getPageEntry().pagename+" "+ans_list.get(i).getRelevance());
            }
            System.out.println(ans_list.get(ans_list.size()-1).getPageEntry().pagename);
            //System.out.println(ans_list.get(ans_list.size()-1).getPageEntry().pagename+" "+ans_list.get(ans_list.size()-1).getRelevance());
        }
        
        if(s.equals("queryFindPagesWhichContainPhrase"))
        {
            MySet<PageEntry> all_pages=ipi.getPagesWhichContainPhrase(Arrays.copyOfRange(arr,1,arr.length));
            MySet<SearchResult> ans=new MySet<SearchResult>();

            if(all_pages.IsEmpty())
            {
                System.out.println("No webpage contains the phrase");
                return;
            }
            
            node<PageEntry> it=all_pages.set_list.front;
            while(it!=null)
            {
                SearchResult result=new SearchResult(it.data,it.data.getRelevanceOfPage(Arrays.copyOfRange(arr,1,arr.length),true));
                ans.addElement(result);
                it=it.after;
            }
            
            ArrayList<SearchResult> ans_list=MySort.sortThisList(ans);
            for(int i=0;i<ans_list.size()-1;i++)
            {
                System.out.print(ans_list.get(i).getPageEntry().pagename+", ");
                //System.out.println(ans_list.get(i).getPageEntry().pagename+" "+ans_list.get(i).getRelevance());
            }
            System.out.println(ans_list.get(ans_list.size()-1).getPageEntry().pagename);
            //System.out.println(ans_list.get(ans_list.size()-1).getPageEntry().pagename+" "+ans_list.get(ans_list.size()-1).getRelevance());

        }
        
        
        
    }
}
