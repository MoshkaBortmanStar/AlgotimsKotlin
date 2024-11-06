package org.example.algoritms

import java.util.LinkedList

class DijkstraAlgorithm {

    companion object {
        // cache
        val processed = HashSet<String>()

        @JvmStatic
        fun dijkstraAlgorithmExample() {
            val graph = HashMap<String, HashMap<String, Int>?>()
            val costs = HashMap<String, Int>()
            val parents = LinkedHashMap<String, String?>()

            setupGraph(graph)

            graph["start"]?.keys?.forEach {

            }


            val map = graph["start"]
            map?.keys?.forEach {
                map[it]?.let { it1 -> costs[it] = it1 }
            }

            graph.keys.forEach {
                if (it == "start") {
                    graph[it]?.keys?.forEach {
                        parents[it] = "start"
                    }
                    return@forEach
                }
                if (costs[it] == null) {
                    costs[it] = Int.MAX_VALUE
                    parents[it] = null
                }
            }

            println(costs.toString())
            println(graph.toString())
            println(parents.toString())

            // algorithm
            var node = findLowestNodeCoast(costs)
            println(node)

            while (node != null) {
                val coastValue = costs[node]
                val neighbours = graph[node]
                //основная логика ищем соседей, смотри их вес, если он меньше то меняем родителя на нод и обновляем цену
                neighbours?.forEach {
                    val currentNode = it.key
                    val newCoastValue = coastValue?.plus(it.value)
                    if (costs[currentNode]!! > newCoastValue!!) {
                        costs[currentNode] = newCoastValue
                        parents[currentNode] = node
                    }
                }

                processed.add(node)
                node = findLowestNodeCoast(costs)
            }

            println(parents.toString())


            var nextNode = parents["final"]
            val list = LinkedList<String>()
            list.add("final")
            println("final |")

            while (nextNode != "start") {
                println("$nextNode |")
                if (nextNode != null) {
                    list.add(nextNode)
                }
                nextNode = parents[nextNode]
            }
            list.add("start")
            println("start |")

            list.reverse()
            println(list)

        }

        private fun findLowestNodeCoast(coast: HashMap<String, Int>): String? {
            var lowestNodeValue = Int.MAX_VALUE
            var lowestNode: String? = null

            coast.forEach {
                val coastValue = it.value
                if (coastValue < lowestNodeValue && !processed.contains(it.key)) {
                    lowestNodeValue = coastValue
                    lowestNode = it.key
                }
            }
            return lowestNode
        }

        private fun setupGraph(graph: HashMap<String, HashMap<String, Int>?>) {
            graph["start"] = HashMap()
            graph["start"]?.set("a", 6)
            graph["start"]?.set("b", 2)
            graph["a"] = HashMap()
            graph["a"]?.set("final", 1)
            graph["b"] = HashMap()
            graph["b"]?.set("a", 3)
            graph["b"]?.set("final", 5)
            graph["final"] = null
        }
    }

}

fun main() {
    DijkstraAlgorithm.dijkstraAlgorithmExample()
}