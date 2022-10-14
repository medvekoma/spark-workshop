package net.medvekoma.scala

import slick.jdbc.H2Profile.api._
import scala.concurrent.duration._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Using

object Kahoot3 extends App {

  val url = "jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1"
  Using.resource(Database.forURL(url, driver = "org.h2.Driver")) { db =>

    val meetup = TableQuery[Meetup]
    initialize(db, meetup)

    val query = meetup
      .sortBy(_.name)

    val records = query
      .filter(_.country === "HU")
      .result

    records.statements.foreach(println)
    val res = db.run(records).map(_.foreach {
      case (name, country) => println(s"${name} from ${country}")
    })
    Await.result(res, 3.seconds)
  }

  def initialize(db: Database, meetup: TableQuery[Meetup]): Unit = {
    val setup = DBIO.seq(
      meetup.schema.create,
      meetup ++= List(
        ("Peter", "HU"),
        ("Marie", "HU"),
        ("Ivonne", "FR")
      )
    )
    Await.result(db.run(setup), 3.seconds)
  }
}

