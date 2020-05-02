package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon1260 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(token.nextToken());
		int M = Integer.parseInt(token.nextToken());
		int startPoint = Integer.parseInt(token.nextToken());
		
		Graph graph = new Graph(N);
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(br.readLine());
			int i1 = Integer.parseInt(token.nextToken());
			int i2 = Integer.parseInt(token.nextToken());
			
			graph.addEdge(i1, i2);
		}
		graph.sort();
		
		
		graph.dfs(startPoint);
		graph.bfs(startPoint);
	}
}

/* Graph Class */
class Graph{
	StringBuilder br;
	Node[] nodes;
	
	Graph(int size){
		nodes = new Node[size + 1]; // Starting point is 1 index
		
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(i);
		}
	}

	/* Make value between i1 node and i2 node */
	void addEdge(int i1 , int i2) {
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		
		if(n1.nodeList.contains(n2) == false) {
			n1.nodeList.add(n2);
		}
		if(n2.nodeList.contains(n1) == false) {
			n2.nodeList.add(n1);
		}
	}

	/* dfs */
	void dfs(int index) {
		init();
		
		Node n = nodes[index];
		dfs(n);
		print();
	}

	/* dfs recursion */
	void dfs(Node node) {
		if(node == null) return;
		node.marked = true;
		br.append(node.data + " ");
		for(Node n : node.nodeList) {
			if(n.marked == false) {
				dfs(n);
			}
		}
	}
	
	/* bfs */
	void bfs(int index) {
		init();
		
		Node root = nodes[index];
		LinkedList<Node> queue = new LinkedList<Node>();
		root.marked = true;
		queue.add(root);
		while(!queue.isEmpty()) {
			Node r = queue.pop();
			for(Node n : r.nodeList) {
				if(n.marked == false) {
					n.marked = true;
					queue.add(n);
				}
			}
			br.append(r.data + " ");
		}
		print();
	}
	
	void print() {
		if(br != null) {
			System.out.println(br.toString());			
		}else {
			System.out.println("StringBuilder Class is null");
		}
	}
	
	/* Init marked value */
	void init() {
		br = new StringBuilder();
		for(Node n : nodes) {
			n.marked = false;
		}
	}
	
	void sort() {
		for(Node n : nodes) {
			Collections.sort(n.nodeList);
		}
	}
	
	/* Node Inner Class */
	class Node implements Comparable<Node>{
		int data;
		LinkedList<Node> nodeList;
		boolean marked;
		
		Node (int data){
			this.data = data;
			this.marked = false;
			nodeList = new LinkedList<Node>();
		}

		@Override
		public int compareTo(Node o) {
			return this.data - o.data;
		}
	}
}
