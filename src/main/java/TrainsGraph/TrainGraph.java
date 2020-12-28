package TrainsGraph;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static jdk.nashorn.internal.objects.Global.println;

public class TrainGraph {
    private int v;// 顶点个数
    private Map<String, Map<String, Integer>> adj;// 邻接表，存储各个顶点的关系
    private Set<String> vertexes;// 所有的顶点集合

    /**
     * 构造方法 初始化顶点之间的关系
     * @param str
     * */
    public TrainGraph(String str) {
        vertexes = new HashSet<>();
        adj = new HashMap<>();
        if(null == str || str.length() <=0){
            return;
        }
        String[] vertexLengths = str.split(", ");
        for (String vertexLength : vertexLengths){
            if(vertexLength.length() != 3){
                continue;
            }
            String s = String.valueOf(vertexLength.charAt(0));
            String t = String.valueOf(vertexLength.charAt(1));
            int w = vertexLength.charAt(2) - '0';
            Map<String, Integer> sEdgeMap = adj.get(s);
            if(null == sEdgeMap) {
                sEdgeMap = new HashMap<>();
                adj.put(s, sEdgeMap);
            }
            sEdgeMap.put(t, w);
            if (vertexes.add(s)) {
                v++;
            }
            if(vertexes.add(t)){
                v++;
            }
        }
    }

    /**
     * 计算多个顶点组成的路径的距离，如果不存在路径返回-1
     * @param vertexes
     * @return dist
     */
    public int findDistOfPath(String[] vertexes) {
        if(null == vertexes || vertexes.length <= 0) {
            return -1;
        }
        String s = vertexes[0];
        int dist = 0;
        for(int i = 1; i < vertexes.length; i++) {
            String t = vertexes[i];
            Map<String, Integer> edgeMap = adj.get(s);
            if(null != edgeMap && edgeMap.containsKey(t)){
                dist += edgeMap.get(t);
            } else {
                return -1;
            }
            s = t;
        }
        return dist;
    }

    public static void main(String[] args){
        String str = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        String[] vertexLengths = str.split(", ");
        for(String vertexLength : vertexLengths){
            System.out.println(vertexLength);
        }
        int w = '9' - '3';
        System.out.println(w);
        //        TrainGraph trainGraph = new TrainGraph(str);

    }
}
