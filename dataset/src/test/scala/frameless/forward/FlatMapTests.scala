package frameless

import org.scalacheck.Prop
import org.scalacheck.Prop._

class FlatMapTests extends TypedDatasetSuite {
  test("flatMap") {
    def prop[A: TypedEncoder, B: TypedEncoder](flatMapFunction: A => Vector[B], data: Vector[A]): Prop =
      TypedDataset.create(data).flatMap(flatMapFunction).collect().run().toVector =? data.flatMap(flatMapFunction)

    check(forAll(prop[Int, Int] _))
    check(forAll(prop[Int, String] _))
    check(forAll(prop[String, Int] _))
  }
}
