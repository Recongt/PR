package com.emil.pr;

public class FeatureSelectionResult {
    private int selectedFeatureIndex;
    private double fisherRatio;

    public int getSelectedFeatureIndex() {
        return selectedFeatureIndex;
    }

    public void setSelectedFeatureIndex(int selectedFeatureIndex) {
        this.selectedFeatureIndex = selectedFeatureIndex;
    }

    public double getFisherRatio() {
        return fisherRatio;
    }

    public void setFisherRatio(double fisherRatio) {
        this.fisherRatio = fisherRatio;
    }
}
