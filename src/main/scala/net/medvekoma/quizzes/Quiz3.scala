package net.medvekoma.quizzes

import slick.jdbc.H2Profile.api._
import slick.lifted.Tag

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Using

object Quiz3 extends App {

  val url = "jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1"
  Using.resource(Database.forURL(url, driver = "org.h2.Driver")) { db =>

    val meetupTable = TableQuery[Meetup]
    Meetup.initialize(db, meetupTable)

    val query = meetupTable
      .sortBy(_.name)

    val records = query
      .filter(_.country === "HU")
      .result

    records.statements.foreach(println)

    val res = db.run(records).map(_.foreach {
      case (name, country) => println(s"$name from $country")
    })
    Await.result(res, 3.seconds)
  }

  class Meetup(tag: Tag) extends Table[(String, String)](tag, "Meetup"){
    def name = column[String]("Name", O.PrimaryKey)
    def country = column[String]("Country")
    def * = (name, country)
  }

  object Meetup {
    def initialize(db: Database, table: TableQuery[Meetup]): Unit = {
      val setup = DBIO.seq(
        table.schema.create,
        table ++= Seq(
          ("Peter", "HU"),
          ("Yoann", "FR"),
          ("Maria", "HU")
        )
      )
      Await.result(db.run(setup), 3.seconds)
    }
  }
}

