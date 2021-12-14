package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;

public class SavingsCalculator {

    private float[] credits;
    private float[] debits;

    public SavingsCalculator(float[] credits, float[] debits) {
        this.credits = credits;
        this.debits = debits;
    }

    public static void main(String[] args) {
        String[] creditsAsString = args[0].split(",");
        String[] debitsAsString = args[1].split(",");

        float credits[] = new float[creditsAsString.length];
        for (int i=0; i<creditsAsString.length; i++) {
            credits[i] = Float.parseFloat(creditsAsString[i]);
        }

        float debits[] = new float[debitsAsString.length];
        for (int i=0; i<debitsAsString.length; i++) {
            debits[i] = Float.parseFloat(debitsAsString[i]);
        }

        SavingsCalculator savingsCalculator = new SavingsCalculator(credits, debits);
        float netSavings = savingsCalculator.calculate();

        System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now()));
    }

    private float sumOfCredits() {
        float sum = 0.0f;

        for (float credit : credits) {
            sum += credit;
        }

        return sum;
    }

    private float sumOfDebits() {
        float sum = 0.0f;

        for (float debit : debits) {
            sum += debit;
        }

        return sum;
    }

    private static int remainingDaysInMonth(LocalDate localDate) {
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());

        int totalDaysInMonth = yearMonth.lengthOfMonth();

        int remainingDays = totalDaysInMonth - localDate.getDayOfMonth();

        return remainingDays;
    }

    public float calculate() {
        return sumOfCredits() - sumOfDebits();
    }
}
