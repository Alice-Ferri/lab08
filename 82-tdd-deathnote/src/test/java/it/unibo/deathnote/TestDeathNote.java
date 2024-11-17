package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;




class TestDeathNote {

    @Test
    void testRuleNumber(){
        DeathNote testNote = new DeathNoteImplementation();
        try {
            testNote.getRule(0);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
        try {
            testNote.getRule(DeathNote.RULES.size() + 1);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
        try {
            testNote.getRule(-1);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testRULES(){
        for(int i = 0 ; i < DeathNote.RULES.size(); i ++){
            assertFalse(DeathNote.RULES.get(i).isEmpty());
            assertNotNull(DeathNote.RULES.get(i));
        }
    }

    @Test
    void testHumanDie(){
        final String name = "Giacomo";
        final String wrongName = "Filippo";
        DeathNote testNote = new DeathNoteImplementation();
        assertFalse(testNote.isNameWritten(null));
        assertFalse(testNote.isNameWritten(name));
        testNote.writeName(name);
        assertTrue(testNote.isNameWritten(name));
        assertFalse(testNote.isNameWritten(wrongName));
        testNote.writeName("");
        assertFalse(testNote.isNameWritten(""));
    }

    @Test
    void testCauseDeath() throws InterruptedException {
        DeathNote testNote = new DeathNoteImplementation();
        final String name = "Giacomo";
        final String causeDeath = "heart attack";
        final String name2 = "Filippo";
        final String causeDeath2 = "karting accident";
        final String causeDeathWrong = "fever";

        try{
            testNote.writeDeathCause(causeDeath);
        }
        catch(IllegalStateException e){
            assertNotNull(e.getMessage());
        }
        testNote.writeName(name);
        Thread.sleep(45);
        assertFalse(testNote.writeDeathCause(causeDeath));
        assertEquals(causeDeath, testNote.getDeathCause(name));
        testNote.writeName(name2);
        assertTrue(testNote.writeDeathCause(causeDeath2));
        assertEquals(causeDeath2, testNote.getDeathCause(name2));
        Thread.sleep(100);
        testNote.writeDeathCause(causeDeathWrong);
        assertNotEquals(causeDeath2, testNote.getDeathCause(name2));
    }

    @Test
    void testDetails() throws InterruptedException {
        DeathNote testNote = new DeathNoteImplementation();
        final String name = "Giacomo";
        final String name2 = "Filippo";
        final String details = "ran for too long";
        final String newDetails = "sleep for too long";

        try {
            testNote.writeDetails(null);
            testNote.writeDetails(details);

        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
        }

        testNote.writeName(name);
        assertTrue(testNote.getDeathDetails(name).isEmpty());
        testNote.writeDetails(details);
        assertEquals(details, testNote.getDeathDetails(name));
        testNote.writeName(name2);
        Thread.sleep(6100);
        testNote.writeDetails(newDetails);
        assertNotEquals(newDetails, testNote.getDeathDetails(name2));

    }


}