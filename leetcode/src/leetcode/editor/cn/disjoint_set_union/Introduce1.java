package leetcode.editor.cn.disjoint_set_union;

public class Introduce1 {
    public static void main(String[] args) {
    }



}

class DSU{
    final static int MAXN=100;
    int[] parents=new int[MAXN];
    public DSU(int n) {

    }

    //初始化并查集
    void init(int n){
        for(int i=0;i<n;i++){
            parents[i]=i; //最开始的时候，各自是自己父节点
        }
    }

    //查询根节点,一层一层的找，根节点的父节点就是自己
    int find(int x){
        if(parents[x]==x)
            return x;
        else
            return find(parents[x]);
    }

    //合并，直接父节点进行合并
    void merge(int x,int y){
        parents[find(y)]=find(x);
    }
}