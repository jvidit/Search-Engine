import java.lang.*;
import java.util.*;


public class AVLTree
{
    TreeNode root;
    
    public void upd_height(TreeNode t)
    {
        if(t==null)
            return;
        
        if(t.left!=null)
            t.lheight=Math.max(t.left.lheight,t.left.rheight)+1;
        else
            t.lheight=0;
        if(t.right!=null)
            t.rheight=Math.max(t.right.lheight,t.right.rheight)+1;
        else
            t.rheight=0;
    }
    
    public void add(Position pos)
    {
        //inorder(root);
        
        //System.out.println("Adding "+pos.nonConnectorWordIndex);
        TreeNode p=new TreeNode();
        p.p=pos;
        if(root==null)
        {
            root=p;
            return;
        }
        
        
        TreeNode temp=root;
        while(true)
        {
            if(p.p.nonConnectorWordIndex<=temp.p.nonConnectorWordIndex)
            {
                if(temp.left!=null)
                    temp=temp.left;
                else
                {
                    temp.left=p;
                    p.parent=temp;
                    break;
                }
            }
            else
            {
                if(temp.right!=null)
                    temp=temp.right;
                else
                {
                    temp.right=p;
                    p.parent=temp;
                    break;
                }
            }
        }
        
        
        
        TreeNode z=p;
        TreeNode w,x,y,a,b,c,d;
        while(z!=null)
        {
            upd_height(z);
            //System.out.println(z.p.nonConnectorWordIndex+" "+z.lheight+" "+z.rheight);
            if(Math.abs(z.lheight-z.rheight)>=2)
            {
                //System.out.println("Violation");
                w=z.parent;
                if(z.rheight>z.lheight)                             //Case 0
                {
                    
                    y=z.right;
                    if(y.rheight>y.lheight)
                    {
                        x=y.right;                         //Case 0-0  r r
                        b=y.left;
                        
                        
                        z.parent=y;
                        z.right=b; 
                        
                        if(b!=null)
                            b.parent=z;
                        
                        y.left=z;
                        y.right=x;
                        y.parent=w;
                        
                        if(w!=null)
                        {
                            if(z.p.nonConnectorWordIndex<=w.p.nonConnectorWordIndex)
                                w.left=y;
                            else
                                w.right=y;
                        }
                        else
                            root=y;
                        
                        upd_height(x);
                        upd_height(z);
                        upd_height(y);
                        
                    }
                    else                                            //Case 0-1  r l
                    {
                        
                        x=y.left;
                        b=x.left;
                        c=x.right;
                        
                        
                        if(b!=null)
                            b.parent=z;
                        
                        if(c!=null)
                            c.parent=y;
                        
                        x.left=z;
                        x.right=y;
                        x.parent=w;
                        
                        y.left=c;
                        y.parent=x;
                        
                        z.right=b;
                        z.parent=x;
                        
                        if(w!=null)
                        {
                            if(z.p.nonConnectorWordIndex<=w.p.nonConnectorWordIndex)
                                w.left=x;
                            else
                                w.right=x;
                        }
                        else
                            root=x;
                    }
                }
                else                                               //Case 1
                {
                    
                    y=z.left;
                    //System.out.println("Here "+y.p.nonConnectorWordIndex+" "+z.p.nonConnectorWordIndex);
                    if(y.lheight>y.rheight)                        //Case 1-0  l l
                    {
                        x=y.left;
                        c=y.right;
                        
                        if(c!=null)
                            c.parent=z;
                        
                        y.right=z;
                        y.parent=w;
                        
                        z.left=c;
                        z.parent=y;
                        
                        if(w!=null)
                        {
                            if(z.p.nonConnectorWordIndex<=w.p.nonConnectorWordIndex)
                                w.left=y;
                            else
                                w.right=y;
                        }
                        else
                            root=y;
                        
                        
                        //System.out.println("Here "+y.p.nonConnectorWordIndex+" "+z.p.nonConnectorWordIndex+" "+x.p.nonConnectorWordIndex);
                        //inorder(root);
                    }
                    else                                           //Case 1-1   l r
                    {
                        x=y.right;
                        b=x.left;
                        c=x.right;
                        
                        if(b!=null)
                            b.parent=y;
                        
                        if(c!=null)
                            c.parent=z;
                        
                        x.left=y;
                        x.right=z;
                        x.parent=w;
                        
                        y.right=b;
                        y.parent=x;
                        
                        z.left=c;
                        z.parent=x;
                        
                        if(w!=null)
                        {
                            if(z.p.nonConnectorWordIndex<=w.p.nonConnectorWordIndex)
                                w.left=x;
                            else
                                w.right=x;
                        }
                        else
                            root=x;
                    }                                               // end case 1-1
                }                                                   // end case 1
                upd_height(z);
                if(x.parent==y)
                {
                    upd_height(x);
                    upd_height(y);
                }
                else
                {
                    upd_height(y);
                    upd_height(x);
                }
                upd_height(w);
                
                //inorder(root);
                //System.out.println("End");
            }                                                       //end shifting
            z=z.parent;
        }
    }
    
    public boolean find(int a)
    {
        TreeNode temp=root;
        while(temp!=null)
        {
            if(temp.p.nonConnectorWordIndex==a)
                return true;
            else if(temp.p.nonConnectorWordIndex>=a)
                temp=temp.left;
            else
                temp=temp.right;
        }
        return false;
        
    }
    
    
    
    public void inorder(TreeNode t)
    {
        if(t==null)
            return;
        inorder(t.left);
        System.out.println(t.p.nonConnectorWordIndex+" "+t.lheight+" "+t.rheight);
        inorder(t.right);
    }
    
    
    
}
