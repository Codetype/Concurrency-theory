import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.collection.mutable.Stack
import java.io._

object Main extends App {
    //Create transactions which we use to create alphabet
    val a_0 = new Transaction('a', 'x', 'x'::'y'::Nil)
    val b_0 = new Transaction('b', 'y', 'y'::'z'::Nil)
    val c_0 = new Transaction('c', 'x', 'x'::'z'::Nil)
    val d_0 = new Transaction('d', 'z', 'y'::'z'::Nil)

    var alphabet = a_0 :: b_0 :: c_0 :: d_0 :: Nil

    //Additionally add two transactions connected with example word baadcb
    val a_1 = new Transaction('a', 'x', 'x'::'y'::Nil)
    val b_1 = new Transaction('b', 'y', 'y'::'z'::Nil)

    var word = b_0 :: a_0 :: a_0 :: d_0 :: c_0 :: b_0 :: Nil

    //1. Get dependency relation - D =
    var D = Transaction.getDependencyRelation(alphabet);
    println("\n1. Dependency Relation:\nD = " + D)

    //2. Get independency relation - I =
    var I  = Transaction.getIndependencyRelation(alphabet);
    println("\n2. Independency Relation:\nI = " + I)


    //3 Get Foata class
    var fnf = Transaction.getFoataClass(alphabet, word, D)
    var F = ListBuffer[List[Transaction]]()
    for(f <- fnf)
        F += f.toList

    println("\n3. Foata classes\nF = " + F.toList)

    //4 Draw graph
    var dependent =  scala.collection.mutable.Map[Char, List[Transaction]]();
    var independent = scala.collection.mutable.Map[Char, List[Transaction]]();

    for(a <- alphabet){
        var listD = ListBuffer[Transaction]()
        var listI = ListBuffer[Transaction]()
        for(l <- D){
          if(a == l._1) listD += l._2
        }
        dependent(a.name) = listD.toList
        for(l <- I){
          if(a == l._1) listI += l._2
        }
        independent(a.name) = listI.toList
    }

    var graph = Graph.createGraph(word, collection.immutable.Map(dependent.toSeq.sortWith(_._1 < _._1): _*), collection.immutable.Map(independent.toSeq.sortWith(_._1 < _._1): _*))
    
    println("\n4. Graph dependency \n")
    Graph.printGraph(graph)

    //5 Graph to FNF
    println("\n5. Foata classes generated from graph\nFg = " + Graph.getFoataFromGraph(graph) + "\n")
}

/*
class Transaction describes transactions from our example 
eg. (a) x := x + y 
name: a; dependVariable: x; resultList: List(x, y)
*/

class Transaction(var tName: Char, var tDependVariable : Char, var tResultList : List[Char]) {
  var name: Char = tName
  var dependVariable: Char = tDependVariable
  var resultList: List[Char] = tResultList

  override
  def toString: String = s"$name"
}

object Transaction {
    //dependency relations get specific to this class transactions
    def getDependencyRelation(alphabet: List[Transaction]): List[(Transaction, Transaction)] = {
      var Depend = ListBuffer[(Transaction, Transaction)]()
      for(t1 <- alphabet)
          for(t2 <- alphabet)
              if(t1.dependVariable == t2.dependVariable) Depend += ((t1, t2))
              else  if(t1.resultList.contains(t2.dependVariable)) Depend += ((t1, t2))
              else  if(t2.resultList.contains(t1.dependVariable)) Depend += ((t1, t2))

      Depend.toList
    }
    
    //independency relations get specific to this class transactions 
    def getIndependencyRelation(alphabet: List[Transaction]): List[(Transaction, Transaction)] = {
      var Independ = ListBuffer[(Transaction, Transaction)]()
      for(t1 <- alphabet)
          for(t2 <- alphabet)
              if(!t1.resultList.contains(t2.dependVariable) && !t2.resultList.contains(t1.dependVariable) )
                    Independ += ((t1, t2))

      Independ.toList
    }

    def returnPosition(l : Char): Int = {
      return l.toInt - 97
    }
    
    //to this algorithm we use special stacks algorithm from Dickert book
    def getFoataClass(alphabet: List[Transaction], word: List[Transaction], D: List[(Transaction, Transaction)]) = {
        val stacks = ArrayBuffer[scala.collection.mutable.Stack[Transaction]]()

        for(t <- alphabet)
            stacks += Stack[Transaction]()

        val sign = new Transaction('*', '*', '*'::Nil)

        for(t <- word){
          stacks(Transaction.returnPosition(t.name)).push(t)
          for(t2 <- D.filter(_._1.name == t.name ).map(_._2).filter( _.name != t.name)){
            stacks(Transaction.returnPosition(t2.name)).push(sign)
          }
        }


        val fnf = ListBuffer[ListBuffer[Transaction]]()
        for(w <- word){
          val currStack = ListBuffer[Transaction]()
          for(s <- stacks){
            if(s.nonEmpty){
              val stackTop = s.pop()

              if(stackTop.name != '*'){
                currStack += stackTop
              }
            }
          }

          if(currStack.nonEmpty){
            fnf += currStack
          }
        }

        fnf.reverse
    }


}


//to create Dickert graph, let's make class Node and Graph
case class Node(id: Int, transaction: Transaction, children: List[Node]) {
    override def toString: String = transaction.toString
}

class Graph(val nodes: List[Node], val edges: List[String])

object Graph {
    def apply(nodes: List[Node], edges: List[String]): Graph = new Graph(nodes, edges)
    
    //create Graph using Set and special independent and dependent properties
    def createGraph(word: List[Transaction], dependent: Map[Char, List[Transaction]], independent: Map[Char, List[Transaction]]): Graph = {
      val nodes: List[Node] = word.zipWithIndex.map { case (t, id) => Node(id, t, List.empty[Node]) }
      nodes.reverse.foldLeft( Set.empty[Node], Graph(List.empty[Node], List.empty[String]))(
          (graphSet: (Set[Node], Graph), head: Node) => {
              val D = graphSet._1.toList.filter(node => dependent(head.transaction.name).toSet(node.transaction))
              val I = graphSet._1.toList.filter(node => independent(head.transaction.name).toSet(node.transaction))
              val pass =
                  D match {
                      case Nil => I.flatMap(d => d.children).filter(node => dependent(head.transaction.name).toSet(node.transaction))
                      case _ => D
                  }
              val vNodes = Node(head.id, head.transaction, pass)

              (graphSet._1 -- D + vNodes, Graph(graphSet._2.nodes.::(vNodes), graphSet._2.edges ++ pass.map(d => s"${head.id+1} -> ${d.id+1}"))
              )
          })._2
    }
  
      //method provides posibility of printing or writing to file the Dickert Graph
      def printGraph(graph: Graph) = {
        //val writer = new PrintWriter(new File("graph.dot"))
        //writer.write("digraph g{\n")
        println("digraph g{")
        for(e <- graph.edges.reverse){
          //writer.write(" " + e + "\n")
          println(" " + e)
        }
        var i = 1
        for(n <- graph.nodes){
          println(" " + i + "|label=" + n + "|")
          //writer.write(" " + i + "|label=" + n + "|\n")
          i += 1
        }
        // writer.write("}")
        println("}")
        //writer.close
      }

  //this method get Foata classes from Dickert Graph
  def getFoataFromGraph(graph: Graph): List[List[Transaction]] = {
      graph.nodes.foldLeft(graph.nodes, List.empty[List[Transaction]])((nodes, _) => {
          val c = indpInGraph(nodes._1)
         
          nodes._1 match {
              case Nil => (Nil, nodes._2)
              case ::(_, tl) ⇒ (c.foldLeft(tl)((acc, e) => matchResult(acc, e)), nodes._2.::(c.map(_.transaction)))
          }
                                                                                    })._2.reverse
  }

    def indpInGraph(nodes: List[Node]): List[Node] = {
      nodes.foldLeft(Set.empty[Node], List.empty[Node])(
          (acc, n: Node) => if (acc._1(n)) (acc._1 ++ n.children, acc._2) 
                            else (acc._1 ++ n.children, acc._2.::(n))
                                                        )._2
    }
    
      //auxiliary function
      def matchResult[Type](l: List[Type], value: Type): List[Type] = {
          val i = l.indexOf(value)
          if (i < 0) {
              l
          } else if (i == 0) {
              l.tail
          } else {
              val (a, b) = l.splitAt(i)
              a ++ b.tail
          }
      }    
}
