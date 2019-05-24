public class Position
{
	PageEntry p;
	int wordIndex;
    public int nonConnectorWordIndex;
	
	public Position(PageEntry pp,int w)
	{
		p=pp;
		wordIndex=w;
	}
	
	PageEntry getPageEntry()
	{	return p;	}
	
	int getWordIndex()
	{	return wordIndex;	}
    
    int getnonConnectorWordIndex()
    {   return nonConnectorWordIndex;   }
	
}
