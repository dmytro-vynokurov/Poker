package dss


/**
 * User: Dmytro Vynokurov
 * Date: 10/24/13
 * Time: 12:27 AM
 */
object ReverseFinder {

  def reverse(facts: List[Fact], rules: List[Rule], desiredFact: Fact): Boolean = {
    if (facts.contains(desiredFact)) return true
    if(rules.isEmpty) return false

//    println("  ****rules: "+rules.mkString("\n"))
//    println("    facts: "+facts.mkString("\n"))
//    println("    desired: "+desiredFact)
//    println()

    rules.exists(rule=>rule.right==desiredFact && rule.left.forall(f => reverse(facts, rules.filter(_!=rule), f)))
  }
}
