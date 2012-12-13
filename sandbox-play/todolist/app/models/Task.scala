package models

import anorm.SqlParser._
import anorm._
import play.api.db._
import play.api.Play.current

/**
 * This class manages the core task object and allows it to be persisted
 *
 */
case class Task(id: Long, label: String)

/**
 * This object class allows us to persist Task objects into the database
 * through some static means.
 */
object Task {

  val task = {
    get[Long]("id") ~
      get[String]("label") map {
      case id ~ label => Task(id, label)
    }
  }

  def all(): List[Task] = DB.withConnection {
    implicit c =>
      SQL("select * from task").as(task *)
  }

  def create(toDoLabel: String) {
    DB.withConnection {
      implicit c =>
        SQL("insert into task (label) values ({label})").on('label -> toDoLabel).executeUpdate()
    }
  }

  def delete(aTaskId: Long) {
    DB.withConnection {
      implicit c =>
        SQL("delete from task where id = {taskId}").on('taskId -> aTaskId).executeUpdate()
    }
  }

}
