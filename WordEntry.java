public class WordEntry
{
    public String str;
    AVLTree tree=new AVLTree();
    MyLinkedList<Position> positions=new MyLinkedList<Position>();
    
    
    public WordEntry(String word)
    {   str=word;   }
    
    
    public WordEntry(WordEntry w)
    {
        str=w.str;
        node<Position> it=w.positions.front;
        while(it!=null)
        {
            positions.Insert(it.data);
            it=it.after;
        }
    }
    
    void addPosition(Position position)
    {
        positions.Insert(position);
        tree.add(position);
    }
    
    void addPositions(MyLinkedList<Position> temp)
    {
        node<Position> it=temp.front;
        while(it!=null)
        {
            positions.Insert(it.data);
            tree.add(it.data);
            it=it.after;
        }
    }
    
    MyLinkedList<Position> getAllPositionsForThisWord()
    {   return positions;   }
    
    float size()
    {
        float ans=0;
        node<Position> it=positions.front;
        while(it!=null)
        {
            ans++;
            it=it.after;
        }
        return ans;
    }
    
}
