package dss

/**
 * User: Dmytro Vynokurov
 * Date: 10/24/13
 * Time: 12:29 AM
 */
object RuleSatisfiedTester {

  def ruleSatisfied(facts: List[Fact], rule: Rule): Boolean = {
    containsAll(facts, rule.left)
  }

  def containsAll(largerList: List[Fact], smallerList: List[Fact]): Boolean = {
    var tempFirst = smallerList
    while (!tempFirst.isEmpty) {
      if (!largerList.contains(tempFirst.head)) return false
      tempFirst = tempFirst.tail
    }
    true
  }
}
