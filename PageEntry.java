import java.util.*;
import java.io.*;
import java.lang.*;

public class PageEntry
{
    String pagename;
    PageIndex pid;
    float no_of_words=0;
    
    boolean isStop(String s)
    {
        String[] arr={"a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of", "or", "and", "does", "will", "whose"};
        for(int i=0;i<arr.length;i++)
        {
            if(s.equals(arr[i]))
                return true;
        }
        return false;
    }
    
    
    
    String rep(String str)
    {
        String[] arr={"{","}","[","]","<",">","=","(",")",".",",",";","'","\"","?","#","!","-",":"};
        for(int i=0;i<arr.length;i++)
            str=str.replace(arr[i]," ");
        return str;
    }
    
       
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
    
    public PageEntry(String page)
    {
        
        pagename=page;
        pid=new PageIndex();
        int i=1,count_nonConnnector=1;
        try
        {
            Scanner sc = new Scanner(new File("./webpages/"+pagename));
            while(sc.hasNext())
            {
               
                String s = sc.next();
                s=rep(s);
                String[] parts=s.split(" ");
                for(int j=0;j<parts.length;j++)
                {
                    Position temp=new Position(this,i);
                    
                    parts[j]=parts[j].toLowerCase();
                    parts[j]=toSingle(parts[j]);
                    if(parts[j].equals(""))
                        continue;
                    
                    if(isStop(parts[j])==false)
                    {
                        temp.nonConnectorWordIndex=count_nonConnnector;
                        pid.addPositionForWord(parts[j],temp);
                        count_nonConnnector++;
                    }
                    i++;
                }
            }
            no_of_words=count_nonConnnector-1;
        }
        catch(FileNotFoundException fnfe){ throw new IllegalArgumentException(); }
    }
    
    
    public float getTermFrequency(String word)
    {
        float num=0;
        node<WordEntry> it=pid.wordentries.front;
        while(it!=null)
        {
            if(it.data.str.equals(word))
                num+=it.data.size();
            it=it.after;
        }
        //System.out.println("tf "+pagename+" "+word+" "+num/no_of_words+" "+num+" "+no_of_words);
        return num/no_of_words;
    }
    
    
    
    float getRelevanceOfPage(String str[],boolean doTheseWordsRepresentAPhrase)
    {
        float ans=0;
        if(doTheseWordsRepresentAPhrase)
        {
            
            float k=(float)str.length;
            
            WordEntry w=pid.getWordEntry(str[0]);
            if(w==null)
                return 0;
            
            TreeNode t=w.tree.root;
            float m=PhraseOccurence(t,str);
            
            
            float nwp=(float)InvertedPageIndex.getPagesWhichContainPhrase(str).size;
            if(nwp==0)
                return 0;
            ans=(m/(no_of_words-(k-1)*m)) * (float)Math.log(InvertedPageIndex.total_pages/nwp);
            
            
        }
        else
        {
            for(int i=0;i<str.length;i++)
            {
                float nwp=(float)InvertedPageIndex.getPagesWhichContainWord(str[i]).size;
                if(nwp!=0)
                {
                    ans+= ( Math.max((float)Math.log(InvertedPageIndex.total_pages/nwp ),0.00000001) *  getTermFrequency(str[i]));
                    //System.out.println("tf "+pagename+" "+str[i]+" "+getTermFrequency(str[i]));
                }
            
            }
        }
        return ans;
    }

    
    float PhraseOccurence(TreeNode t,String str[])
    {
        if(t==null)
            return 0;
        return isPhrasePresent(t.p.nonConnectorWordIndex,str)+PhraseOccurence(t.left,str)+PhraseOccurence(t.right,str);
    }
    
    float isPhrasePresent(int ind,String str[])
    {
        for(int i=1;i<str.length;i++)
        {
            
            WordEntry temp=pid.getWordEntry(str[i]);
            if(temp==null)
                return 0;
            if(temp.tree.find(ind+i)==false)
                return 0;
        }
        return 1;
    }
    
    
    PageIndex getPageIndex()
    {   return pid; }
    
}

