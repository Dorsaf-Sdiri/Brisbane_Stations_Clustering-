package com.citybikes.clustering
import org.scalatest.FunSuite
import org.scalaml.app.ScalaMlTest

final class Test extends ScalaMlTest {
	/**
		* Name of the chapter the tests are related to
		*/

	test(s" Running main class") {
		evaluate(Run)
	}
}
class Temp extends FunSuite with ShouldMatchers {
	val ref =
	test("Same predictions each time")  { (Run.clusters) should equal (ref) }}

//class test extends FunSuite {
//	test("test") {
//	assert(main.method)
//}
//}