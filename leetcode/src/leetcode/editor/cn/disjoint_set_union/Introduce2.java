package leetcode.editor.cn.disjoint_set_union;

public class Introduce2 {
}
class DsuPathCompress{
    final static int MAXN=100;
    int[] parents=new int[MAXN];
    int[] rank =new int[MAXN];
    public DsuPathCompress(int n) {
        init(n);
    }
    //初始化并查集
    void init(int n){
        for(int i=0;i<n;i++){
            parents[i]=i; //最开始的时候，各自是自己父节点
            rank[i]=1;  //最初的时候，设置每个子树（最初只有一个节点）的秩为1
        }
    }

    //查询根节点,一层一层的找，根节点的父节点就是自己
    //在查找过程中，顺带把路径上的点的父节点也设置为根节点
    int find(int x){
        if(parents[x]==x)
            return x;
        else{
            parents[x]=find(parents[x]);//时每一个节点都指向父节点
            return parents[x];
        }
    }

    //合并，直接父节点进行合并
    void merge(int i,int j){
        //先找到两个节点的父节点
        int x=find(i);
        int y=find(j);

        //先判断是否是同一个根节点，如果是就不用合并
        //先判断是否是同一个根节点，如果是就不用合并
        if(x==y)
            return;
        //将秩小的树合并到秩更大的树
        if(rank[x]>=rank[y]){
            parents[y]=x;
        }
        else{
            parents[x]=y;
        }
        //如果两个的秩相同，合并后，x的秩会加一
        if(rank[x]==rank[y]){
            rank[x]++;
        }

    }
}