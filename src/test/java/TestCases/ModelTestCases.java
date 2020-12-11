package TestCases;


import static org.junit.Assert.*;
import wpi.xojo.g2.project.model.*;
import org.junit.Test;
import java.sql.Timestamp;

public class ModelTestCases {
	
	/*
	 * Team Member Test Cases
	 */
	
	TeamMember ValidMember1 = new TeamMember("TestTeamMember", "Tester1", "TestChoiceID", "password");
	
	@Test
	public void MemberEqualsTrue() {
		TeamMember Member2 = new TeamMember("TestTeamMember", "Tester1", "TestChoiceID", "password");
		assertTrue(ValidMember1.equals(Member2));
	}
	@Test
	public void MemberEqualsNotEqual() {
		TeamMember Member2 = new TeamMember("TestTeamMemberID2", "TesterNum1", "TestTeam", "password123");
		assertFalse(ValidMember1.equals(Member2));
	}
	@Test
	public void MemberEqualsNull() {
		TeamMember Member2 = null;
		assertFalse(ValidMember1.equals(Member2));
	}
	@Test
	public void MemberEqualsNonMember() {
		String Member2 = "001";
		assertFalse(ValidMember1.equals(Member2));
	}
	
	@Test
	public void MembertoString() {
		String Member1String = "Name: " + ValidMember1.name + "\tChoice: " + ValidMember1.choiceID;
		assertEquals(ValidMember1.toString(), Member1String);
	}
	
	/*
	 * Choice Test Cases
	 */
	
	@Test
	public void ChoiceEqualsTrue() {
		Choice Choice1 = new Choice("TestChoiceID", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		Choice Choice2 = new Choice("TestChoiceID", "Choice1" , "Not a test Choice. But it actually is just need a different description for this one to differentiate between the two visually.", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		assertTrue(Choice1.equals(Choice2));
	}
	@Test
	public void ChoiceEqualsFalse() {
		Choice Choice1 = new Choice("TestChoiceID", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		Choice Choice2 = new Choice("TestChoice", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		assertFalse(Choice1.equals(Choice2));
	}
	@Test
	public void ChoiceEqualsNull() {
		Choice Choice1 = new Choice("TestChoiceID", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		Choice Choice2 = null;
		assertFalse(Choice1.equals(Choice2));
	}
	@Test
	public void ChoiceEqualsNonChoice() {
		Choice Choice1 = new Choice("TestChoiceID", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		String Choice2 = "TestChoice";
		assertFalse(Choice1.equals(Choice2));
	}
	@Test
	public void ChoiceToString() {
		Choice Choice1 = new Choice("TestChoiceID", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		String ChoiceString = "Choice ID: TestChoiceID";
		assertEquals(Choice1.toString(), ChoiceString);
	}
	
	/*
	 * Alternative Test Cases
	 */
	
	Alternative Alternative1 = new Alternative("TestAlternativeID", "TestChoiceID", "Description", false);
	
	@Test
	public void AlternativeEqualsTrue() {
		Alternative Alternative2 = new Alternative("TestAlternativeID", "TestChoiceID", "Definitely a description", false);
		assertTrue(Alternative1.equals(Alternative2));
	}
	@Test
	public void AlternativeEqualsFalse() {
		Alternative Alternative2 = new Alternative("TestAlternative", "TestChoiceID", "Definitely a description", false);
		assertFalse(Alternative1.equals(Alternative2));
	}
	@Test
	public void AlternativeEqualsnull() {
		Alternative Alternative2 = null;
		assertFalse(Alternative1.equals(Alternative2));
	}
	@Test
	public void AlternativeEqualsNonAlternative() {
		Choice Alternative2 = new Choice("TestChoiceID", "Choice1" , "A test Choice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
		assertFalse(Alternative1.equals(Alternative2));
	}
	@Test
	public void AlternativeToString() {
		String AlternativeString = "Alternative ID: " + "TestAlternativeID" + "\tAssociated choice: " + "TestChoiceID";
		assertEquals(Alternative1.toString(), AlternativeString);
	}
	/*
	 * Vote Test Cases
	 */
	Vote ValidVote1 = new Vote("TestAlternativeID", "TestMemberID", true);
	
	@Test
	public void VoteEqualsTrue() {
		Vote ValidVote2 = new Vote("TestAlternativeID", "TestMemberID", true);
		assertTrue(ValidVote1.equals(ValidVote2));
	}
	@Test
	public void VoteEqualsNotEqualAlt() {
		Vote ValidVote2 = new Vote("AlternativeID", "TestMemberID", true);
		assertFalse(ValidVote1.equals(ValidVote2));
	}
	@Test
	public void VoteEqualsNotEqualMember() {
		Vote ValidVote2 = new Vote("TestAlternativeID", "MemberID", true);
		assertFalse(ValidVote1.equals(ValidVote2));
	}
	@Test
	public void VoteEqualsNeitherSame() {
		Vote ValidVote2 = new Vote("AlternativeID", "MemberID", true);
		assertFalse(ValidVote1.equals(ValidVote2));
	}
	@Test
	public void VoteEqualsNull() {
		Vote Vote2 = null;
		assertFalse(ValidVote1.equals(Vote2));
	}
	@Test
	public void VoteEqualsNonVote() {
		String Vote2 = "A totally valid vote";
		assertFalse(ValidVote1.equals(Vote2));
	}
	@Test
	public void VoteTestToString() {
		String VoteToString = "Alternative ID: " + ValidVote1.alternativeID + "\t Member ID: " + ValidVote1.memberID;
		assertEquals(ValidVote1.toString(), VoteToString);
	}
}
