package net.medvekoma.scala

import slick.lifted.Tag
import slick.jdbc.H2Profile.api._

class Meetup(tag: Tag) extends Table[(String, String)](tag, "Meetup"){
  def name = column[String]("Name", O.PrimaryKey)
  def country = column[String]("Country")
  def * = (name, country)
}
