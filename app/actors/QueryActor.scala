package actors

import akka.actor._

object QueryActor {
  def props = Props[QueryActor]

  case class PerformQuery(querySql: String)
}

class QueryActor extends Actor {
  import QueryActor._

  def receive = {
    case PerformQuery(querySql: String) =>
      sender() ! "Performed, " + querySql
  }
}
