package net.medvekoma.quizzes

object Quiz0 extends App {

  val strings = List(
    "one,two,three",
    "four,five,six"
  )

  val result = strings
    .flatMap(text => text.split(","))
    .size

  println(result)
}
