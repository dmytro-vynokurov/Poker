package src.dss

import RuleSatisfiedTester._

/**
 * User: Dmytro Vynokurov
 * Date: 10/24/13
 * Time: 12:26 AM
 */
object StraightFinder {

  def straight(facts: List[Fact], rules: List[Rule], desiredFactName: String): Option[Fact] = {
    executeRule(facts, rules) match {
      case None => None
      case Some(fact@Fact(name: String, _)) => {
        if (name == desiredFactName) Some(fact)
        else straight(fact :: facts, rules, desiredFactName)
      }
    }
  }

  def executeRule(facts: List[Fact], rules: List[Rule]): Option[Fact] = {
    if (rules.isEmpty) return None
    if (!facts.contains(rules.head.right) && ruleSatisfied(facts, rules.head)) return Some(rules.head.right)
    executeRule(facts, rules.tail)
  }

}
