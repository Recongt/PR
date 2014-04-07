package com.emil.pr;

public class FeatureInputData {
    private int featureCount;
    private String[] classNames;
    private int[] sampleCount;
    private int[] classLabels;
    private double[][] f;

    public int getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(int featureCount) {
        this.featureCount = featureCount;
    }

    public String[] getClassNames() {
        return classNames;
    }

    public void setClassNames(String[] classNames) {
        this.classNames = classNames;
    }

    public int[] getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int[] sampleCount) {
        this.sampleCount = sampleCount;
    }

    public int[] getClassLabels() {
        return classLabels;
    }

    public void setClassLabels(int[] classLabels) {
        this.classLabels = classLabels;
    }

    public double[][] getF() {
        return f;
    }

    public void setF(double[][] f) {
        this.f = f;
    }
}
