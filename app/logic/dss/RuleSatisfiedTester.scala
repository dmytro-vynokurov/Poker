package logic.dss

/**
 * User: Dmytro Vynokurov
 * Date: 10/24/13
 * Time: 12:29 AM
 */
object RuleSatisfiedTester {

  def ruleSatisfied(facts: List[Fact], rule: Rule): Boolean = rule.left.forall(e=>facts.contains(e))
}
