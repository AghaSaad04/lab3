/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;
import java.util.*;
import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	private static final Set<String> a = new HashSet<String>();
	
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T10:05:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:02:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T10:08:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T09:50:00Z");
    
   

    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "taimur", "going to rivest with @madham", d3);
    private static final Tweet tweet4 = new Tweet(4, "hammad", "email on hammir@gmail.com for quieries", d4);
    private static final Tweet tweet5 = new Tweet(5, "waleed", "party scenes here with @obaj989 @Maham @madham", d5);
    private static final Tweet tweet6 = new Tweet(6, "madham", "party scenes here with @obaj989@Maham@madham", d6);
    
     
   
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void test2GetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d4, timespan.getEnd());
    }
    
    @Test
    public void test3GetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6));
        
        assertEquals("expected start", d6, timespan.getStart());
        assertEquals("expected end", d4, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void test1GetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet4));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
  
    @Test
    public void test2GetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        Set<String> expectedOutput = new HashSet<String>();
    	expectedOutput.add("@madham");
    	
        assertEquals("expected output",expectedOutput,mentionedUsers);
    }
    
    @Test
    public void test3GetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet4, tweet5, tweet6, tweet3));
        Set<String> expectedOutput = new HashSet<String>();
        expectedOutput.add("@obaj989");
    	expectedOutput.add("@madham");
    	expectedOutput.add("@Maham");
    	
        assertEquals("expected output",expectedOutput,mentionedUsers);
    }
//    @Test
//    public void testGetMentionedUsers() {
//        
//    	Set<String> expectedOutput = new HashSet<String>();
//    	expectedOutput.add("@hamna");
//    	expectedOutput.add("@maria");
//    	expectedOutput.add("@noor");
//    	expectedOutput.add("@hype");
//    	
//    	//tweet3 = "rivest talk in 30 minutes @hype"
//    	//tweet4 = "#he@llo @HAMNA @noor @maria @mit.edu"
//    	
//    	/*
//    	 * As Twitter u
//    	 * sernames are case-insensitive, and the returned set
//    	 * includes a username at most once.
//    	 * 
//    	 * an email address like bitdiddle@mit.edu does NOT 
//    	 * contain a mention of the username mit. 
//    	 * As username-mention cannot be immediately preceded or followed by any
//    	 * character valid in a Twitter username.
//    	 */
//    	Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3, tweet4));
//        System.out.println(mentionedUsers);
//        System.out.println(expectedOutput);
//        assertEquals(expectedOutput, mentionedUsers);
//    }
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
