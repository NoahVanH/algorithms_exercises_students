package graphs;

import java.util.*;

public class Erdos {

	public static final String erdos = "Paul Erdös";
	private final HashMap<String, Integer> map; // Key: Author, Value: Erdős number

	/**
	 * Constructs an Erdos object and computes the Erdős numbers for each author.
	 *
	 * @param articlesAuthors An ArrayList of String arrays, where each array represents the list of authors of a single article.
	 */
	public Erdos(ArrayList<String[]> articlesAuthors) {
		this.map = new HashMap<>(); // Initialize the map to store Erdős numbers

		// Step 1: Build the graph of co-author relationships
		HashMap<String, List<String>> graph = new HashMap<>();
		for (String[] relations:articlesAuthors) {
			for (String author:relations) {
				graph.putIfAbsent(author,new ArrayList<>());
				for (String coAuthor:relations) {
					if(!author.equals(coAuthor)){
						graph.get(author).add(coAuthor);
					}

				}


			}
			
		}

		// Step 2: Perform BFS starting from "Paul Erdös"
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(erdos,0));
		map.put(erdos,0);

		while (!queue.isEmpty()){
			Node current = queue.poll();
			String author = current.name;
			int distance = current.distance;
			for (String coAuthor:graph.getOrDefault(author,new ArrayList<>())) {
				if(!map.containsKey(coAuthor)){
					map.put(coAuthor,distance+1);
					queue.add(new Node(coAuthor,distance+1));
				}

			}
		}



	}

	/**
	 * Returns the Erdős number of a given author.
	 * This method is expected to run in O(1).
	 *
	 * @param author The name of the author whose Erdős number is to be found.
	 * @return The Erdős number of the specified author. If the author is not in the network, returns -1.
	 */
	public int findErdosNumber(String author) {
		return map.getOrDefault(author, 0);
	}

	/**
	 * Helper class to represent a node in the BFS queue.
	 */
	public static class Node {
		String name;
		int distance;

		Node(String name, int distance) {
			this.name = name;
			this.distance = distance;
		}
	}
}
