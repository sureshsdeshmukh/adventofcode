package com.ssd.advent.model;

public class Instruction {
    private String operation;
    private int jump;
    private int visit;

    public Instruction(String operation, int jump, int visit) {
        this.operation = operation;
        this.jump = jump;
        this.visit = visit;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "operation='" + operation + '\'' +
                ", jump=" + jump +
                ", visit=" + visit +
                '}';
    }
}
