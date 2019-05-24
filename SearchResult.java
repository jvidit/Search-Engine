import java.lang.*;


public class SearchResult implements Comparable<SearchResult>
{
    PageEntry page;
    Float relevance;
    public SearchResult(PageEntry p,float r)
    {
        page=p;
        relevance=r;
    }
    
    public PageEntry getPageEntry()
    {   return page;    }
    
    public float getRelevance()
    {   return relevance;   }
    
    @Override 
    public int compareTo(SearchResult otherObject)
    {
        Float temp1=this.relevance;
        Float temp2=otherObject.relevance;
        return temp1.compareTo(temp2);
    }
}
