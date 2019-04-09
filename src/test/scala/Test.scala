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
//class test extends FunSuite {
//	test("test") {
//	assert(main.method)
//}
//}