package TestCases;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import wpi.xojo.g2.project.db.*;
import wpi.xojo.g2.project.model.*;
import org.junit.Test;

public class DBTestCases {

	/*
	 * ChoiceDAO Tests
	 */
	
	Choice Choice1 = new Choice("TestChoiceID", "TestChoice", "JunitTestChoice", 1, new Timestamp(java.lang.System.currentTimeMillis()));
	
	Choice Choice2 = null;
	
	@Test
	public void getChoiceTestPass() {
		ChoiceDAO TestDAO = new ChoiceDAO();
		try{
			TestDAO.addChoice(Choice1);
			Choice returned = TestDAO.getChoice("TestChoiceID");
			assertEquals(returned, Choice1);
		}
		catch(Exception e){
			fail("Unexpected Exception thrown " + e);
		}
	}
	@Test
	public void getChoiceTestNonExistant() {
		ChoiceDAO TestDAO = new ChoiceDAO();
		try{
			TestDAO.getChoice("NonExistantChoiceID");
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void addChoiceTestCompletion() {
		ChoiceDAO TestDAO = new ChoiceDAO();
		try {
			TestDAO.addChoice(Choice1);
		}
		catch(Exception e) {
			fail("Exception Thrown");
		}
		assertTrue(true);
	}
	@Test
	public void addChoiceTestFailDuplicate() {
		ChoiceDAO TestDAO = new ChoiceDAO();
		for(int i = 0; i >= 1; i++) {
			try {
				TestDAO.addChoice(Choice1);
			}
			catch(Exception e) {
				assertTrue(true);
			}
		}
	}
	
	AlternativeDAO TestAlternativeDB = new AlternativeDAO();
	//TestChoice1 will be the passing Choice
	//TestChoice2 will be a choice without alternatives
	Choice TestChoice1 = new Choice("TestChoiceID1", "JunitTestChoice1", "Please ignore", 1, new Timestamp(java.lang.System.currentTimeMillis()));
	Choice TestChoice2 = new Choice("TestChoiceID2", "JunitTestChoice2", "Please ignore", 1, new Timestamp(java.lang.System.currentTimeMillis()));
	Alternative TestAlt1 = new Alternative("TestAlternativeID1", "TestChoiceID1", "Please ignore", false);
	Alternative TestAlt2 = new Alternative("TestAlternativeID2", "TestChoiceID1", "Please ignore", false);
	
	@Test
	public void getChoiceAlternativesTestPass() {
		ChoiceDAO TestChoiceDB = new ChoiceDAO();
		try {
			TestChoiceDB.addChoice(TestChoice1);
			TestAlternativeDB.addAlternative(TestAlt1);
			TestAlternativeDB.addAlternative(TestAlt2);
			
			List<Alternative> returned = TestChoiceDB.getChoiceAlternatives("TestChoiceID1");
			boolean TestAlt1InList = false;
			boolean TestAlt2InList = false;
			
			for(Alternative alt: returned) {
				if(alt.equals(TestAlt1)) {
					TestAlt1InList = true;
				}
				else if(alt.equals(TestAlt2)){
					TestAlt2InList = true;
				}
			}
			
			assertTrue(TestAlt1InList && TestAlt2InList);
			
		} catch (Exception e) {
			fail("Unexpected Exception thrown: " + e);
		}
	
		
	}
	@Test
	public void getChoiceAlternativesTestNonExistChoice() {
		ChoiceDAO TestChoiceDB = new ChoiceDAO();
		try { 
			//this should throw an exception for this and we can catch it to verify that the function is able to throw an exception for it
			TestChoiceDB.getChoiceAlternatives("TestChoiceID3");
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getChoiceAlternativesTestNullChoice() {
		ChoiceDAO TestChoiceDB = new ChoiceDAO();
		try {
			TestChoiceDB.getChoiceAlternatives(null);
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getChoiceAlternativesTestNoAlternatves() {
		ChoiceDAO choiceDAOTest = new ChoiceDAO();
		try {
			choiceDAOTest.addChoice(TestChoice2);
			List<Alternative> returned = choiceDAOTest.getChoiceAlternatives("TestChoiceID2");
			assertTrue(returned.size() <= 1);
		}
		catch(Exception e) {
			fail("Unexcepted Exception thrown: " + e);
		}
	}
	@Test
	public void getAllChoiceTestPass() {
		ChoiceDAO choiceDAOTest = new ChoiceDAO();
		try {
			choiceDAOTest.addChoice(TestChoice1);
			choiceDAOTest.addChoice(TestChoice2);
			List<Choice> returned = choiceDAOTest.getAllChoices();
			assertTrue((returned.contains(TestChoice1) && returned.contains(TestChoice2)));
		}
		catch(Exception e) {
			fail("Unexcepted Exception thrown: " + e);
		}
	}
	@Test
	public void getAllChoiceTestNoChoices() {
		try {
			ChoiceDAO TestChoiceDBT = new ChoiceDAO();
			TestChoiceDBT.getAllChoices();
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	
	/*
	 * AlternativeDAO Tests
	 */
	
	@Test
	public void getAltTestPass() {
		AlternativeDAO altDAOTest = new AlternativeDAO();
		ChoiceDAO choiceDAOTest = new ChoiceDAO();
		try {
			choiceDAOTest.addChoice(TestChoice1);
			altDAOTest.addAlternative(TestAlt1);
			altDAOTest.addAlternative(TestAlt2);
			Alternative result = altDAOTest.getAlternative("TestAlternativeID1");
			assertEquals(result, TestAlt1);
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void getAltTestNonExist() {
		AlternativeDAO altDAOTest = new AlternativeDAO();
		try {
			altDAOTest.addAlternative(TestAlt1);
			altDAOTest.addAlternative(TestAlt2);
			altDAOTest.getAlternative("TestAlternativeID2");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getAltTestNullID() {
		AlternativeDAO altDAOTest = new AlternativeDAO();
		try {
			altDAOTest.addAlternative(TestAlt1);
			altDAOTest.addAlternative(TestAlt2);
			altDAOTest.getAlternative(null);
		}catch(Exception e) {
			assertTrue(true);
		} 
	}
	@Test
	public void addAltTestPass() {
		AlternativeDAO altDAOTest = new AlternativeDAO();
		ChoiceDAO choiceDAOTest = new ChoiceDAO();
		try {
			choiceDAOTest.addChoice(TestChoice1);
			assertTrue(altDAOTest.addAlternative(TestAlt1));
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void addAltTestNull() {
		AlternativeDAO altDAOTest = new AlternativeDAO();
		try {
			boolean result = altDAOTest.addAlternative(null);
			if(!result){assertTrue(!result);}
			else {
				fail("the method added a null alternative");
			}
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	/*
	 * MemberDAO Tests
	 */

	TeamMember TestMember1 = new TeamMember("TestMemberID1", "TestChoiceID1", "Tester1", ""); 
	TeamMember TestMember2 = new TeamMember("TestMemberID2", "TestChoiceID4", "Tester2", "");
	TeamMember TestMember3 = new TeamMember("TestMemberID3", "TestChoiceID1", "Tester3", "");
	
	@Test
	public void getMemberTestPass() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			memberDB.addMember(TestMember3);
			assertEquals(memberDB.getMember("Tester1", "TestChoiceID1"), TestMember1);
		}catch(Exception e) {
			fail("Unexpected Exception thrown: " + e);
		}
	}
	@Test
	public void getMemberTestInvalidName() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			memberDB.addMember(TestMember3);
			memberDB.getMember("Tester4", "TestChoiceID1");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getMemberTestInvalidChoice() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			memberDB.addMember(TestMember3);
			memberDB.getMember("Tester1", "TestChoiceID4");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void addMemberTestPass() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			assertTrue(memberDB.addMember(TestMember1));
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void addMemberTestInvalidMember() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			assertFalse(memberDB.addMember(TestMember2));
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void addMemberTestDuplicate() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			assertFalse(memberDB.addMember(TestMember1));
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void countCurrMembersTestPass() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			memberDB.addMember(TestMember3);
			assertEquals(memberDB.countCurrMembers("TestChoiceID1"), 2);
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void countCurrMembersTestNoMembers() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			assertEquals(memberDB.countCurrMembers("TestChoiceID1"), 0);
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void countcurrMembersTestInvalidChoice() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.countCurrMembers("TestChoiceID6");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getMaxMembersTestPass() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			assertEquals(memberDB.getMaxMembers("TestChoiceID1"), 1);
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void getMaxMembersTestInvalidChoice() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			memberDB.addMember(TestMember1);
			assertEquals(memberDB.getMaxMembers("TestChoiceID6"), -1);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getMaxMembersTestNoMembers() {
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			assertEquals(memberDB.getMaxMembers("TestChoiceID1"), -1);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	/*
	 * VoteDAO Tests
	 */
	
	Vote testVote1 = new Vote("TestAlternativeID1", "TestMemberID1", true);
	Vote testVote2 = new Vote("TestAlternativeID1", "TestMemberID3", true);
	Vote testVote3 = new Vote("TestAlternativeID6", "TestMemberID3", true);
	Vote testVote4 = new Vote("TestAlternativeID1", "TestMemberID6", true);
	
	@Test
	public void getVoteTestPass() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.addVote(testVote2);
			assertEquals(voteDB.getVote("TestAlternativeID1", "TestMemberID1"), testVote1);
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void getVoteTestInvalidAltID() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.addVote(testVote2);
			voteDB.getVote("TestAlternativeID6", "TestMemberID1");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getVoteTestInvalidMemberID() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.addVote(testVote2);
			voteDB.getVote("TestAlternativeID1", "TestMemberID6");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void getVoteTestBothInvalid() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.addVote(testVote2);
			voteDB.getVote("TestAlternativeID6", "TestMemberID6");
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void addVoteTestPass() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			assertTrue(voteDB.addVote(testVote1));
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void addVoteTestInvalidAlt() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			assertFalse(voteDB.addVote(testVote3));
		}catch(Exception e) {
			assertTrue(true); //expected exception thrown... 
		}
	}
	@Test
	public void addVoteTestInvalidMember() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			assertFalse(voteDB.addVote(testVote4));
		}catch(Exception e) {
			assertTrue(true); //expected exception thrown... 
		}
	}
	@Test
	public void changeVoteTestPass() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.changeVote("TestAlternativeID1", "TestMemberID1", true);
			assertFalse(voteDB.getVote("TestAlternativeID1", "TestMemberID1").isUpvote);
		}catch(Exception e) {
			fail("Unexpected exception thrown: " + e);
		}
	}
	@Test
	public void changeVoteTestInvalidAltID() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.changeVote("TestAlternativeID6", "TestMemberID1", true);
			;
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void changeVoteTestInvalidMemberID() {
		VoteDAO voteDB = new VoteDAO();
		ChoiceDAO choiceDB = new ChoiceDAO();
		MemberDAO memberDB = new MemberDAO();
		AlternativeDAO altDB = new AlternativeDAO();
		try {
			choiceDB.addChoice(TestChoice1);
			altDB.addAlternative(TestAlt1);
			memberDB.addMember(TestMember1);
			voteDB.addVote(testVote1);
			voteDB.changeVote("TestAlternativeID1", "TestMemberID6", true);
			;
		}catch(Exception e) {
			assertTrue(true);
		}
	}
}
