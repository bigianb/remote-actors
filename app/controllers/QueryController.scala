package controllers

import javax.inject.{Inject, Singleton}

import actors.QueryActor
import actors.QueryActor.PerformQuery
import akka.actor.ActorSystem
import play.api.mvc.{Action, Controller}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  * Handles Queries.
  */
@Singleton
class QueryController @Inject()(implicit ec:ExecutionContext, system: ActorSystem) extends Controller
{

  val queryActor = system.actorOf(QueryActor.props, "query-actor")
  implicit val timeout: Timeout = 5.seconds

  def query = Action.async {
    (queryActor ? PerformQuery("ian")).mapTo[String].map { message =>
      Ok(message)
    }
  }
}
