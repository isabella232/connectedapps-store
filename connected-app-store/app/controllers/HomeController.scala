package controllers

import java.util.UUID
import javax.inject._
import play.api._
import play.api.mvc._
import models._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                              repo: DAO) 
                              (implicit ec: ExecutionContext)
                              extends AbstractController(cc) {

  def index() = Action.async { implicit request =>    
    repo.getAllCategory.map ( result =>
     Ok(views.html.index(result)) 
    )
  }

  def error() = Action.async { implicit request => 
      // Ok(views.html.404())
      ???
  }

   def appDetail( category: String, name: String ) = Action.async { implicit request => 

        val result =for{
      detail <-   repo.getApplicationByName(name)

  } yield (detail)
    result.map {  detail =>
      detail match {
            case Some(x) => Ok(views.html.app_detail(x,category)) 
            case None => Ok
      }
    }   
  }

  def appList(name:String, category: UUID) = Action.async { implicit request =>      
      repo.getAllApplication(category).map( result =>
        Ok(views.html.app_list(result,name))
      )      
  }

  def searchList() = Action.async { implicit request => 
      ???
  }
}
