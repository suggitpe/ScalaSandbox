package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task


/*
 * This is the main application context and is fed through the routes
 * file.  In this instance we have bound all of the routes to Actions
 * to execute into the core task object.
 * This class is really responsible for all of the HTTP stuff and basic
 * error processing.
 */
object Application extends Controller {

  val taskForm = Form("label" -> nonEmptyText)

  def index = Action(Redirect(routes.Application.tasks()))

  def tasks = Action(Ok(views.html.index(Task.all(), taskForm)))

  def newTask = Action {
    implicit request =>
      taskForm.bindFromRequest.fold(
        errors => BadRequest(views.html.index(Task.all(), errors)),
        label => {
          Task.create(label)
          Redirect(routes.Application.tasks)
        }
      )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }


}