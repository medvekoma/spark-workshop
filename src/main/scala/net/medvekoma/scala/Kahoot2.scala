package net.medvekoma.scala

object Kahoot2 extends App {

  val numbers = LazyList.from(1 to 10)

  val evens = numbers.filter { number =>
    println(number)
    number % 2 == 0
  }
  println("Count:", evens.size)
  println("Elements:", evens.toList)
}
