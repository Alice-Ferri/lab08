package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private class Death{
        private String causeDeath;
        private String detailDeath;
        
        public Death(){
            this.causeDeath = "heart attack";
            this.detailDeath = "";
        }
    }

    private final Map<String, Death> mapDeath = new HashMap<>();
    private String lastName;
    long startTimeName;
    long endTimeCause;
    long endTimeDetails;

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber > 1 || ruleNumber < DeathNote.RULES.size()) {
            return DeathNote.RULES.get(ruleNumber - 1);
        }
        throw new IllegalArgumentException("The rule number is wrong");
        
    }

    @Override
    public void writeName(String name) {
        if(name == null){
            throw new NullPointerException("The name cannot be null");
        }
        if(!"".equals(name)){
            startTimeName = System.currentTimeMillis();
            this.lastName = name;
            Death newDeath = new Death();
            mapDeath.put(name, newDeath);
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(cause == null || mapDeath.keySet().isEmpty()){
            throw new IllegalStateException("Error with the name o the cause");
        }
        endTimeCause = System.currentTimeMillis();
        if(endTimeCause - startTimeName <= 40){
            mapDeath.get(this.lastName).causeDeath = cause;
            return true;
        }
        else{
            return false; 
        }
    }

    @Override
    public boolean writeDetails(String details) {
        if(details == null || mapDeath.keySet().isEmpty()){
            throw new IllegalStateException("Error with the name or the details");
        }
        endTimeDetails = System.currentTimeMillis();
        if(endTimeDetails - startTimeName <= 40 || 
            "".equals(mapDeath.get(this.lastName).detailDeath)){
                mapDeath.get(this.lastName).detailDeath = details;
                return true;
        }
        else{
            return false; 
        }
    }

    @Override
    public String getDeathCause(String name) {
        if(!mapDeath.containsKey(name)){
            throw new IllegalArgumentException("There isn't the name");
        }
        return mapDeath.get(name).causeDeath;
    }

    @Override
    public String getDeathDetails(String name) {
        if(!mapDeath.containsKey(name)){
            throw new IllegalArgumentException("There isn't the name");
        }
        return mapDeath.get(name).detailDeath;
    }

    @Override
    public boolean isNameWritten(String name) {
        return mapDeath.containsKey(name);
    }


}