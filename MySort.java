import java.util.*;

public class MySort
{
    
    static public void merge(ArrayList<SearchResult> arr, int l, int m, int r)
    {
        
        int n1 = m - l + 1;
        int n2 = r - m;
        
        
        ArrayList<SearchResult> L=new ArrayList<SearchResult>();
        ArrayList<SearchResult> R=new ArrayList<SearchResult>();
        
        
        for (int i=0; i<n1; i++)
            L.add(arr.get(l+i));
        for (int j=0; j<n2; j++)
            R.add(arr.get(m + 1+ j));
        
        
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2)
        {
            if ( L.get(i).compareTo(R.get(j)) > 0)
            {
                arr.set(k,L.get(i));
                i++;
            }
            else
            {
                arr.set(k,R.get(j));
                j++;
            }
            k++;
        }
        
        while (i < n1)
        {
            arr.set(k,L.get(i));
            i++;
            k++;
        }
    
        while (j < n2)
        {
            arr.set(k,R.get(j));
            j++;
            k++;
        }
    }
    
   
    static public void sort(ArrayList<SearchResult> arr, int l, int r)
    {
        if (l < r)
        {
            
            int m = (l+r)/2;
            
            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r); 
        } 
    }
    
    
    
    
    public static ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSearchResultEntries)
    {
        ArrayList<SearchResult> arr=new ArrayList<SearchResult>();
        node<SearchResult> it=listOfSearchResultEntries.set_list.front;
        while(it!=null)
        {
            arr.add(it.data);
            it=it.after;
        }
        
        sort(arr,0,arr.size()-1);
        return arr;
    }
}
