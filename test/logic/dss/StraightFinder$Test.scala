package logic.dss

import org.specs2.mutable.Specification
import StraightFinder._

/**
 * User: Dmytro Vynokurov
 * Date: 11/4/13
 * Time: 11:47 PM
 */
class StraightFinder$Test extends Specification{


  val rule1: Rule = Rule(
    Fact("work", "remote") ::
      Fact("children", "none") ::
      Fact("money", "much") ::
      Nil,
    Fact("flat", "suburbs")
  )

  val rule2: Rule = Rule(
    Fact("profession", "developer") ::
      Fact("selforganisation", "ok") ::
      Nil,
    Fact("work", "remote")
  )

  val rule3: Rule = Rule(
    Fact("university", "kpi") ::
      Fact("speciality", "technic") ::
      Nil,
    Fact("profession", "developer")
  )

  val rule4: Rule = Rule(
    Fact("profession", "developer") ::
      Fact("selforganisation", "none") ::
      Nil,
    Fact("work", "present")
  )

  val rule5: Rule = Rule(
    Fact("profession", "developer") ::
      Nil,
    Fact("money", "much")
  )

  val rule6: Rule = Rule(
    Fact("age", "young") ::
      Nil,
    Fact("children", "none")
  )

  val rule7: Rule = Rule(
    Fact("money", "much") ::
      Fact("work", "present") ::
      Nil,
    Fact("flat", "downtown")
  )


  val facts: List[Fact] =
    Fact("university", "kpi") ::
      Fact("speciality", "technic") ::
      Fact("age", "young") ::
      Fact("selforganisation", "ok") ::
      Nil

  val rules: List[Rule] = rule1 :: rule2 :: rule3 :: rule4 :: rule5 :: rule6 :: rule7 :: Nil

  "The StraightFinder's straight method " should {
    " - return fact if it can be achieved" in {
      straight(facts,rules,"flat") mustEqual Some(Fact("flat","suburbs"))
    }
  }


}
